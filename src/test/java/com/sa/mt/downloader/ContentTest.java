package com.sa.mt.downloader;

import com.sa.mt.options.downloader.Content;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContentTest {

    @Test
    public void shouldSaveStreamToAFile() {
        String file = new File(".") + File.separator + "HelloStreamSave.txt";
        File testFile = new File(file);
        assertFalse(testFile.exists());
        String text = "Hello world .. try saving it";
        InputStream stream = IOUtils.toInputStream(text);
        Content content = new Content();
        try {
            File savedFile = content.saveTo(stream, file);
            assertTrue(savedFile.exists());
            assertTrue(savedFile.length() == text.length());
        } finally {
            if (testFile.exists()) {
                testFile.delete();
            }
        }

    }
}
