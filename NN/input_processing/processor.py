import os
import numpy as np
from sklearn.preprocessing import MinMaxScaler

symbols = ['alpha', 'beta', 'gamma', 'delta', 'epsilon']

train_path = os.path.abspath(__file__ + '/../../data')


def scale(x):
    return MinMaxScaler((-1, 1)).fit_transform(x)


def calculate_distance(x, y):
    return np.linalg.norm(y - x)


def get_total_distance(x):
    return sum([calculate_distance(x[i], x[i + 1]) for i in range(len(x) - 1)])


def find_closest(x, space, distances):
    return x[1 + np.argmin([np.abs(distances[i] - space) for i in range(len(distances))])]


def get_processed(x, features=10):
    x = np.array(x, dtype='float64')
    x = scale(x)
    D = get_total_distance(x)
    interspace = [k * D / (features - 1) for k in range(features - 1)]
    distances = [calculate_distance(x[0], x[i]) for i in range(1, len(x))]  # distances from starting point
    x_final = [x[0]]
    [x_final.append(find_closest(x, space, distances)) for space in interspace]
    return np.array(x_final).flatten()


def get_data_from_file(symbol):
    file = train_path + '/' + symbol + '.txt'
    open(file, 'a+')
    return np.genfromtxt(file)


def store_data_to_file(data, symbol):
    file = train_path + '/' + symbol + '.txt'
    f = open(file, 'a+')
    np.savetxt(file, data, fmt='%.6f')
    f.flush()


def get_data():
    for i, symbol in enumerate(symbols):
        yi = np.zeros(len(symbols))
        yi[i] = 1
        xi = get_data_from_file(symbol)
        yi = np.array([yi for i in range(len(xi))])
        if i == 0:
            X = xi
            y = yi
        else:
            X = np.vstack((X, xi))
            y = np.vstack((y, yi))

    return X, y


def store_data(x, symbol, n_features):
    if (len(x) < n_features):
        return False

    # Prepare data to store in file
    x = get_processed(x, n_features)

    # Store data
    data = get_data_from_file(symbol)
    if len(data) == 0:
        data = x
    else:
        data = np.vstack((data, x))
    store_data_to_file(data, symbol)

    return True


def delete_data():
    try:
        for symbol in symbols:
            file = train_path + '/' + symbol + '.txt'
            os.remove(file)
    except FileNotFoundError:
        return


def get_symbol(i):
    return symbols[i]