import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier
import joblib

# Load your data (ensure this file is in the same directory or update the path)
df = pd.read_csv("user_activity.csv")  # Replace with your actual dataset path

# Features and Target
X = df[['loginCount', 'profileUpdated', 'lastLoginDays']]
y = df['satisfaction']  # 1 = Satisfied, 0 = Not Satisfied

# Preprocessing
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# Train KNN Model
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)

# Save the trained model and scaler
joblib.dump(knn, "knn_model.pkl")   # This is the model file
joblib.dump(scaler, "scaler.pkl")    # This is the scaler file
