package zadaca4.functions;

/**
 * Class which implements the assigned function.
 *
 * @author goran
 * @version 1.0
 */
public class Function {
    /**
     * Calculates and returns the function value at the provided (x, y) points based on the chromosome gene values.
     *
     * @param x    X point.
     * @param y    Y point.
     * @param beta Array containing chromosome gene values.
     * @return Function value at the provided points.
     */
    public static double getValue(double x, double y, double[] beta) {
        return Math.sin(beta[0] + beta[1] * x) + beta[2]
                * Math.cos(x * (beta[3] + y))
                * (1. / (1 + Math.exp(Math.pow(x - beta[4], 2))));
    }
}
