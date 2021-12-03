package by.itcourse.autopark.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String name;
    private int roles_id;
    private String email;
    private String passportNumber;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(int roles_id) {
        this.roles_id = roles_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return roles_id == user.roles_id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passportNumber, user.passportNumber);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, roles_id, email, passportNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roles_id=" + roles_id +
                ", email='" + email + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
