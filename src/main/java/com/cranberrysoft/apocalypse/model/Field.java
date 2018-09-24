package com.cranberrysoft.apocalypse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mariusz Dubielecki
 */
class Field {
    public static final int INITIAL_FIELD_CAPACITY = 5;

    private final List<Pawn> pawns = new ArrayList<>(INITIAL_FIELD_CAPACITY);

    public List<Pawn> getPawns() {
        return Collections.unmodifiableList(pawns);
    }

    void addPawn(Pawn pawn) {
        pawns.add(pawn);
    }

    void removePawn(Pawn pawn) {
        pawns.remove(pawn);
    }
}
