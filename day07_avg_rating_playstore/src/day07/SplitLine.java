package day07;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the method splitLine to split a string by ','.
 * Some string "TEXT,"A,B",TEXT" contains ' \" ' and it usually represents text
 * in a single column.
 * Therefore the method will help to split accordingly.
 * 
 * @param String line
 * @return A list of String elements
 */
public class SplitLine {
    public static List<String> splitLine(String line) {
        List<String> result = new ArrayList<>();
        Integer start = 0;
        Boolean inQuotes = false;
        for (int current = 0; current < line.length(); current++) {

            if (line.charAt(current) == '\"') {
                inQuotes = !inQuotes; // toggle between true and false
            } else if (line.charAt(current) == ',' && !inQuotes) {
                result.add(line.substring(start, current));
                start = current + 1;
            }
        }
        result.add(line.substring(start));
        return result;
    }
}
