package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("d MM yy, HH:mm");
    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );

    @Override
    public LocalDateTime parse(String parse) {
        StringBuilder str = new StringBuilder(parse);
        String date;
        int[] diapason = new int[2];
        if (parse.contains("сегодня")) {
            date = DateTimeFormatter
                    .ofPattern("d MM yy")
                    .format(LocalDateTime.now());
            diapason[1] = 7;
        } else if (parse.contains("вчера")) {
            date = DateTimeFormatter
                    .ofPattern("d MM yy")
                    .format(LocalDateTime.now()
                            .minusDays(1));
            diapason[1] = 5;
        } else {
            diapason[0] = str.indexOf(" ") + 1;
            diapason[1] = str.indexOf(" ") + 4;
            date = MONTHS.get(str.substring(diapason[0], diapason[1]));
        }
        str.replace(diapason[0], diapason[1], date);

        return LocalDateTime.parse(str.toString(), FORMATTER);
    }

    public static void main(String[] args) {
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        LocalDateTime date = parser.parse("22 ноя 20, 15:56");
        System.out.println(date);
    }
}
