package com.cranberrysoft.apocalypse;

import com.cranberrysoft.apocalypse.impl.GameParams;
import com.cranberrysoft.apocalypse.impl.AiloApocalypseGame;
import com.cranberrysoft.apocalypse.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static com.cranberrysoft.apocalypse.model.Move.*;

/**
 * @author Mariusz Dubielecki
 */
public class AiloApocalypseGameTest {

    @Test
    void shouldScoreOnePointAndTransformCreatureToAilo() {
        Position ailoInitialPosition = new Position(1, 1);
        Position creaturePosition = new Position(2, 1);

        GameParams gameParams = new GameParams(4, ailoInitialPosition, List.of(creaturePosition), List.of(R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(1, game.getScore(), "Should transform one creature to ailo");
        assertEquals(List.of(new Position(2, 1), new Position(3, 1)), game.getAllAiloPositions());
    }


    @Test
    void shouldTwoPointsByDifferentAilos() {
        Position ailoInitialPosition = new Position(1, 1);
        List<Position> creaturePositions = List.of(new Position(2, 1), new Position(3, 1));
        GameParams gameParams = new GameParams(4, ailoInitialPosition, creaturePositions, List.of(R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(2, game.getScore());
        assertEquals(List.of(new Position(2, 1), new Position(3, 1), new Position(0, 1)), game.getAllAiloPositions());
    }


    @Test
    void shouldReturnTheSameResultAsProvidedExample() {
        Position ailoInitialPosition = new Position(2, 1);
        List<Position> creaturePositions = List.of(new Position(0, 1), new Position(1, 2), new Position(3, 1));
        GameParams gameParams = new GameParams(4, ailoInitialPosition, creaturePositions, List.of(D, L, U, U, R, R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(3, game.getScore());
        assertEquals(List.of(new Position(3, 0), new Position(2, 1), new Position(1, 0), new Position(0, 0)), game.getAllAiloPositions());
    }

    @Test
    void shouldPrintResultInDefinedFormat() throws UnsupportedEncodingException {
        Position ailoInitialPosition = new Position(1, 1);
        Position creaturePosition = new Position(2, 1);

        GameParams gameParams = new GameParams(4, ailoInitialPosition, List.of(creaturePosition), List.of(R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for aio

        game.start();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PrintStream printStream = new PrintStream(byteArrayOutputStream, true, "UTF-8")) {
            game.printResult(printStream);
            assertEquals(String.format("ailos score: 1%nailos positions:%n(2,1) (3,1)%n"), byteArrayOutputStream.toString(StandardCharsets.UTF_8));
        }

    }

    @Test
    void shouldOutputTheSameResultWhenTheGameIsStartedAgain() {
        Position ailoInitialPosition = new Position(1, 1);
        Position creaturePosition = new Position(2, 1);

        GameParams gameParams = new GameParams(4, ailoInitialPosition, List.of(creaturePosition), List.of(R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(1, game.getScore(), "Should transform one creature to ailo");
        assertEquals(List.of(new Position(2, 1), new Position(3, 1)), game.getAllAiloPositions());

        game.start();
        assertEquals(1, game.getScore(), "Should transform one creature to ailo");
        assertEquals(List.of(new Position(2, 1), new Position(3, 1)), game.getAllAiloPositions());
    }


    @Test
    void shouldScoreZeroPointsWhenThereAreNoCreaturesAndReturnOneAilo() {
        Position ailoInitialPosition = new Position(1, 1);

        GameParams gameParams = new GameParams(4, ailoInitialPosition, Collections.emptyList(), List.of(L));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(0, game.getScore(), "Should not transform any creature to ailo");
        assertEquals(List.of(new Position(0, 1)), game.getAllAiloPositions());
    }

    @Test
    void shouldInfectTwoCreaturesOnTheSamePosition() {
        Position ailoInitialPosition = new Position(0, 0);
        List<Position> creaturePositions = List.of(new Position(1, 0), new Position(1, 0));

        GameParams gameParams = new GameParams(4, ailoInitialPosition, creaturePositions, List.of(R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(2, game.getScore(), "Should transform two creatures to ailo");
        assertEquals(List.of(new Position(1, 0),new Position(2, 0),new Position(2, 0)), game.getAllAiloPositions());
    }

    @Test
    void shouldInfectOneCreatureForOneDimensionBoard() {
        Position ailoInitialPosition = new Position(0, 0);
        List<Position> creaturePositions = List.of(new Position(0, 0));

        GameParams gameParams = new GameParams(1, ailoInitialPosition, creaturePositions, List.of(R));
        AiloApocalypseGame game = new AiloApocalypseGame(gameParams); //moves for ailo

        game.start();
        assertEquals(1, game.getScore(), "Should transform two creatures to ailo");
        assertEquals(List.of(new Position(0, 0),new Position(0, 0)), game.getAllAiloPositions());
    }

}
