import pandas as pd

# Creating a mock dataset
data = {
    "loginCount": [10, 15, 8, 20, 5, 18, 12, 14, 7, 9],
    "profileUpdated": [1, 0, 1, 1, 0, 1, 0, 1, 1, 0],
    "lastLoginDays": [5, 7, 3, 1, 10, 4, 8, 2, 6, 12],
    "satisfaction": [1, 0, 1, 1, 0, 1, 0, 1, 0, 0]
}

# Convert the data into a DataFrame
df = pd.DataFrame(data)

# Save to CSV
df.to_csv("user_activity.csv", index=False)
print("CSV file 'user_activity.csv' created!")
