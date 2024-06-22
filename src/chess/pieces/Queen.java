package chess.pieces;
//Queen: Moves horizontally, vertically, or diagonally any number of squares.

import chess.Board;
import chess.Box;
import chess.base.PieceSameMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends PieceSameMove {

    private final List validMoves = new ArrayList(Arrays.asList(
            "-11", "-22", "-33", "-44", "-55", "-66", "-77",
            "1-1", "2-2", "3-3", "4-4", "5-5", "6-6", "7-7",
            "11", "22", "33", "44", "55", "66", "77",
            "-1-1", "-2-2", "-3-3", "-4-4", "-5-5", "-6-6", "-7-7"));

    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        if (rookMoves(board, start, end)) return CAN_MOVE;
        if (bishopMoves(validMoves, board, start, end)) return CAN_MOVE;
        return CANNOT_MOVE;
    }

    @Override
    public String getName() {
        return "Q";
    }
}
