package chess.pieces;

//chess.pieces.Knight: Moves in an "L" shape: two squares in one direction and then one square perpendicular.

import chess.Board;
import chess.Box;
import chess.base.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {

    private List<String> validMoves = new ArrayList<>(Arrays.asList("-2-1", "-21", "-1-2", "1-2", "2-1", "21", "-12", "12"));

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        if (validMoves.contains(getMove(start, end))) {
            if (end.isBoxVacant()) return CAN_MOVE;
            else if (isSamePiece(start, end)) return CANNOT_MOVE;
            else return CAN_MOVE;
        } else {
            return CANNOT_MOVE;
        }
    }

    @Override
    public String getName() {
        return "N";
    }
}
