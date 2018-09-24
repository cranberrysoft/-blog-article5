package com.cranberrysoft.apocalypse;

import com.cranberrysoft.apocalypse.impl.GameParams;
import com.cranberrysoft.apocalypse.model.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.cranberrysoft.apocalypse.model.Move.*;

/**
 * @author Mariusz Dubielecki
 */
public class GameParamsTest {


    @Test
    void shouldCreateValidParams() {
        GameParams gameParams = new GameParams(5, new Position(1, 1), List.of(new Position(1, 1)), List.of(R));
        assertEquals(5, gameParams.getDimensions());
        assertEquals(new Position(1, 1), gameParams.getInitialPosition());
        assertEquals(List.of(new Position(1, 1)), gameParams.getCreaturePositions());
        assertEquals(List.of(R), gameParams.getMoves());

    }

    @Test
    void shouldThrowAnExceptionWhenInitialPawnIsOutside() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new GameParams(5, new Position(5,1), List.of(new Position(1,1)), List.of(R));
        });
        assertEquals("One of the pawn is outside", exception.getMessage());

    }


    @Test
    void shouldThrowAnExceptionWhenCreaturePawnIsOutside() {

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new GameParams(5, new Position(1,1), List.of(new Position(5,1)), List.of(R));
        });
        assertEquals("One of the pawn is outside", exception.getMessage());

    }

}
