import numpy as np
from scipy import signal
import io
import matplotlib.pyplot as plt

def plot(x, y):
    xa = [float(word) for word in x.split()]
    ya = [float(word) for word in y.split()]

    fig, ax = plt.subplots()
    ax.plot(xa, ya)

    f = io.BytesIO()
    plt.savefig(f, format="png")
    return f.getvalue()

def add(x, y):
    return x + y

def test_filtering_with_java_data(x):
    # t = np.linspace(0, 1.0, 2001)
    # xlow = np.sin(2 * np.pi * 5 * t)
    # xhigh = np.sin(2 * np.pi * 250 * t)
    # x = xlow + xhigh
    b, a = signal.butter(8, 0.125)
    y = signal.filtfilt(b, a, x, padlen=150)
    # np.abs(y - xlow).max()
    # print(y)
    return y

def test_plot():
    t = np.linspace(0, 1.0, 2001)
    xlow = np.sin(2 * np.pi * 5 * t)
    xhigh = np.sin(2 * np.pi * 250 * t)
    x = xlow + xhigh
    b, a = signal.butter(8, 0.125)
    y = signal.filtfilt(b, a, x, padlen=150)
    np.abs(y - xlow).max()
    print(a)
    print(b)
    print(y)
    fig, ax = plt.subplots()
    ax.plot(t, y)
    f = io.BytesIO()
    plt.savefig(f, format="png")
    return f.getvalue()

def test_filtering():
    t = np.linspace(0, 1.0, 2001)
    xlow = np.sin(2 * np.pi * 5 * t)
    xhigh = np.sin(2 * np.pi * 250 * t)
    x = xlow + xhigh
    b, a = signal.butter(8, 0.125)
    y = signal.filtfilt(b, a, x, padlen=150)
    np.abs(y - xlow).max()





#
# def main():
#     print("Enter your name, or an empty line to exit.")
#     while True:
#         try:
#             name = input()
#         except EOFError:
#             break
#         if not name:
#             break
#         print("Hello {}!".format(name))