package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    // x, y
    // 0, 0 => A8
    // 0, 1 => A7
    // 0, 2 => A6
    // 0, 3 => A5
    // 0, 4 => A4
    // 0, 5 => A3
    // 0, 6 => A2
    // 0, 7 => A1

    public HashMap<String, List<Integer>> coordenates = new HashMap<>();

    public Board() {
        for (int column = 8; column > 0; column--) {
            int index = 0;
            for (char file = 'A'; file <= 'H'; file++) {
                String cord = String.valueOf(file) + column;
                this.coordenates.put(cord, Arrays.asList(8 - column, index));
                index++;
            }
        }
        this.coordenates.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

    }

    public static void main(String[] args) {
        Board board = new Board();
        for (int column = 8; column > 0; column--) {
            int index = 0;
            for (char file = 'A'; file <= 'H'; file++) {
                String cord = String.valueOf(file) + column;
                board.coordenates.put(cord, Arrays.asList(8 - column, index));
                index++;
            }
        }
        board.coordenates.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);

    }

}
