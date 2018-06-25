package com.em.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class EMException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final String stackTraceToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return "<pre>" + sw.toString() + "</pre>";
    }
    private String message;

    public EMException() {
        message = "An error occured while processing your request.....<br />Please try after sometime";
    }

    public EMException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
