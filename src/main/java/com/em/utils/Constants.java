package com.em.utils;

import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Constants {

    private static final Logger LOGGER = LogManager.getLogger(Constants.class.getName());


    public static final String SPECIALITY_FILE_NAME = "Speciality.properties";
    public static final String MODE_FILE_NAME = "Mode.properties";
    public static final String DIVISION_FILE_NAME = "Division.properties";
    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy hh:mm a";
    public static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_TIME_FORMAT);
    public static final String LOGGED_USER = "loggedUser";
    private Constants() {
    }
}
