package com.cranberrysoft.apocalypse.model;

import static com.cranberrysoft.apocalypse.model.Move.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Mariusz Dubielecki
 */
public class Board {
    private final Field[][] fields;
    private final int dimensions;

    private final BiFunction<Position, Integer, Position> turnRight =
            (position, dimension) ->
                    position.getX() == dimension -1 ?
                            new Position(0, position.getY()):
                            new Position(Integer.sum(position.getX(), 1), position.getY());

    private final BiFunction<Position, Integer, Position> turnLeft =
            (position, dimension) ->
                    position.getX() == 0 ?
                            new Position(dimension-1, position.getY()):
                            new Position(Integer.sum(position.getX(), -1), position.getY());

    private final BiFunction<Position, Integer, Position> turnDown =
            (position, dimension) ->
                    position.getY() == dimension -1 ?
                            new Position(position.getX(), 0):
                            new Position(position.getX(), Integer.sum(position.getY(), 1));

    private final BiFunction<Position, Integer, Position> turnUp =
            (position, dimension) ->
                    position.getY() == 0 ?
                            new Position(position.getX(), dimension-1):
                            new Position(position.getX(), Integer.sum(position.getY(), -1));


    private final Map<Move, BiFunction<Position, Integer, Position>> moves = Map.of(
            L, turnLeft,
            R, turnRight,
            U, turnUp,
            D, turnDown
    );

    public Board(int dimensions) {
        fields = new Field[dimensions][dimensions]; //init board

        for(Field[] field: fields ){
            Arrays.setAll(field, notUsed -> new Field());
        }

        this.dimensions = dimensions;
    }


    public Position movePawn(Pawn pawn, Move move) {
        final Position pawnPosition = pawn.getPosition();
        final Position newPosition = moves.get(move).apply(pawnPosition, dimensions);

        //We are mowing a pawn
        fields[pawnPosition.getY()][pawnPosition.getX()].removePawn(pawn); //Removing pawn from the previous field
        pawn.setPosition(newPosition); //updating position for the pawn
        addPawn(pawn); //adding pawn to the board
        return newPosition;
    }


    public List<Pawn> getPawns(Position position){
        return fields[position.getY()][position.getX()].getPawns();
    }

    public void addPawn(Pawn pawn) {
        final Position pawnPosition = pawn.getPosition();
        fields[pawnPosition.getY()][pawnPosition.getX()].addPawn(pawn);
    }

}
