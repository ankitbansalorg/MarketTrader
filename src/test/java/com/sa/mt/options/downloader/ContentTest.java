package com.sa.mt.options.downloader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContentTest {

    @Test
    public void shouldSaveStreamToAFile() {
        String path = new File(".") + File.separator;
        String fileName = "HelloStreamSave.txt";
        File testFile = new File(path, fileName);
        assertFalse(testFile.exists());
        String text = "Hello world .. try saving it";
        InputStream stream = IOUtils.toInputStream(text);
        Content content = new Content();
        try {
            File savedFile = content.saveTo(stream, path, fileName);
            assertTrue(savedFile.exists());
            assertTrue(savedFile.length() == text.length());
        } finally {
            if (testFile.exists()) {
                testFile.delete();
            }
        }

    }
}
