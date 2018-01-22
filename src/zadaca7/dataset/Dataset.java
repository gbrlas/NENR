package zadaca7.dataset;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private final int inputDimension = 2;
    private final int outputDimension = 3;
    private double[][] inputs;
    private double[][] outputs;

    public Dataset(String file) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(file))) {

            List<double[]> in = new ArrayList<>();
            List<double[]> out = new ArrayList<>();

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");

                try {
                    in.add(new double[]{Double.valueOf(parts[0]), Double.valueOf(parts[1])});
                    out.add(new double[]{Double.valueOf(parts[2]), Double.valueOf(parts[3]), Double.valueOf(parts[4])});
                } catch (Exception e) {
                    System.err.println("Invalid file format: " + file);
                    System.exit(1);
                }

                inputs = new double[in.size()][inputDimension];
                inputs = in.toArray(inputs);

                outputs = new double[out.size()][outputDimension];
                outputs = out.toArray(outputs);

            }

        } catch (IOException e) {
            System.err.println("Exception occured while opening a file: " + file);
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public int size() {
        return inputs.length;
    }

    public double[] getInput(int i) {
        return inputs[i];
    }

    public double[] getOutput(int i) {
        return outputs[i];
    }

    public int getInputDimension() {
        return inputDimension;
    }

    public int getOutputDimension() {
        return outputDimension;
    }

    public String toString(int i) {
        double[] in = getInput(i);
        StringBuilder input = new StringBuilder("[ ");
        for (int j = 0; j < inputDimension; j++) {
            input.append(String.format("%.3f ", in[j]));
        }
        input.append("]");

        return input.toString() + ", " + getBinaryOutput(i);
    }

    public String getBinaryOutput(int i) {
        double[] out = getOutput(i);
        StringBuilder output = new StringBuilder();
        for (int j = 0; j < outputDimension; j++) {
            output.append(String.valueOf((int) out[j]));
        }

        return output.toString();
    }


}
