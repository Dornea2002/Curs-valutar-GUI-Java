public class Moneda {
    private String name;
    private double value;

    public Moneda(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public Moneda() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
