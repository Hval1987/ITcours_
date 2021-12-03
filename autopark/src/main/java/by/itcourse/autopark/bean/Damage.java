package by.itcourse.autopark.bean;

import java.io.Serializable;
import java.util.Objects;

public class Damage implements Serializable {
    private int id;
    private String content;
    private int carIdNumber;
    private int usersId;
    private String status;

    public Damage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCarIdNumber() {
        return carIdNumber;
    }

    public void setCarIdNumber(int carIdNumber) {
        this.carIdNumber = carIdNumber;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Damage damage = (Damage) o;
        return id == damage.id &&
                carIdNumber == damage.carIdNumber &&
                usersId == damage.usersId &&
                Objects.equals(content, damage.content) &&
                Objects.equals(status, damage.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, carIdNumber, usersId, status);
    }

    @Override
    public String toString() {
        return "Damage{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", carIdNumber=" + carIdNumber +
                ", usersId=" + usersId +
                ", status='" + status + '\'' +
                '}';
    }
}
