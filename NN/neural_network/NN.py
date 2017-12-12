import numpy as np
from neural_network import layers


def forward_pass(net, inputs):
    output = inputs
    for layer in net:
        output = layer.forward(output)
    return output


def backward_pass(net, loss, x, y):
    grads = []
    grad_out = loss.backward_inputs(x, y)
    if loss.has_params:
        grads += loss.backward_params()
    for layer in reversed(net):
        grad_inputs = layer.backward_inputs(grad_out)
        if layer.has_params:
            grads += [layer.backward_params(grad_out)]
        grad_out = grad_inputs
    return grads

def sgd_update_params(grads, learning_rate):
    for layer_grads in grads:
        for i in range(len(layer_grads) - 1):
            params = layer_grads[i][0]
            grads = layer_grads[i][1]
            params -= learning_rate * grads

def calculate_and_update(net, train_x, train_y, loss, learning_rate):
    fw = forward_pass(net, train_x)
    loss_val = loss.forward(fw, train_y)

    grads = backward_pass(net, loss, fw, train_y)
    sgd_update_params(grads, learning_rate)

    return loss_val

def train(train_x, train_y, net, loss, config):
    max_epochs = config['max_epochs']
    learning_rate = config['learning_rate']
    batch_size = config['batch_size']
    loss_val = 0

    for epoch in range(0, max_epochs):
        i = 0
        while i < len(train_x):
            batch_X, batch_Y = train_x[i:i + batch_size], train_y[i:i + batch_size]
            loss_val = calculate_and_update(net, batch_X, batch_Y, loss, learning_rate)
            i += batch_size

        if epoch % 100 == 0:
            print("Epoch", epoch, " loss:", loss_val)

    return net



