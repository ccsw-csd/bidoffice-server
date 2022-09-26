package com.ccsw.bidoffice.common.converter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.databind.util.StdConverter;

public class DateToLocalDateConverter extends StdConverter<Date, LocalDate> {

    @Override
    public LocalDate convert(Date date) {
        if (date == null)
            return null;
        
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
