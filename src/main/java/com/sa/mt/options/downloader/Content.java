package com.sa.mt.options.downloader;

import java.io.*;

import com.sa.mt.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Content {

    public File saveTo(InputStream stream, String path) {
        File localFile = new File(path);
        if (!localFile.exists()) {
            localFile = FileUtils.createFileWithDirectoryStructure(localFile);
        }
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
