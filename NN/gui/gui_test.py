from tkinter import *
import numpy as np

from neural_network import layers
from neural_network.NN import forward_pass, train
from input_processing import  processor

X, y = processor.get_data()

config = {}
config['max_epochs'] = 20000
config['learning_rate'] = 0.0015
config['batch_size'] = 100

global net

net = []
net += [layers.FC(X, 20, "fc0")]
net += [layers.FC(net[-1], 200, "fc1")]
net += [layers.FC(net[-1], 100, "fc2")]
net += [layers.FC(net[-1], 100, "fc3")]
net += [layers.FC(net[-1], 5, "fc4")]
net += [layers.Sigmoid(net[-1], "s4")]



loss = layers.MSE()

train(X, y, net, loss, config)

alpha = [1.000000, -1.000000, 1.000000, -0.986207, 0.899371, -0.393103, 0.584906, 0.172414, -0.018868, 0.475862,
         -0.899371, 0.448276, -1.000000, 0.931034, -1.000000, 0.931034, -1.000000, 0.931034, -1.000000, 0.931034]

print(forward_pass(net, np.array(X[0])))

