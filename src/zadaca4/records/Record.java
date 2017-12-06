package zadaca4.records;

public class Record {
    private double x;
    private double y;
    private double value;

    public Record(double x, double y, double value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getValue() {
        return value;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
