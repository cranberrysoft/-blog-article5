package com.cranberrysoft.apocalypse;

import com.cranberrysoft.apocalypse.model.Board;
import com.cranberrysoft.apocalypse.model.Move;
import com.cranberrysoft.apocalypse.model.Pawn;
import com.cranberrysoft.apocalypse.model.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mariusz Dubielecki
 */
public class BoardTest {

    @Test
    void shouldMovePawnToTheRight() {
        Pawn initPawn = new Pawn(new Position(0, 0));
        Board board = new Board(4);
        board.addPawn(initPawn);
        board.movePawn(initPawn, Move.R);

        List<Pawn> pawn = board.getPawns(new Position(1, 0));

        assertEquals(List.of(initPawn), pawn, "Should return the initial pawn");
    }


    @Test
    void shouldMovePawnToDown() {
        Pawn initPawn = new Pawn(new Position(0, 0));
        Board board = new Board(4);
        board.addPawn(initPawn);

        board.movePawn(initPawn, Move.D);

        List<Pawn> pawn = board.getPawns(new Position(0, 1));

        assertEquals(List.of(initPawn), pawn, "Should return the initial pawn");
    }

    @Test
    void shouldMovePawnToUp() {
        Pawn initPawn = new Pawn(new Position(0, 0));
        Board board = new Board(4);
        board.addPawn(initPawn);


        board.movePawn(initPawn, Move.U);

        List<Pawn> pawn = board.getPawns(new Position(0, 3));

        assertEquals(List.of(initPawn), pawn, "Should return the initial pawn");
    }


    @Test
    void shouldMovePawnToUpFromLeftDownCorner() {
        Pawn initPawn = new Pawn(new Position(0, 0));
        Board board = new Board(4);
        board.addPawn(initPawn);


        board.movePawn(initPawn, Move.U);

        List<Pawn> pawn = board.getPawns(new Position(0, 3));

        assertEquals(List.of(initPawn), pawn, "Should return the initial pawn");
    }

    @Test
    void shouldMovePawnToLeft() {
        Pawn initPawn = new Pawn(new Position(0, 0));
        Board board = new Board(4);
        board.addPawn(initPawn);

        board.movePawn(initPawn, Move.L);

        List<Pawn> pawn = board.getPawns(new Position(3, 0));

        assertEquals(List.of(initPawn), pawn, "Should return the initial pawn");
    }

    @Test
    void shouldStoreTwoPawnsOneTheSamePosition() {
        Pawn initPawn = new Pawn(new Position(0, 0));
        Board board = new Board(4);
        board.addPawn(initPawn);

        Pawn secondPawn = new Pawn(new Position(0, 0));

        board.addPawn(secondPawn);

        List<Pawn> pawns = board.getPawns(new Position(0, 0));

        assertEquals(List.of(initPawn, secondPawn), pawns, "Should return pawns on the same position");
    }

}
