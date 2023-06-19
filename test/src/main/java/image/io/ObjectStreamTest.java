package image.io;

import entity.Employee;
import entity.Manager;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        var carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        carl.setSecretary(harry);
        var tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
        tony.setSecretary(harry);
        
        var staff = new Employee[3];

        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        try (var out = new ObjectOutputStream(new FileOutputStream("employee.object"))) {
            out.writeObject(staff);
        }
        try (var in = new ObjectInputStream(new FileInputStream("employee.object"))) {
            var newStaff = (Employee[]) in.readObject();

            newStaff[1].raiseSalary(10);

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }

    }
}
