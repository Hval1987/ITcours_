package by.itacademy.appliances.model;

import java.util.Objects;

public class Oven extends Appliance {

    private String type;
    private String id;
    private int powerConsumption;
    private int weight;
    private int capacity;
    private double depts;
    private double width;
    private double height;

    public Oven(){}

    public Oven(String type, String id, int power_consumption, int weight, int capacity, double depts, double width, double height) {
        this.type=type;
        this.id = id;
        this.powerConsumption = power_consumption;
        this.weight = weight;
        this.capacity = capacity;
        this.depts = depts;
        this.width = width;
        this.height = height;
    }

    public String getType() {return type; }

    public void setType(String type) { this.type = type; }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int power_consumption) {
        this.powerConsumption = power_consumption;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getDepts() {
        return depts;
    }

    public void setDepts(double depts) {
        this.depts = depts;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}


    @Override
    public String toString() {
        return "Oven{" +
                "type=" + type +
                ", id=" + id + '\'' +
                ", power consumption=" + powerConsumption +
                ", weight=" + weight +
                ", capacity=" + capacity +
                ", depts=" + depts +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oven oven = (Oven) o;
        return powerConsumption == oven.powerConsumption &&
                weight == oven.weight &&
                capacity == oven.capacity &&
                Double.compare(oven.depts, depts) == 0 &&
                Double.compare(oven.width, width) == 0 &&
                Double.compare(oven.height, height) == 0 &&
                Objects.equals(type, oven.type) &&
                Objects.equals(id, oven.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, powerConsumption, weight, capacity, depts, width, height);
    }
}
