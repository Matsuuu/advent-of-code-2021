package matsu;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DayFour {

    public static void solve() {
        String input = Util.readFile("input4.txt");
        /*String input ="""
7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7

            """;*/

        String[] inputParts = input.split("\n\n");
        List<Integer> ballSeq = Stream.of(inputParts[0].split(",")).map(Integer::parseInt).toList();
        List<Board> boards = Stream.of(inputParts).skip(1).map(bs -> new Board(bs)).toList();

        for (int i = 0; i < ballSeq.size(); i++) {
            List<Integer> currentSeq = ballSeq.subList(0, i);
            Optional<Board> winningBoard = boards.stream().filter(b -> b.win(currentSeq)).findFirst();
            if (winningBoard.isPresent()) {
                int winningNumber = currentSeq.get(currentSeq.size() - 1);
                System.out.println("Winning number " + winningNumber);
                System.out.println("Final: " + winningNumber * winningBoard.get().addUncheckedNumbers(currentSeq));
                break;
            }
        }

        for (int i = ballSeq.size() - 1; i > 0; i--) {
            System.out.println(i);
            List<Integer> currentSeq = ballSeq.subList(0, i);
            Optional<Board> lastWinningboard = boards.stream().filter(b -> !b.win(currentSeq)).findFirst();
            if (lastWinningboard.isPresent()) {
                List<Integer> lastWinningSeq = ballSeq.subList(0, i + 1);
                int winningNumber = lastWinningSeq.get(lastWinningSeq.size() - 1);
                System.out.println("Winning number " + winningNumber);
                System.out.println("Final: " + winningNumber * lastWinningboard.get().addUncheckedNumbers(lastWinningSeq));
                break;
            }
        }
    }

    static class Board {
        public List<List<Integer>> rows;
        public List<List<Integer>> columns;

        public Board(String boardAsString) {
            rows = Stream.of(boardAsString.split("\n"))
                .map(row -> Stream.of(row.split(" "))
                        .filter(Predicate.not(String::isBlank))
                        .map(Integer::parseInt).toList())
                .toList();

            columns = IntStream.range(0, 5)
                .mapToObj(i -> getColumn(i))
                .toList();
        }

        List<Integer> getColumn(final int i) {
            return rows.stream().map(row -> row.get(i)).toList();
        }

        public boolean win(List<Integer> calledNumbers) {
            return rows.stream().anyMatch(row -> row.stream().allMatch(rowNum -> calledNumbers.contains(rowNum))) ||
                    columns.stream().anyMatch(col -> col.stream().allMatch(colNum -> calledNumbers.contains(colNum)));
        }

        public int addUncheckedNumbers(List<Integer> calledNumbers) {
            return rows.stream()
                .flatMap(List::stream)
                .filter(n -> !calledNumbers.contains(n))
                .mapToInt(Integer::valueOf)
                .sum();
        }

        @Override
        public String toString() {
            return "Board \n[columns\n " 
                + columns.stream().map(c -> c.toString() + "\n").toList() 
                + ", \n\nrows=\n " 
                + rows.stream().map(r -> r.toString() + "\n").toList() 
                + "]";
        }
    }
}
