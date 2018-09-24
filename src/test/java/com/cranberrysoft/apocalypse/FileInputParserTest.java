package com.cranberrysoft.apocalypse;

import com.cranberrysoft.apocalypse.impl.GameParams;
import com.cranberrysoft.apocalypse.impl.utils.FileInputParser;
import com.cranberrysoft.apocalypse.model.Position;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mariusz Dubielecki
 */
public class FileInputParserTest {

    @Test
    public void shouldParseCorrectInputFile() {
        try {
            GameParams gameParams = FileInputParser.parse(Paths.get(ClassLoader.getSystemResource("input.txt").toURI()));
            assertEquals(4, gameParams.getDimensions());
            assertEquals(new Position(2, 1), gameParams.getInitialPosition());
            assertEquals(List.of(new Position(0, 1), new Position(1, 2), new Position(3, 1)),
                    gameParams.getCreaturePositions());
            assertEquals(4, gameParams.getDimensions());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void shouldThrowAnExceptionWhenGetCorruptedInputFile() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            GameParams gameParams = FileInputParser.parse(Paths.get(ClassLoader.getSystemResource("corrupted_input.txt").toURI()));
        });
        assertEquals("Wrong format from input file. Please look for readme", exception.getMessage());
    }

}
