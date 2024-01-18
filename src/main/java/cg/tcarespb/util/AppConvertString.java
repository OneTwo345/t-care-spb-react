package cg.tcarespb.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppConvertString {
    public static String converString(String dateString) {
        String pattern = "^\\d{2}/\\d{2}/\\d{4}$";
        String patternSecond = "^\\d{4}-\\d{2}-\\d{2}$";

        Pattern regexPattern = Pattern.compile(pattern);
        Pattern regexPatternSecond = Pattern.compile(patternSecond);
        Matcher matcher = regexPattern.matcher(dateString);
        Matcher matcherSecond = regexPatternSecond.matcher(dateString);
        if (matcher.matches()) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate date = LocalDate.parse(dateString, inputFormatter);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String formattedDate = date.format(outputFormatter);
            return formattedDate;
        } else if (matcherSecond.matches()) {
            return dateString;
        }
        return null;
    }
}
