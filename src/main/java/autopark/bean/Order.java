package autopark.bean;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private String transportType;
    private String date;
    private int employerId;


    public Order(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                employerId == order.employerId &&
                Objects.equals(transportType, order.transportType) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transportType, date, employerId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", transport type='" + transportType + '\'' +
                ", date='" + date + '\'' +
                ", employerId=" + employerId +
                '}';
    }
}
