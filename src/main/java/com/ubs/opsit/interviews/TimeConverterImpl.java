package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.Clock;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeConverterImpl implements TimeConverter {

    @Override
    public String convertTime(String aTime) {
        String[] values = aTime.split(":");
        if(values.length != 3) {
            throw new IllegalArgumentException("Input time must be in 00:00:00 format");
        }
        int hours = Integer.parseInt(values[0]);
        int minutes = Integer.parseInt(values[1]);
        int seconds = Integer.parseInt(values[2]);

        validate(hours, minutes, seconds);

        return new BerlinClock().setTime(hours, minutes, seconds).display();
    }

    private void validate(int hours, int minutes, int seconds) {
        if(hours < 0 || hours > 24) {
            throw new IllegalArgumentException("hours must be in [0..24]");
        }
        if(minutes < 0 || minutes > 60) {
            throw new IllegalArgumentException("minutes must be in [0..60]");
        }
        if(seconds < 0 || seconds > 60) {
            throw new IllegalArgumentException("seconds must be in [0..60]");
        }
    }
}
