package matsu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DayOne {

    public static void solveFirst() {
        String input = Util.readFile("input1.txt");

        List<Long> inputList = Stream.of(input.split("\n")).map(Util::parseLong).toList();
        List<Pair> measures = new ArrayList<>();
        for (int i = 1; i < inputList.size(); i+=1) {
            measures.add(new Pair(inputList.get(i -1), inputList.get(i)));
        }

        long increaseCount = measures.stream().filter(hasIncreased).count();
        System.out.println(increaseCount);
    }

    public static void solveSecond() {  
        String input = Util.readFile("input1.txt");

        List<Long> inputList = Stream.of(input.split("\n")).map(Util::parseLong).toList();
        List<TriMeasurement> triMeasures = new ArrayList<>();
        for (int i = 0; i < inputList.size() - 2; i+=1) {
            triMeasures.add(new TriMeasurement(inputList.get(i), inputList.get(i+1), inputList.get(i+2)));
        }

        List<Pair> measurePairs = new ArrayList<>();
        for (int i = 1; i < triMeasures.size(); i+=1) {
            measurePairs.add(new Pair(
                        triMeasures.get(i - 1).combine(), 
                        triMeasures.get(i).combine()
            ));
        }

        long increaseCount = measurePairs.stream().filter(hasIncreased).count();
        System.out.println(increaseCount);
    }

    public record Pair(Long first, Long second) {}

    public record TriMeasurement(Long first, Long second, Long third) {
        public Long combine() {
            return first + second + third;
        }
    }

    public static Predicate<Pair> hasIncreased = (Pair pair) -> pair.second > pair.first;

}
