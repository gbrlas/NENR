package zadaca4.functions;

public class Function {

    public static double getValue(double x, double y, double[] beta) {
        return Math.sin(beta[0] + beta[1] * x) + beta[2]
                * Math.cos(x * (beta[3] + y))
                * (1. / (1 + Math.exp(Math.pow(x - beta[4], 2))));
    }
}
