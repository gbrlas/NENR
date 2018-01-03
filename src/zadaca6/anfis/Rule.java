package zadaca6.anfis;

import zadaca6.data.Example;

import java.util.Random;

/**
 * Class which represents a single rule and all its parameters.
 */
public class Rule {
    /**
     * Sigmoid of (x, A1, A2).
     */
    double alpha;
    /**
     * Sigmoid of (y, B1, B2).
     */
    double beta;
    /**
     * alpha * beta.
     */
    double w;
    /**
     * Normalized alpha * beta.
     */
    double w_norm;
    /**
     * p*x + q*y + r
     */
    double z;

    double A1;
    double A2;
    double B1;
    double B2;

    double p;
    double q;
    double r;

    private double dA1;
    private double dA2;
    private double dB1;
    private double dB2;

    private double dp;
    private double dq;
    private double dr;

    /**
     * Constructor which initializes random parameter values.
     */
    public Rule() {
        Random rand = new Random();

        A1 = rand.nextDouble();
        A2 = rand.nextDouble();
        B1 = rand.nextDouble();
        B2 = rand.nextDouble();
        p = rand.nextDouble();
        q = rand.nextDouble();
        r = rand.nextDouble();

        resetDerivations();
    }

    /**
     * Updates rule's derivation values.
     *
     * @param error    Error of the example.
     * @param razlomak
     * @param example  Example
     */
    public void updateDerivations(double error, double razlomak, Example example) {
        dA1 -= error * razlomak * beta * alpha * (1 - alpha) * A2;
        dA2 -= error * razlomak * beta * alpha * (1 - alpha) * (A1 - example.getX());
        dB1 -= error * razlomak * alpha * beta * (1 - beta) * B2;
        dB2 -= error * razlomak * alpha * beta * (1 - beta) * (B1 - example.getY());

        dp -= error * w_norm * example.getX();
        dq -= error * w_norm * example.getY();
        dr -= error * w_norm;
    }

    /**
     * Resets derivation values to zero.
     */
    public void resetDerivations() {
        dA1 = dA2 = dB1 = dB2 = dp = dq = dr = 0;
    }

    /**
     * Updates parameter values.
     *
     * @param learningRate
     */
    public void updateParameters(double learningRate) {
        A1 -= learningRate * dA1;
        A2 -= learningRate * dA2;
        B1 -= learningRate * dB1;
        B2 -= learningRate * dB2;
        p -= learningRate * dp;
        q -= learningRate * dq;
        r -= learningRate * dr;
    }

    @Override
    public String toString() {
        return "" + A1 + "   " + A2 + "   " + B1 + "    " + B2;
    }
}