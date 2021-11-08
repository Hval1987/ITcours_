package by.itacademy.appliances.model;

import java.util.Objects;

public class Laptop extends Appliance {
    private String type;
    private String os;
    private double memory_rom;
    private double system_memory;
    private double cpu;
    private double display_inch;
    private double battery_capacity;
    private String id;


    public Laptop(String type,String os, double memory_rom, double system_memory, double cpu, double display_inch, String id, double battery_capacity) {
        this.type=type;
        this.os = os;
        this.memory_rom = memory_rom;
        this.system_memory = system_memory;
        this.cpu = cpu;
        this.display_inch = display_inch;
        this.id = id;
        this.battery_capacity = battery_capacity;
    }

    public Laptop(){

    }


    public String getId() {
        return id;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getMemory_rom() {
        return memory_rom;
    }

    public void setMemory_rom(double memory_rom) {
        this.memory_rom = memory_rom;
    }

    public double getSystem_memory() {
        return system_memory;
    }

    public void setSystem_memory(double system_memory) {
        this.system_memory = system_memory;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getDisplay_inch() {
        return display_inch;
    }

    public void setDisplay_inch(double display_inch) {
        this.display_inch = display_inch;
    }


    public double getBattery_capacity() {
        return battery_capacity;
    }

    public void setBattery_capacity(double battery_capacity) {
        this.battery_capacity = battery_capacity;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "type=" + type +
                ", os='" + os + '\'' +
                ", memory rom=" + memory_rom +
                ", system memory=" + system_memory +
                ", cpu=" + cpu +
                ", display inch=" + display_inch +
                ", battery capacity=" + battery_capacity +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(laptop.memory_rom, memory_rom) == 0 &&
                Double.compare(laptop.system_memory, system_memory) == 0 &&
                Double.compare(laptop.cpu, cpu) == 0 &&
                Double.compare(laptop.display_inch, display_inch) == 0 &&
                Double.compare(laptop.battery_capacity, battery_capacity) == 0 &&
                os.equals(laptop.os) &&
                id.equals(laptop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(os, memory_rom, system_memory, cpu, display_inch, battery_capacity, id);
    }
}
