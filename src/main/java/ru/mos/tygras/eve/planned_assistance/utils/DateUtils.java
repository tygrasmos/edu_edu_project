package ru.mos.tygras.eve.planned_assistance.utils;

import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtils {

    private final static String timeIsUp = "00:00:00";

    public LocalDateTime parseToDate(String date){
        Instant inst = Instant.parse(date);
        return LocalDateTime.ofInstant(inst, ZoneId.of("Z"));
    }

    public String getTimeLeft(LocalDateTime endDate){
        Period period = Period.between(LocalDateTime.now().toLocalDate(), endDate.toLocalDate());
        Duration duration = Duration.between(LocalDateTime.now(), endDate);
        long sec = duration.getSeconds();
        StringBuilder sb = new StringBuilder();
        if(sec != 0L) {
            if (period.getMonths() != 0) {
                sb.append(period.getMonths()).append("M").append(" ");
            }
            if (period.getDays() != 0) {
                sb.append(period.getDays()).append("D").append(" ");
            }
            long hours = sec / 3600;
            if (hours > 0) {
                sb.append(hours).append(":");
            } else {
                sb.append("00").append(":");
            }
            long minutes = (sec - hours * 3600) / 60;
            if (minutes > 0) {
                sb.append(minutes).append(":");
            } else {
                sb.append("00").append(":");
            }
            long seconds = sec - hours * 3600 - minutes * 60;
            if (seconds > 0) {
                sb.append(seconds);
            } else {
                sb.append("00");
            }
            return sb.toString();
        } else {
            return timeIsUp;
        }
    }

    public Integer getHourValueTimeLeft(LocalDateTime endDate){
        Duration duration = Duration.between(LocalDateTime.now(), endDate);
        return (int) duration.getSeconds() / 3600;
    }

    public String parseToString(LocalDateTime date, String parseFormat){
        return date.format(DateTimeFormatter.ofPattern(parseFormat));
    }
}
