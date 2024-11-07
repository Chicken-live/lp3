import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.ensemble import RandomForestRegressor
from sklearn.metrics import mean_squared_error, r2_score

# Load the dataset
url = 'path_to_your_downloaded_csv_file.csv'  # Replace with the local path to your CSV file
df = pd.read_csv(url)

# Display the first few rows of the dataset
print(df.head())

# Check for missing values
print(df.isnull().sum())

# Drop rows with missing values
df = df.dropna()

# Convert the pickup datetime column to datetime format
df['pickup_datetime'] = pd.to_datetime(df['pickup_datetime'])

# Extract relevant features (e.g., hour, day of the week)
df['pickup_hour'] = df['pickup_datetime'].dt.hour
df['pickup_day'] = df['pickup_datetime'].dt.dayofweek

# Check the types of the dataframe
print(df.info())

# Visualize the distribution of fare prices to identify outliers
plt.figure(figsize=(10, 6))
sns.boxplot(x=df['fare_amount'])
plt.title('Fare Amount Distribution')
plt.show()

# Remove outliers (e.g., using IQR method)
Q1 = df['fare_amount'].quantile(0.25)
Q3 = df['fare_amount'].quantile(0.75)
IQR = Q3 - Q1

# Define the limits for outliers
lower_bound = Q1 - 1.5 * IQR
upper_bound = Q3 + 1.5 * IQR

# Filter the dataframe to remove outliers
df = df[(df['fare_amount'] >= lower_bound) & (df['fare_amount'] <= upper_bound)]

# Check the correlation matrix
correlation_matrix = df.corr()

# Display the correlation heatmap
plt.figure(figsize=(12, 8))
sns.heatmap(correlation_matrix, annot=True, fmt='.2f', cmap='coolwarm')
plt.title('Correlation Matrix')
plt.show()

# Define features and target variable
X = df.drop(['fare_amount', 'pickup_datetime'], axis=1)  # Drop target variable and datetime
y = df['fare_amount']

# Split the dataset into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Linear Regression
lin_reg = LinearRegression()
lin_reg.fit(X_train, y_train)
y_pred_lin = lin_reg.predict(X_test)

# Random Forest Regression
print("Fitting Random Forest model...")
rf_reg = RandomForestRegressor(n_estimators=10, random_state=42)  # Reduced number of estimators
rf_reg.fit(X_train, y_train)
y_pred_rf = rf_reg.predict(X_test)

# Evaluate Linear Regression Model
lin_r2 = r2_score(y_test, y_pred_lin)
lin_rmse = mean_squared_error(y_test, y_pred_lin, squared=False)

# Evaluate Random Forest Regression Model
rf_r2 = r2_score(y_test, y_pred_rf)
rf_rmse = mean_squared_error(y_test, y_pred_rf, squared=False)

# Print the results
print(f'Linear Regression R^2: {lin_r2:.4f}, RMSE: {lin_rmse:.4f}')
print(f'Random Forest Regression R^2: {rf_r2:.4f}, RMSE: {rf_rmse:.4f}')
