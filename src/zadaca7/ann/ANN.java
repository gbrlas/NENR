package zadaca7.ann;

import zadaca7.dataset.Dataset;

import java.util.Arrays;

public class ANN {

    private int[] layers;
    private double[] weights;

    public ANN(int[] layers) {
        this.layers = layers;
    }

    public String predict(double[] inputs) {
        double[] output = forwardPass(inputs, weights);
        StringBuilder o = new StringBuilder();

        for (double anOutput : output) {
            o.append((anOutput < 0.5) ? "0" : "1");
        }

        return o.toString();
    }

    private double[] forwardPass(double[] inputs, double[] weights) {

        int layerIndex;
        int neuronIndex;
        int weightIndex;

        double[] inputsCopy = Arrays.copyOf(inputs, inputs.length);


        double[] z = new double[layers[1]];
        int numOfTypeOneNeurons = layers[1];

        // Type 1 neurons
        for (int j = 0; j < numOfTypeOneNeurons; j++) {
            neuronIndex = layers[0] * j * 2;
            double net = 0;

            for (int k = 0, numOfInputs = inputsCopy.length; k < numOfInputs; k++) {
                weightIndex = neuronIndex + 2 * k;
                net += Math.abs(inputsCopy[k] - weights[weightIndex]) / Math.abs(weights[weightIndex + 1]);
            }

            z[j] = 1. / (1 + net);
        }

        inputsCopy = z;

        // Type 2 neurons
        for (int i = 2, numOfLayers = layers.length; i < numOfLayers; i++) {
            layerIndex = layers[0] * layers[1] * 2;
            for (int l = 2; l < i; l++) {
                layerIndex += layers[l - 1] * layers[l];
            }
            z = new double[layers[i]];
            for (int j = 0, numOfNeurons = layers[i]; j < numOfNeurons; j++) {
                neuronIndex = layers[i - 1] * j;
                double net = 0;
                for (int k = 0, numOfInputs = inputsCopy.length; k < numOfInputs; k++) {
                    weightIndex = layerIndex + neuronIndex + k;
                    net += inputsCopy[k] * weights[weightIndex];
                }
                z[j] = 1. / (1 + Math.exp(-net));
            }

            inputsCopy = z;

        }

        return inputsCopy;

    }


    public int getNumberOfWeights() {
        int numOfWeights = layers[0] * layers[1] * 2;
        for (int i = 1, len = layers.length - 1; i < len; i++) {
            numOfWeights += layers[i] * layers[i + 1];
        }
        return numOfWeights;
    }

    public double calculateError(Dataset dataset, double[] weights) {
        int N = dataset.size();
        double error = 0;
        int outputDimension = dataset.getOutputDimension();

        for (int s = 0; s < N; s++) {
            double[] t = forwardPass(dataset.getInput(s), weights);
            double[] y = dataset.getOutput(s);

            for (int o = 0; o < outputDimension; o++) {
                error += Math.pow(t[o] - y[o], 2);
            }
        }
        return error / N;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public int[] getLayers() {
        return layers;
    }

    public void setLayers(int[] layers) {
        this.layers = layers;
    }
}
