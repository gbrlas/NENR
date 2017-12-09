package zadaca4.records;

/**
 * Class which represents a single measurement record.
 */
public class Record {
    /**
     * Record x value.
     */
    private double x;
    /**
     * Record y value.
     */
    private double y;
    /**
     * Record measurement value.
     */
    private double value;

    /**
     * Constructor which sets the parameters to the provided values.
     *
     * @param x     Record x value.
     * @param y     Record y value.
     * @param value Record measurement value.
     */
    public Record(double x, double y, double value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /**
     * Getter for the record x value.
     *
     * @return Record x value.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the record y value.
     *
     * @return Record y value.
     */
    public double getY() {
        return y;
    }

    /**
     * Getter for the record measurement value.
     *
     * @return Record measurement value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter for the record x value.
     *
     * @param x Value to set.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Setter for the record y value.
     *
     * @param y Value to be set.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Setter for the record measurement value.
     *
     * @param value Value to be set.
     */
    public void setValue(double value) {
        this.value = value;
    }
}
