package by.itacademy.appliances.model;

import java.util.Objects;

public class TabletPC extends Appliance {
    private String type;
    private String id;
    private double batteryCapacity;
    private double displayInch;
    private  int memoryRom;
    private int memoryCapacity;
    private String color;

    public TabletPC() {}

    public TabletPC(String type,String id, int batteryCapacity, double displayInch, int memoryRom, int memoryCapacity, String color) {
        this.type=type;
        this.id = id;
        this.batteryCapacity = batteryCapacity;
        this.displayInch = displayInch;
        this.memoryRom = memoryRom;
        this.memoryCapacity = memoryCapacity;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getDisplayInch() {
        return displayInch;
    }

    public void setDisplayInch(double displayInch) {
        this.displayInch = displayInch;
    }

    public int getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(int memoryRom) {
        this.memoryRom = memoryRom;
    }

    public int getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(int memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TabletPC{" +
                "type=" + type  +
                ", id='" + id + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", displayInch=" + displayInch +
                ", memoryRom=" + memoryRom +
                ", memoryCapacity=" + memoryCapacity +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabletPC tabletPC = (TabletPC) o;
        return Double.compare(tabletPC.batteryCapacity, batteryCapacity) == 0 &&
                Double.compare(tabletPC.displayInch, displayInch) == 0 &&
                memoryRom == tabletPC.memoryRom &&
                memoryCapacity == tabletPC.memoryCapacity &&
                id.equals(tabletPC.id) &&
                color.equals(tabletPC.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, batteryCapacity, displayInch, memoryRom, memoryCapacity, color);
    }
}
