package com.sa.mt.options.downloader;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.sa.mt.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Content {

    public File saveTo(InputStream stream, String path, String fileName) {
        File localFile = new File(path, fileName);
        if (!localFile.exists()) {
            localFile = FileUtils.createFileWithDirectoryStructure(localFile);
        }
        try {
            File zipFile = downloadZipFile(localFile, stream);
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
            String absolutePath = localFile.getAbsolutePath();
            File csvFile = new File(absolutePath.substring(0, absolutePath.length() - 4));
            if(csvFile.exists()) {
               csvFile.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(csvFile);
            BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream, 65536);
            while(zipInputStream.getNextEntry()!=null) {
                int count;
                byte data[] = new byte[1000];
                while ((count = zipInputStream.read(data,0,1000)) != -1) {
                        outputStream.write(data,0,count);
                }
            }
            outputStream.flush();
            outputStream.close();
            zipInputStream.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return localFile;
    }

    private File downloadZipFile(File localFile, InputStream stream) {
         try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFile.getAbsolutePath()), 65536);
            InputStream inputStream = new BufferedInputStream(stream);
            byte[] readArray = new byte[4096];

            int bytesRead = inputStream.read(readArray);
            while (bytesRead != -1) {
                outputStream.write(readArray, 0, bytesRead);
                bytesRead = inputStream.read(readArray);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return localFile;
    }
}
