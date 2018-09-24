package com.cranberrysoft.apocalypse.impl;

import com.cranberrysoft.apocalypse.Game;
import com.cranberrysoft.apocalypse.model.Board;
import com.cranberrysoft.apocalypse.model.Move;
import com.cranberrysoft.apocalypse.model.Pawn;
import com.cranberrysoft.apocalypse.model.Position;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * @author Mariusz Dubielecki
 */
public class AiloApocalypseGame implements Game {
    public static final String AILO = "ailo";
    public static final String CREATURE = "creature";

    private final GameResult gameResult = new GameResult();

    private final Board board;
    private final List<Move> moves;
    private final Position ailoPosition;
    private final List<Position> creaturesPositions;


    public AiloApocalypseGame(GameParams gameParams) {
        board = new Board(gameParams.getDimensions());
        this.moves = Collections.unmodifiableList(gameParams.getMoves());
        this.ailoPosition = gameParams.getInitialPosition();
        this.creaturesPositions = Collections.unmodifiableList(gameParams.getCreaturePositions());
    }

    @Override
    public void start() {
        final Pawn initAilo = initBoard(ailoPosition, creaturesPositions);
        gameResult.addAilo(initAilo);

        doMovesForAilo(List.of(initAilo));
    }

    @Override
    public void printResult(PrintStream printStream) {
        printStream.print(gameResult);
    }

    @Override
    public long getScore() {
        return gameResult.getScore();
    }

    public List<Position> getAllAiloPositions(){
        return gameResult.getAilos().stream().map(Pawn::getPosition).collect(toList());
    }

    private Pawn initBoard(Position ailoPosition, List<Position> creaturesPositions) {
        //Clear internal state for the game
        gameResult.clear();

        //Init board
        creaturesPositions.stream().map(position -> new Pawn(position, CREATURE)).forEach(board::addPawn);
        return new Pawn(ailoPosition, AILO);
    }

    private List<Pawn> doMovesForAilo(List<Pawn> ailos){
        if(ailos.isEmpty()){
            return Collections.emptyList();
        }
        return doMovesForAilo(ailos.stream().map(this::doMovesForAilo).flatMap(List::stream).collect(toList()));
    }

    private List<Pawn> doMovesForAilo(Pawn ailo){
        final List<Pawn> ailos = new ArrayList<>();
        for (Move move : moves) {
            //move ailo
            final Position position = board.movePawn(ailo, move);

            //infect creatures
            final List<Pawn> infectedCreatures = infectCreatures(position);
            ailos.addAll(infectedCreatures);
        }
        return ailos;
    }

    private List<Pawn> infectCreatures(Position position) {
        //Get all creatures on the ailo position and infect them
        final List<Pawn> ailos =
                board.getPawns(position).stream()
                        .filter(pawn -> CREATURE.equals(pawn.getLabel()))
                        .map(turnIntoAilo).collect(toList());

        ailos.forEach(gameResult::addAilo);
        return ailos;
    }

    private Function<Pawn, Pawn> turnIntoAilo = creature -> {
        creature.setLabel(AILO);
        return creature;
    };
}
