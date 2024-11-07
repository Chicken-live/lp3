import numpy as np
import matplotlib.pyplot as plt

# Define the function and its derivative
def func(x):
    return (x + 3)**2  # Function to minimize

def grad_func(x):
    return 2 * (x + 3)  # Derivative of the function

# Gradient Descent function
def gradient_descent(learning_rate, start_point, num_iterations):
    x = start_point
    history = [x]  # List to store the history of points

    for i in range(num_iterations):
        grad = grad_func(x)  # Compute the gradient
        x = x - learning_rate * grad  # Update the point by moving in the direction of the negative gradient
        history.append(x)  # Store the new point

    return x, history

# Parameters
learning_rate = 0.1  # Step size
start_point = 2  # Initial point
num_iterations = 50  # Number of iterations

# Run the gradient descent
min_point, history = gradient_descent(learning_rate, start_point, num_iterations)

# Print the result
print(f"Local minima found at x = {min_point}")
print(f"Function value at the minima: y = {func(min_point)}")

# Plotting the function and the gradient descent steps
x_vals = np.linspace(-10, 4, 100)  # Generate x values for plotting
y_vals = func(x_vals)  # Corresponding y values for the function

plt.figure(figsize=(8, 6))
plt.plot(x_vals, y_vals, label='Function: $(x+3)^2$', color='blue')  # Plot the function
plt.scatter(history, [func(x) for x in history], color='red', zorder=5)  # Plot the gradient descent steps
plt.plot(history, [func(x) for x in history], color='red', linestyle='dashed', label='Gradient Descent Steps')
plt.title('Gradient Descent for Finding Local Minima')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid(True)
plt.show()
