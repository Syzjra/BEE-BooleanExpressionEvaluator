package main.java.com.hazdra.Bee.models;

final public class BeeResult {
    private double done;
    private double total;

    public BeeResult(double done, double total) {
        this.done = done;
        this.total = total;
    }

    public double getRate() {
        return total == 0 ? 1.0 : done / total;
    }

    public double getDone() {
        return done;
    }

    public void setDone(double done) {
        this.done = done;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}