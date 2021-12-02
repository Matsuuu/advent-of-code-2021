package matsu;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayTwo {

    public static void solveFirst() {
        String input = Util.readFile("input2.txt");

        List<NavAction> navActions = Stream.of(input.split("\n"))
                .map(row -> row.split(" "))
                .map(toNavAction)
                .toList();

        Long horiz = navActions.stream().filter(isForward).mapToLong(NavAction::amount).sum();

        Long depth = navActions.stream().filter(Predicate.not(isForward)).mapToLong(getDepthChange).sum();

        Long realDepth = navActions.stream().reduce(new NavData(0L, 0L),
                (op, next) ->
                        isForward.test(next)
                                ? new NavData(op.aim, op.depth + op.aim * next.amount)
                                : new NavData(op.aim + getDepthChange.applyAsLong(next), op.depth)
                ,
                (op1, op2) -> op1).depth;
        System.out.println("Horizontal: " + horiz);
        System.out.println("Depth: " + depth);
        System.out.println(horiz * depth);

        System.out.println("Real Depth: " + realDepth);
        System.out.println(horiz * realDepth);
    }

    record NavAction(String dir, Long amount) {}

    record NavData(Long aim, Long depth) {}

    static Predicate<NavAction> isForward = navAction -> navAction.dir.equals("forward");

    static ToLongFunction<NavAction> getDepthChange = navAction -> navAction.dir.equals("up") ? navAction.amount * -1 : navAction.amount;

    static Function<String[], NavAction> toNavAction = (rowSplit) -> new NavAction(rowSplit[0], Util.parseLong(rowSplit[1]));

}
