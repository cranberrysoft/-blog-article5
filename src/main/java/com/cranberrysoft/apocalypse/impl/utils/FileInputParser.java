package com.cranberrysoft.apocalypse.impl.utils;

import com.cranberrysoft.apocalypse.impl.GameParams;
import com.cranberrysoft.apocalypse.model.Move;
import com.cranberrysoft.apocalypse.model.Position;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Mariusz Dubielecki
 */
public final class FileInputParser {

    private FileInputParser() {
    }

    public static GameParams parse(Path inputFile) throws IOException {
        final List<String> params = Files.readAllLines(inputFile);
        if (params.size() != 4) {
            throw new IllegalArgumentException("Wrong format from input file. Please look for readme");
        }
        final int dimension = Integer.parseInt(params.get(0));
        final Position initialPosition = Position.from(params.get(1)).get(0);
        final List<Position> creaturePositions = Position.from(params.get(2));
        final List<Move> moves = params.get(3).chars().mapToObj( c -> String.valueOf((char)c)).map(Move::valueOf).collect(toList());
        return new GameParams(dimension, initialPosition, creaturePositions, moves);
    }


}
