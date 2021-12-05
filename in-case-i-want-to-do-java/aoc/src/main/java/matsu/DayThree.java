package matsu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DayThree {

    // This day was just a brute force shitshow. Sorry for that
    public static void solve() {
        String input = Util.readFile("input3.txt");
        /*String input = """
00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010
""";*/

        List<String> rows = List.of(input.split("\n"));
        List<String> columns = Arrays.asList(new String[rows.get(0).length()]);
        Collections.fill(columns, "");

        for (String row : rows) {
            for (int i = 0; i < row.length(); i++) {
                columns.set(i, columns.get(i) + row.charAt(i));
            }
        }

        columns.forEach(System.out::println);
        String gamma = "";
        Map<Integer, Character> mostCommon = new HashMap<>();
        int i = 0; 
        for (String col : columns) {
            long zeros = Stream.of(col.split("")).filter(c -> c.equals("0")).count();;
            long ones = Stream.of(col.split("")).filter(c -> c.equals("1")).count();;
            gamma += zeros > ones ? "0" : "1";

            mostCommon.put(i, zeros > ones ? '0' : '1');
            i++;
        }

        String epsilon = String.join("", Stream.of(gamma.split("")).map(c -> {
            return c.equals("0") ? "1" : "0";
        }).toList());


        System.out.println(gamma);
        System.out.println(epsilon);
        int gammaDecimal = Integer.parseInt(gamma, 2);
        int epsilonDecimal = Integer.parseInt(epsilon, 2);
        System.out.println(gammaDecimal);
        System.out.println(epsilonDecimal);

        System.out.println("Power: "+ gammaDecimal * epsilonDecimal);
        System.out.println("===================");

        List<String> oxygenRows = rows;
        i = 0;
        while (oxygenRows.size() > 1) {
            System.out.println(i + ": " + oxygenRows.size());
            oxygenRows.forEach(System.out::println);
            oxygenRows = filterRows(oxygenRows, i);
            i++;
        }
        int oxygen = Integer.parseInt(oxygenRows.get(0), 2);
        System.out.println("oxygenRows" + oxygenRows);
        System.out.println("Oxygen: "+ oxygen);

        List<String> co2Rows = rows;
        i = 0;
        while (co2Rows.size() > 1) {
            System.out.println(i + ": " + co2Rows.size());
            co2Rows.forEach(System.out::println);
            co2Rows = filterRowsLeastCommon(co2Rows, i);
            i++;
        }
        int co2 = Integer.parseInt(co2Rows.get(0), 2);
        System.out.println("CO2 " + co2);

        System.out.println(" Total " + co2 * oxygen);
    }

    static List<String> filterRows(List<String> rows, final int index) {
        Map<Integer, Character> mostCommon = getMostCommon(rows);
        return rows.stream().filter(row -> row.charAt(index) == mostCommon.get(index)).toList();
    }

    static List<String> filterRowsLeastCommon(List<String> rows, final int index) {
        Map<Integer, Character> mostCommon = getMostCommon(rows);
        return rows.stream().filter(row -> row.charAt(index) != mostCommon.get(index)).toList();
    }

    static Map<Integer, Character> getMostCommon(List<String> rows) {
        Map<Integer, Character> mostCommon = new HashMap<>();
        int i = 0; 
        for (String col : toColumns(rows)) {
            long zeros = Stream.of(col.split("")).filter(c -> c.equals("0")).count();;
            long ones = Stream.of(col.split("")).filter(c -> c.equals("1")).count();;

            mostCommon.put(i, zeros > ones ? '0' : '1');
            i++;
        }
        return mostCommon;
    }

    static List<String> toColumns(List<String> rows) {
        List<String> columns = Arrays.asList(new String[rows.get(0).length()]);
        Collections.fill(columns, "");

        for (String row : rows) {
            for (int i = 0; i < row.length(); i++) {
                columns.set(i, columns.get(i) + row.charAt(i));
            }
        }
        return columns;
    }
}
