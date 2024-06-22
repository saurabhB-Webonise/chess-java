package chess.pieces;

import chess.Board;
import chess.Box;
import chess.base.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Pawn: Moves forward one square, two squares from the initial position. Captures diagonally.
public class Pawn extends Piece {

    private boolean initialMove = true;


    List<String> initialVerticalValidMoves = new ArrayList<>(Arrays.asList("-10", "-20"));
    List<String> verticalValidMoves = new ArrayList<>(Arrays.asList("-10"));
    List<String> diagonalValidMoves = new ArrayList<>(Arrays.asList("-1-1", "-11"));

    public boolean isInitialMove() {
        return initialMove;
    }

    public void setInitialMove(boolean initialMove) {
        this.initialMove = initialMove;
    }

    public Pawn(boolean white) {
        super(white);
    }


    boolean isPathClear(Board board, Box start, Box end) {
        boolean flag = true;
        int initial = Math.min(start.getRow(), end.getRow());
        int destiny = Math.max(start.getRow(), end.getRow());
        for (int i = initial + 1; i < destiny; i++) {
            if (board.boxes[i][start.getColumn()].getPiece() != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean canMove(Board board, Box start, Box end) {
        String str = "";
        if (start.getPiece().isWhite()) str = getMove(start, end);
        else str = getMove(end, start);

        boolean isDiagonalMove = diagonalValidMoves.contains(str);
        boolean isNull = end.getPiece() == null;

        if (initialMove) {
            boolean isInitialVerticalValidMove = initialVerticalValidMoves.contains(str);
            if (!isPathClear(board, start, end)) return CANNOT_MOVE;

            if (isNull) {
                return !isDiagonalMove && isInitialVerticalValidMove;
            } else {
                return isDiagonalMove && !(end.getPiece().isWhite() == start.getPiece().isWhite());
            }
        } else {
            boolean isVerticalValidMove = verticalValidMoves.contains(str);
            if (isNull) {
                return !isDiagonalMove && isVerticalValidMove;
            } else {
                return isDiagonalMove && !(end.getPiece().isWhite() == start.getPiece().isWhite());
            }
        }
    }

    @Override
    public String getName() {
        return "P";
    }
}
