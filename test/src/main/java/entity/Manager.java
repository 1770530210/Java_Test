package entity;

import java.util.Date;

public class Manager extends Employee {
    private Employee secretary;

    public Manager(String name, int salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        this.hireDay = new Date(year, month, day);
    }

    public void setSecretary(Employee harry) {
        secretary = harry;
    }
}
