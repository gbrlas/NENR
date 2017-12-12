from abc import ABCMeta, abstractmethod
import scipy.stats as stats
import numpy as np

def variance_scaling_initializer(shape, fan_in, factor=2.0, seed=None):
    sigma = np.sqrt(factor / fan_in)
    return stats.truncnorm(-2, 2, loc=0, scale=sigma).rvs(shape)

# -- ABSTRACT CLASS DEFINITION --
class Layer(metaclass = ABCMeta):
    "Interface for layers"
    # See documentation of abstract base classes (ABC): https://docs.python.org/3/library/abc.html

    @abstractmethod
    def forward(self, inputs):
        """
        Args:
          inputs: ndarray tensor.
        Returns:
          ndarray tensor, result of the forward pass.
        """
        pass

    @abstractmethod
    def backward_inputs(self, grads):
        """
        Args:
          grads: gradient of the loss with respect to the output of the layer.
        Returns:
          Gradient of the loss with respect to the input of the layer.
        """
        pass

    def backward_params(self, grads):
        """
        Args:
          grads: gradient of the loss with respect to the output of the layer.
        Returns:
          Gradient of the loss with respect to all the parameters of the layer as a list
          [[w0, g0], ..., [wk, gk], self.name] where w are parameter weights and g their gradient.
          Note that wk and gk must have the same shape.
        """
        pass

class FC(Layer):
    def __init__(self, input_layer, num_outputs, name,
                 weights_initializer_fn=variance_scaling_initializer,
                 bias_initializer_fn=np.zeros):
        """
        Args:
          input_layer: layer below
          num_outputs: number of neurons in this layer
          weights_initializer_fn: initializer function for weights,
          bias_initializer_fn: initializer function for biases
        """

        self.input_shape = input_layer.shape
        self.N = self.input_shape[0]
        self.shape = (self.N, num_outputs)
        self.num_outputs = num_outputs

        self.num_inputs = 1
        for i in range(1, len(self.input_shape)):
            self.num_inputs *= self.input_shape[i]

        self.weights = weights_initializer_fn([num_outputs, self.num_inputs], fan_in=self.num_inputs)
        self.bias = bias_initializer_fn([num_outputs])
        self.name = name
        self.has_params = True

    def forward(self, inputs):
        """
        Args:
          inputs: ndarray of shape (N, num_inputs)
        Returns:
          An ndarray of shape (N, num_outputs)
        """
        self.inputs = inputs
        return inputs.dot(self.weights.T) + self.bias

    # error backprop
    def backward_inputs(self, grads):
        """
        Args:
          grads: ndarray of shape (N, num_outputs)
        Returns:
          An ndarray of shape (N, num_inputs)
        """
        return grads.dot(self.weights)

    # weight gradients backprop
    def backward_params(self, grads):
        """
        Args:
          grads: ndarray of shape (N, num_outputs)
        Returns:
          List of params and gradient pairs.
        """
        grad_weights = grads.T.dot(self.inputs)
        grad_bias = grads.sum(axis=0)
        return [[self.weights, grad_weights], [self.bias, grad_bias], self.name]

def sigmoid_function(x):
    return 1. / (1 + np.exp(-x))

class Sigmoid(Layer):
    def __init__(self, input_layer, name):
        self.shape = input_layer.shape
        self.name = name
        self.has_params = False

    def forward(self, inputs):
        """
        Args:
          inputs: ndarray of shape (N, C, H, W).
        Returns:
          ndarray of shape (N, C, H, W).
        """
        self.inputs = inputs
        return sigmoid_function(inputs)

    def backward_inputs(self, grads):
        """
        Args:
          grads: ndarray of shape (N, C, H, W).
        Returns:
          ndarray of shape (N, C, H, W).
        """
        return grads * sigmoid_function(self.inputs) * (1 - sigmoid_function(self.inputs))


class MSE():
    def __init__(self):
        self.has_params = False

    def forward(self, x, y):
        """
       Args:
         x: ndarray of shape (N, num_classes).
         y: ndarray of shape (N, num_classes).
       Returns:
         Scalar, average loss over N examples.
         It is better to compute average loss here instead of just sum
         because then learning rate and weight decay won't depend on batch size.
       """
        return np.sum(1 / (2. * len(y)) * np.dot((y - x).T, (y - x)))

    def backward_inputs(self, x, y):
        """
       Args:
         x: ndarray of shape (N, num_classes).
         y: ndarray of shape (N, num_classes).
       Returns:
         Gradient with respect to the x, ndarray of shape (N, num_classes).
       """
        # Hint: don't forget that we took the average in the forward pass
        return 1 / (1. * len(y)) * (-(y - x))