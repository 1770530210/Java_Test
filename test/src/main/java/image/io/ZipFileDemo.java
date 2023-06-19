package image.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileDemo {

    /**
     * 解压zip文件
     *
     * @param src
     * @param target
     * @throws IOException
     */
    public static void unzip(File src, File target) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(src));
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {//获取文件或目录，获取不到返回null
            System.out.println(zipEntry);
            if (zipEntry.isDirectory()) {//目录
                File file = new File(target, zipEntry.toString());
                file.mkdirs();
            } else {//文件
                FileOutputStream outputStream = new FileOutputStream(new File(target, zipEntry.toString()));
                int len = 0;
                byte[] data = new byte[1024];
//                while ((len = zipInputStream.read()) != -1) {
//                    outputStream.write(len);
//                }

                while ((len = zipInputStream.read(data)) != -1) {
                    outputStream.write(data, 0, len);
                }

                outputStream.close();
                zipInputStream.closeEntry();
            }
        }
        zipInputStream.close();
    }

    /**
     * 压缩单个文件
     *
     * @param src
     * @param target
     * @throws IOException
     */
    public static void toZipFile(File src, File target) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(target, "a.zip")));
        ZipEntry zipEntry = new ZipEntry("a.txt");//表示压缩包中文件和文件夹
        zipOutputStream.putNextEntry(zipEntry);//放入压缩流中

        FileInputStream fileInputStream = new FileInputStream(src);
        int len;
        byte[] data = new byte[1024];
        while ((len = fileInputStream.read(data)) != -1) {
            zipOutputStream.write(data, 0, len);
        }
        fileInputStream.close();
        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }

}
