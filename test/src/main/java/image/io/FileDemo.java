package image.io;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        String str = "";
        File file1 = new File(str);
        System.out.println(file1);

        String parent = "";
        String child = "";
        File file2 = new File(parent, child);
        System.out.println(file2);


        File file3 = new File(new File(parent), child);
        System.out.println(file3);
    }
}
