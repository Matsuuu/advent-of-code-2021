package matsu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DayOneGolf {

    public record Tally(Long m, Long inc) { }

    public static void solveFirst() {
        String input = Util.readFile("input1.txt");

        Long inc = Stream.of(input.split("\n"))
                .map(Util::parseLong)
                .reduce(new Tally(Long.MAX_VALUE,0L), (tally, measure)
                -> new Tally(measure, measure > tally.m ? tally.inc + 1 : tally.inc),
                        (t1, t2) -> t2).inc;
        System.out.println(inc);
    }
}
