package zadaca6.data;

import zadaca6.function.Function;

/**
 * Class which represents a single example and contains its x, y and value.
 *
 * @author goran
 * @version 1.0
 */
public class Example {
    /**
     * X value.
     */
    private double x;
    /**
     * Y value.
     */
    private double y;
    /**
     * Function value of this example.
     */
    private double value;

    /**
     * Constructor which sets the instance parameters to the provided ones.
     *
     * @param x X value.
     * @param y Y value.
     */
    public Example(double x, double y) {
        this.x = x;
        this.y = y;
        this.value = Function.calculate(x, y);
    }

    /**
     * Getter for the example x value.
     *
     * @return Example x value.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the example y value.
     *
     * @return Example y value.
     */
    public double getY() {
        return y;
    }

    /**
     * Getter for the example function value.
     *
     * @return Example function value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the example x value to the provided new one.
     *
     * @param x X value.
     */
    public void setX(double x) {
        this.x = x;
        this.value = Function.calculate(this.x, this.y);
    }

    /**
     * Sets the example y value to the provided new one.
     *
     * @param y Y value.
     */
    public void setY(double y) {
        this.y = y;
        this.value = Function.calculate(this.x, this.y);
    }

    /**
     * Sets the example function value to the provided new one.
     *
     * @param value Function value.
     */
    public void setValue(double value) {
        this.value = value;
    }
}
