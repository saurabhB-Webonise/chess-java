package chess.pieces;
//chess.pieces.King: Moves one square in any direction.

import chess.Board;
import chess.Box;
import chess.base.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {

    private List<String> validMoves = new ArrayList<>(Arrays.asList("-1-1", "-10", "-11", "0-1", "01", "1-1", "10", "11"));

    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        if (validMoves.contains(getMove(start, end)) && (end.isBoxVacant() || !isSamePiece(start, end)))
            return CAN_MOVE;
        return CANNOT_MOVE;
    }

    @Override
    public String getName() {
        return "K";
    }
}
