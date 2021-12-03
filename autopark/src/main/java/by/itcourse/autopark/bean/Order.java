package by.itcourse.autopark.bean;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private String request;
    private String date;
    private int employerId;
    private String fillingDate;
    private int approvedOrderId;

    public Order(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
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

    public String getFillingDate() {
        return fillingDate;
    }

    public void setFillingDate(String fillingDate) {
        this.fillingDate = fillingDate;
    }

    public int getApprovedOrderId() {
        return approvedOrderId;
    }

    public void setApprovedOrderId(int approvedOrderId) {
        this.approvedOrderId = approvedOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return employerId == order.employerId &&
                approvedOrderId == order.approvedOrderId &&
                request.equals(order.request) &&
                date.equals(order.date) &&
                fillingDate.equals(order.fillingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(request, date, employerId, fillingDate, approvedOrderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", request='" + request + '\'' +
                ", date='" + date + '\'' +
                ", employerId=" + employerId +
                ", fillingDate='" + fillingDate + '\'' +
                ", approvedOrderId=" + approvedOrderId +
                '}';
    }
}
