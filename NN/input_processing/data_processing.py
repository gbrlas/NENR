import os
import numpy as np

from input_processing import data_transformation

symbols = ['alpha', 'beta', 'gamma', 'delta', 'epsilon']
data_path = os.path.abspath(__file__ + '/../../data')


def get_symbol(i):
    return symbols[i]

def load_data():
    X = 0
    y = 0

    for i, symbol in enumerate(symbols):
        yi = np.zeros(len(symbols))
        yi[i] = 1

        file = data_path + '/' + symbol + '.txt'
        open(file, 'a+')
        xi = np.genfromtxt(file)
        yi = np.array([yi for i in range(len(xi))])

        if i == 0:
            X = xi
            y = yi
        else:
            X = np.vstack((X, xi))
            y = np.vstack((y, yi))

    return X, y

def save_data(x, symbol, n_features):
    if len(x) < n_features:
        return False

    x = data_transformation.process_points(x, n_features)

    file = data_path + '/' + symbol + '.txt'
    open(file, 'a+')
    data = np.genfromtxt(file)

    if len(data) == 0:
        data = x
    else:
        data = np.vstack((data, x))

    file = data_path + '/' + symbol + '.txt'
    f = open(file, 'a+')
    np.savetxt(file, data, fmt='%.6f')
    f.flush()

    return True


def delete_data():
    try:
        for symbol in symbols:
            file = data_path + '/' + symbol + '.txt'
            os.remove(file)
    except FileNotFoundError:
        return
