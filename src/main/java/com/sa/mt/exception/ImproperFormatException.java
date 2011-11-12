package com.sa.mt.exception;

import java.util.Arrays;

public class ImproperFormatException extends RuntimeException {
    public ImproperFormatException(String cause, String[] header) {
        super(cause+ ":" +Arrays.toString(header));
    }
}
