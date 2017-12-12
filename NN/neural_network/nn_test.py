from neural_network import layers
from neural_network.NN import train, forward_pass
import numpy as np

X = np.linspace(1, 10, 1000).reshape((1000, 1))
y = np.array([x for x in X])

config = {}
config['max_epochs'] = 40000
config['learning_rate'] = 0.05
config['batch_size'] = 1000


net = []
net += [layers.FC(X, 6, "fc1")]
net += [layers.Sigmoid(net[-1], "sigmoid1")]
net += [layers.FC(net[-1], 1, "fc2")]
net += [layers.Sigmoid(net[-1], "sigmoid2")]
net += [layers.FC(net[-1], 1, "fc3")]

loss = layers.MSE()

train(X, y, net, loss, config)

print(forward_pass(net, np.array(8))[0])