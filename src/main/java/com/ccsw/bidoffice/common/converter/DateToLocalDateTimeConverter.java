package com.ccsw.bidoffice.common.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DateToLocalDateTimeConverter extends StdConverter<Date, LocalDateTime> {

    @Override
    public LocalDateTime convert(Date date) {
        if (date == null)
            return null;

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
