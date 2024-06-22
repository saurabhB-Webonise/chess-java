package chess.pieces;

import chess.Board;
import chess.Box;
import chess.base.PieceSameMove;

//Rook: Moves horizontally or vertically any number of squares.
public class Rook extends PieceSameMove {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        return rookMoves(board, start, end);
    }

    @Override
    public String getName() {
        return "R";
    }
}
