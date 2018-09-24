package com.cranberrysoft.apocalypse;

import java.io.PrintStream;

/**
 * @author Mariusz Dubielecki
 */
public interface Game {

    /**
     * Start the game
     */
    void start();

    /**
     * Print the result to defined output
     * @return
     */
    void printResult(PrintStream printStream);

    /**
     * Return score
     * @return
     */
    long getScore();

}
