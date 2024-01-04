package com.tdd.catalog.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;

public class DateUtils {
    private static final Logger logger = LogManager.getLogger();

    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static boolean isValid(String value, String pattern, boolean allowBlank) {
        if (Strings.isBlank(value))
            return allowBlank;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
            formatter.parse(value);
            return true;
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    public static LocalDate toLocalDate(String value, String pattern) {
        if (value == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withResolverStyle(ResolverStyle.STRICT);
        return LocalDate.parse(value, formatter);
    }

    public static LocalDateTime toLocalDateTime(String value, String pattern) {
        if (value == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(value, formatter);
    }

    public static String toString(TemporalAccessor value, String pattern) {
        if (value == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(value);
    }
}
