package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Seller implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Double basesalary;

    private Department department;

    public Seller() {
    }

    public Seller(Integer id, String name, String email, Double basesalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.basesalary = basesalary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public Double getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(Double basesalary) {
        this.basesalary = basesalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(getId(), seller.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", basesalary=" + basesalary +
                ", department=" + department +
                '}';
    }
}
