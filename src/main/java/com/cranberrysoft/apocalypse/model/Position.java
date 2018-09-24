package com.cranberrysoft.apocalypse.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Mariusz Dubielecki
 */
public class Position {
    public static final Pattern POSITION_PATTERN = Pattern.compile("\\((\\d+),(\\d+)\\)");
    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static List<Position> from(String positions) {
        return Arrays.stream(positions.split("\\s+")).flatMap(
                position -> POSITION_PATTERN.matcher(position)
                        .results()
                        .map(group -> new Position(Integer.parseInt(group.group(1)), (Integer.parseInt(group.group(2)))
                        ))).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
