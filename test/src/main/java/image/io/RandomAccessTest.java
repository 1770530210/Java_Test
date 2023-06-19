package image.io;

import entity.Employee;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        var staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try (var out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            for (Employee employee : staff) {
                writeData(out, employee);
            }
        }
        try (var in = new RandomAccessFile("employee.dat", "r")) {
            int n = (int) (in.length() / 3);
            var newStaff = new Employee[n];
            for (int i = n - 1; i >= 0; i--) {
                newStaff[i] = new Employee();
                in.seek(i * 3L);
                newStaff[i] = readData(in);
            }
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }

    }

    public static void writeData(DataOutputStream out, Employee employee) {
    }

    public static Employee readData(RandomAccessFile in) {
        return null;
    }
}
