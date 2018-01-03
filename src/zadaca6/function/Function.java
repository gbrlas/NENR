package zadaca6.function;

/**
 * Class which provides a static method for calculating function value at the provided coordinates.
 *
 * @author goran
 * @version 1.0
 */
public class Function {
    /**
     * Calculates the function value at the provided coordinates.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return Function value at the provided coordinates.
     */
    public static double calculate(double x, double y) {
        return (Math.pow((x - 1), 2) + Math.pow((y + 2), 2) - 5 * x * y + 3) * Math.pow(Math.cos(x / 5), 2);
    }
}
