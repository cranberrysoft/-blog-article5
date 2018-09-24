package com.cranberrysoft.apocalypse.impl;

import com.cranberrysoft.apocalypse.model.Move;
import com.cranberrysoft.apocalypse.model.Position;

import java.util.*;

/**
 * @author Mariusz Dubielecki
 */
public class GameParams {
    private final int dimensions;
    private final Position initialPosition;
    private final List<Position> creaturePositions;
    private final List<Move> moves;

    public GameParams(int dimensions, Position initialPosition, List<Position> creaturePositions, List<Move> moves) {
        this.dimensions = dimensions;
        this.initialPosition = initialPosition;
        this.creaturePositions = creaturePositions;
        this.moves = moves;
        validateGameParams();
    }

    public int getDimensions() {
        return dimensions;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public List<Position> getCreaturePositions() {
        return creaturePositions;
    }

    public List<Move> getMoves() {
        return moves;
    }

    private void validateGameParams(){
        final Set<Position> allPositions = new HashSet(creaturePositions);
        allPositions.add(initialPosition);
        final boolean isOutside = allPositions.stream()
                .anyMatch(position ->
                        position.getX() < 0 ||
                        position.getY() < 0 ||
                        position.getX() >= dimensions ||
                        position.getY() >= dimensions);
        if(isOutside){
            throw new IllegalArgumentException("One of the pawn is outside");
        }
    }
}
