package image.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileDemo2 {
    public static void main(String[] args) throws IOException {
        File src = new File("D:/aaa");
        File destParent = src.getParentFile();
        File dest;
        if (src.isDirectory()) {
            dest = new File(destParent, src.getName() + ".zip");
        } else {
            dest = new File(destParent, src.getName().split(".")[0] + ".zip");
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(dest));
        toZip(src, zipOutputStream, "");
        zipOutputStream.close();

    }

    /**
     * @param src
     * @param zipOutputStream
     * @param name
     * @throws IOException
     */
    public static void toZip(File src, ZipOutputStream zipOutputStream, String name) throws IOException {
        File[] files = src.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(name + File.separator + file.getName());//路径
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(file);
                int len = 0;
                byte[] data = new byte[1024];
                while ((len = fileInputStream.read()) != -1) {
                    zipOutputStream.write(data, 0, len);
                }
                fileInputStream.close();
                zipOutputStream.closeEntry();
            } else {
                toZip(file, zipOutputStream, name + File.separator + file.getName());
            }
        }
    }
}
