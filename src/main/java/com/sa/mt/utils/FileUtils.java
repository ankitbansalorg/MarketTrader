package com.sa.mt.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static File createFileWithDirectoryStructure(File fileToCreate) {
        String filePath = fileToCreate.getPath();
        int lastIndexOfSeparator = filePath.lastIndexOf(File.separator);
        createDirectoryStructure(filePath.substring(0, lastIndexOfSeparator));
        try {
            fileToCreate.createNewFile();
            return fileToCreate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDirectoryStructure(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
