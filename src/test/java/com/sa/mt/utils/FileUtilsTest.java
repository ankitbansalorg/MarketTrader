package com.sa.mt.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileUtilsTest {


    @Test
    public void shouldCreateFileWithDirectoryStructure() throws IOException {
        String directoryPath = "test-orange";
        String filePath = directoryPath + "/Prices.csv";
        File directory = new File(directoryPath);
        File feedFile = new File(filePath);
        assertFalse(directory.exists());
        assertFalse(feedFile.exists());
        File createdFile = FileUtils.createFileWithDirectoryStructure(feedFile);
        assertTrue(directory.exists());
        assertTrue(createdFile.exists());
        createdFile.delete();
        directory.delete();
    }

}
