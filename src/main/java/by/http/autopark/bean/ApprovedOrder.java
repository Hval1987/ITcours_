package by.http.autopark.bean;

import java.util.Objects;

public class ApprovedOrder {
    private int id;
    private int idAssignedCar;
    private String status;
    private int idManager;
    private int idDriver;
    private int idEmployer;
    private int idOrder;
    private String Date;

    public ApprovedOrder() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  int getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(int idEmployer) {
        this.idEmployer = idEmployer;
    }

    public int getIdAssignedCar() {
        return idAssignedCar;
    }

    public void setIdAssignedCar(int idAssignedCar) {
        this.idAssignedCar = idAssignedCar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovedOrder that = (ApprovedOrder) o;
        return idAssignedCar == that.idAssignedCar &&
                idManager == that.idManager &&
                idDriver == that.idDriver &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssignedCar, status, idManager, idDriver);
    }

    @Override
    public String toString() {
        return "ApprovedOrder{" +
                "id=" + id +
                ", idAssignedCar=" + idAssignedCar +
                ", status='" + status + '\'' +
                ", idManager=" + idManager +
                ", idDriver=" + idDriver +
                ", idEmployer=" + idEmployer +
                ", idOrder=" + idOrder +
                '}';
    }
}
