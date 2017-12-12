from neural_network import Layers
from neural_network.NN import train, forward_pass
import numpy as np

X = np.linspace(1, 10, 1000).reshape((1000, 1))
y = np.array([x for x in X])

config = {}
config['max_epochs'] = 200
config['learning_rate'] = 0.05
config['batch_size'] = 1



net = []
net += [Layers.FC(X, 6, "fc1")]
net += [Layers.Sigmoid(net[-1], "sigmoid1")]
net += [Layers.FC(net[-1], 1, "fc2")]
net += [Layers.Sigmoid(net[-1], "sigmoid2")]
net += [Layers.FC(net[-1], 1, "fc3")]

loss = Layers.MSE()

train(X, y, net, loss, config)

print(forward_pass(net, np.array(4))[0])