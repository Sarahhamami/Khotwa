from flask import Flask, request, jsonify
import joblib
import numpy as np

# Initialize the Flask app
app = Flask(__name__)

# Load the pre-trained model and scaler
knn_model = joblib.load("knn_model.pkl")
scaler = joblib.load("scaler.pkl")

# A simple test route
@app.route('/api/message', methods=['GET'])
def get_message():
    return jsonify({"message": "Hello from Flask API!"})

# Prediction route
@app.route('/predict', methods=['POST'])
def predict():
    try:
        # Get the input data from the request
        data = request.get_json()

        # Log the incoming data to verify it
        print(f"Received data: {data}")

        # Validate and convert data
        try:
            login_count = int(data['loginCount'])
            profile_updated = int(data['profileUpdated'])
            last_login_days = int(data['lastLoginDays'])
        except ValueError:
            return jsonify({"error": "Invalid data types for one or more fields"}), 400

        # Extract features from input
        features = np.array([[login_count, profile_updated, last_login_days]])

        # Scale the input features
        scaled_features = scaler.transform(features)

        # Make prediction using the KNN model
        prediction = knn_model.predict(scaled_features)

        # Return the prediction result
        result = 'Satisfied' if prediction[0] == 1 else 'Not Satisfied'
        return jsonify({"satisfaction": result})

    except Exception as e:
        print(f"Error during prediction: {str(e)}")  # Log the error
        return jsonify({"error": str(e)}), 400


# Start the app
if __name__ == '__main__':
    app.run(debug=True, port=5000)
