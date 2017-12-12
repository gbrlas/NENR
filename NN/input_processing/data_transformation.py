import numpy as np
from sklearn.preprocessing import MinMaxScaler


def scale(x):
    return MinMaxScaler((-1, 1)).fit_transform(x)


def get_total_distance(x):
    return sum([np.linalg.norm(x[i + 1] - x[i]) for i in range(len(x) - 1)])


def get_closest_points(x, space, distances):
    return x[1 + np.argmin([np.abs(distances[i] - space) for i in range(len(distances))])]


def process_points(x, features=10):
    x = np.array(x, dtype='float64')
    x = scale(x)
    D = get_total_distance(x)

    interpolation = [k * D / (features - 1) for k in range(features - 1)]
    distances = [np.linalg.norm(x[i] - x[0]) for i in range(1, len(x))]  # distances from starting point

    x_final = [x[0]]
    [x_final.append(get_closest_points(x, space, distances)) for space in interpolation]

    return np.array(x_final).flatten()
