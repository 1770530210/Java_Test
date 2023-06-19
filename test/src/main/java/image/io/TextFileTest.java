package image.io;

import entity.Employee;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args) throws IOException {
        var staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (var out = new PrintWriter("employee.dat", StandardCharsets.UTF_8)) {
            writeData(staff, out);
        }
        try (var in = new Scanner(new FileInputStream("employee.dat"), StandardCharsets.UTF_8)) {
            Employee[] newStaff = readData(in);
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        for (Employee employee : employees) {
            writeEmployee(out, employee);
        }
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();

        var employee = new Employee[n];
        for (int i = 0; i < n; i++) {
            employee[i] = readEmployee(in);
        }
        return employee;
    }

    public static void writeEmployee(PrintWriter out, Employee employee) {
        out.println(employee.getName() + "|" + employee.getSalary() + "|" + employee.getHireDay());
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        Date hireDate = new Date(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonth();
        int day = hireDate.getDay();
        return new Employee(name, salary, year, month, day);
    }
}
