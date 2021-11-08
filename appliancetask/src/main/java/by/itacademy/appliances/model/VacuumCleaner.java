package by.itacademy.appliances.model;

import java.util.Objects;

public class VacuumCleaner extends Appliance {
    private String type;
    private String id;
    private int powerConsumption;
    private String filterType;
    private String bagType;
    private String wandType;
    private int motorSpeedRegulation;
    private int cleaningWidth;

    public VacuumCleaner(String id) {
        this.id = id;
    }

    public VacuumCleaner(String type,String id, int powerConsumption, String filterType, String bagType, String wandType, int motorSpeedRegulation, int cleaningWidth) {
        this.type=type;
        this.id = id;
        this.powerConsumption = powerConsumption;
        this.filterType = filterType;
        this.bagType = bagType;
        this.wandType = wandType;
        this.motorSpeedRegulation = motorSpeedRegulation;
        this.cleaningWidth = cleaningWidth;
    }


    public VacuumCleaner() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getBagType() {
        return bagType;
    }

    public void setBagType(String bagType) {
        this.bagType = bagType;
    }

    public String getWandType() {
        return wandType;
    }

    public void setWandType(String wandType) {
        this.wandType = wandType;
    }

    public int getMotorSpeedRegulation() {
        return motorSpeedRegulation;
    }

    public void setMotorSpeedRegulation(int motorSpeedRegulation) {
        this.motorSpeedRegulation = motorSpeedRegulation;
    }

    public int getCleaningWidth() {
        return cleaningWidth;
    }

    public void setCleaningWidth(int cleaningWidth) {
        this.cleaningWidth = cleaningWidth;
    }

    public String getType() {return type;  }

    public void setType(String type) { this.type = type; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacuumCleaner that = (VacuumCleaner) o;
        return powerConsumption == that.powerConsumption &&
                motorSpeedRegulation == that.motorSpeedRegulation &&
                cleaningWidth == that.cleaningWidth &&
                id.equals(that.id) &&
                filterType.equals(that.filterType) &&
                bagType.equals(that.bagType) &&
                wandType.equals(that.wandType);
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "type=" + type  +
                ", id='" + id + '\'' +
                ", power consumption=" + powerConsumption +
                ", filter type=" + filterType  +
                ", bag type=" + bagType +
                ", wand type=" + wandType  +
                ", motor speed regulation=" + motorSpeedRegulation +
                ", cleaning width=" + cleaningWidth +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, powerConsumption, filterType, bagType, wandType, motorSpeedRegulation, cleaningWidth);
    }
}
