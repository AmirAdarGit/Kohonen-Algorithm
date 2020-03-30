import numpy as np
import sys
import math
import matplotlib.pyplot as plt
import random


class Point:
    def __init__(self, x=0, y=0, change=0):
        self.x = x
        self.y = y
        self.change = change


def min_distance(point, neurons):
    min = sys.maxsize
    index = 0
    distance = 0
    for i in range(len(neurons)):
        distance = math.sqrt((point.x - neurons[i].x)**2 + (point.y - neurons[i].y)**2)
        if distance < min:
            min = distance
            index = i
    return index


def move_algorithm(point, index, neurons, radius):
    neurons[index].x += (point.x - neurons[index].x)*1/2
    neurons[index].y += (point.y - neurons[index].y)*1/2
    neurons[index].change = 1
    for i in range(1, radius+1):
        i_right = (index + i) % len(neurons)
        i_left = index - i
        if i_left < 0:
            i_left += len(neurons)
        d = 1/(2**(i+1))
        if i_left != i_right:
            left_distance_x = (point.x - neurons[i_left].x) * d + neurons[i_left].x
            left_distance_y = (point.y - neurons[i_left].y) * d + neurons[i_left].y

            right_distance_x = (point.x - neurons[i_right].x) * d + neurons[i_right].x
            right_distance_y = (point.y - neurons[i_right].y) * d + neurons[i_right].y

            neurons[i_left].x = left_distance_x
            neurons[i_left].y = left_distance_y
            neurons[i_left].change = 1

            neurons[i_right].x = right_distance_x
            neurons[i_right].y = right_distance_y
            neurons[i_right].change = 1
    return neurons


def algorithm(points, neurons, label):
    size = int(len(points)/len(neurons)*2) + 1
    radius = int(len(neurons)/2)
    neurons_x = []
    neurons_y = []
    for i in range(len(points)):
        plt.scatter(points[i].x, points[i].y, color='pink')
    for i in range(len(neurons)):
        neurons_x.append(neurons[i].x)
        neurons_y.append(neurons[i].y)
        plt.scatter(neurons[i].x, neurons[i].y, color='blue')

    plt.suptitle(label)
    plt.title("Processing: Radius: " + str(radius))
    plt.plot(neurons_x, neurons_y)
    plt.draw()
    plt.pause(0.1)
    plt.clf()

    for i in range(len(points)):
        if i % size == 0 and i != 0:
            radius -= 1
        new_neurons = move_algorithm(points[i], min_distance(points[i], neurons), neurons, radius)
        for j in range(len(neurons)):
            neurons_x[j] = new_neurons[j].x
            neurons_y[j] = new_neurons[j].y
            if new_neurons[j].change == 1:
                plt.scatter(new_neurons[j].x, new_neurons[j].y, color='orange')
                new_neurons[j].change = 0
            else:
                plt.scatter(new_neurons[j].x, new_neurons[j].y, color='blue')
        for j in range(len(points)):
            if j == i:
                 plt.scatter(points[j].x, points[j].y, color='red')
            else:
                plt.scatter(points[j].x, points[j].y, color='pink')
        plt.suptitle(label)
        plt.title("Processing: Radius: " + str(radius))
        plt.plot(neurons_x, neurons_y)
        plt.draw()
        plt.pause(0.1)
        plt.clf()
    for i in range(len(new_neurons)):
        neurons_x[i] = new_neurons[i].x
        neurons_y[i] = new_neurons[i].y
        plt.scatter(new_neurons[i].x, new_neurons[i].y, color='blue')
    for i in range(len(points)):
        plt.scatter(points[i].x, points[i].y, color='pink')
    plt.suptitle(label)
    plt.title("Done!")
    plt.plot(neurons_x, neurons_y)
    plt.show()


if __name__ == '__main__':
    neurons = []
    neurons2 = []
    neurons3 = []
    neurons4 = []
    points = []
    points2 = []
    points3 = []
    points4 = []
    for i in range(1, 16):
        neurons.append(Point(random.randint(1, 60), random.randint(1, 60)))

    # Question 1 - TEST
    for i in range(100):
        points.append(Point(random.randint(0, 50), random.randint(0, 50)))

    algorithm(points, neurons, "Uniform Data & Neurons Distribution")

    for i in range(1, 16):
        neurons2.append(Point(random.randint(1, 60), random.randint(1, 60)))

    #Question 2 - TEST
    for i in range(70):
        points2.append(Point(random.randint(20, 30), random.randint(20, 30)))
    #
    for i in range(70):
        points2.append(Point(random.randint(0, 50), random.randint(0, 50)))

    random.shuffle(points2)
    algorithm(points2, neurons2, "Center Data & Neurons Distribution")

    for i in range(1, 16):
        neurons3.append(Point(random.randint(1, 60), random.randint(1, 60)))

    # Question 3 - TEST
    for i in range(140):
        index = random.randint(1, 50)
        points3.append(Point(index, index))

    algorithm(points3, neurons3, "Diagonal Data & Neurons Distribution")

    for i in range(1, 16):
        neurons4.append(Point(random.randint(1, 60), random.randint(1, 60)))

    #Question 4 - TEST
    for i in range(40):
        points4.append(Point(random.randint(10, 15), random.randint(10, 40)))

    for i in range(40):
        points4.append(Point(random.randint(35, 40), random.randint(10, 40)))

    for i in range(30):
        points4.append(Point(random.randint(15, 35), random.randint(10, 15)))

    for i in range(30):
        points4.append(Point(random.randint(15, 35), random.randint(35, 40)))

    random.shuffle(points4)
    algorithm(points4, neurons4, "Donut Data & Neurons Distribution")









