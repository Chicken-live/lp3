import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report, accuracy_score, confusion_matrix
from sklearn.svm import SVC

# Load the dataset
df = pd.read_csv(r"D:\Z BE Practical Coding\Datasets\emails.csv")

# Display the first few rows of the dataset to confirm structure
print(df.head())

# Define features (X) and labels (y)
X = df.drop(columns=['Email No.', 'Prediction'])  # Use all columns except 'Email No.' and 'Prediction'
y = df['Prediction']  # This should contain the spam classification

# Splitting the dataset into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# K-Nearest Neighbors Classification
knn = KNeighborsClassifier(n_neighbors=5)
knn.fit(X_train, y_train)
y_pred_knn = knn.predict(X_test)

# Support Vector Machine Classification
svm = SVC(kernel='linear')
svm.fit(X_train, y_train)
y_pred_svm = svm.predict(X_test)

# Performance Evaluation
print("K-Nearest Neighbors Classifier Performance:")
print(confusion_matrix(y_test, y_pred_knn))
print(classification_report(y_test, y_pred_knn))
print(f'Accuracy: {accuracy_score(y_test, y_pred_knn)}\n')

print("Support Vector Machine Classifier Performance:")
print(confusion_matrix(y_test, y_pred_svm))
print(classification_report(y_test, y_pred_svm))
print(f'Accuracy: {accuracy_score(y_test, y_pred_svm)}\n')
