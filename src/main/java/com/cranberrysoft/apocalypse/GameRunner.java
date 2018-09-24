package com.cranberrysoft.apocalypse;

import com.cranberrysoft.apocalypse.impl.GameParams;
import com.cranberrysoft.apocalypse.impl.AiloApocalypseGame;
import com.cranberrysoft.apocalypse.impl.utils.FileInputParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameRunner {

    public static final String DEFAULT_INPUT_FILE = "input.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {
        final GameParams gameParams = FileInputParser.parse(getFilePath(args));
        final Game game = new AiloApocalypseGame(gameParams);
        game.start();
        game.printResult(System.out);
    }

    private static Path getFilePath(String[] args) throws URISyntaxException {
        if (args.length > 0) {
            return Paths.get(args[0]);
        } else {
            return Paths.get(GameRunner.class.getClassLoader().getResource(DEFAULT_INPUT_FILE).toURI());
        }
    }
}
