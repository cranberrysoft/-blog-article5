package com.cranberrysoft.apocalypse.impl;

import com.cranberrysoft.apocalypse.model.Pawn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mariusz Dubielecki
 */
class GameResult {

    private final List<Pawn> ailos = new ArrayList<>();

    public void addAilo(Pawn ailo) {
        ailos.add(ailo);
    }

    public long getScore() {
        return Math.abs(ailos.size() - 1);
    }

    public List<Pawn> getAilos() {
        return Collections.unmodifiableList(ailos);
    }

    public void clear() {
        ailos.clear();
    }

    @Override
    public String toString() {
        return String.format("ailos score: %d%nailos positions:%n%s%n",
                getScore(),
                ailos.stream()
                        .map(Pawn::getPosition)
                        .map(Object::toString).collect(Collectors.reducing((a, b) -> a + " " + b)).orElse(""));
    }
}
