package com.Crud.jqGridCrud.model;

import java.util.Objects;

public class StudentDetail {
    private int id;
    private String name;
    private String address;
    private String department;
    private String keyName;
    private String active;

    // ✅ Required by Spring for data binding
    public StudentDetail() {
    }

    // For insert (without id)
    public StudentDetail(String name, String address, String department, String keyName, String active) {
        this.name = name;
        this.address = address;
        this.department = department;
        this.keyName = name;
        this.active = active;
    }

    // For fetch/update (with id)
    public StudentDetail(int id, String name, String address,
                         String department, String keyName, String active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.department = department;
        this.keyName = keyName;
        this.active = active;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getKeyName() { return keyName; }
    public void setKeyName(String keyName) { this.keyName = keyName; }

    public String getActive() { return active; }
    public void setActive(String active) { this.active = active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDetail)) return false;
        StudentDetail that = (StudentDetail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(department, that.department) &&
                Objects.equals((keyName), that.keyName) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, department, keyName, active);
    }

    @Override
    public String toString() {
        return "StudentDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", keyName='" + keyName + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}