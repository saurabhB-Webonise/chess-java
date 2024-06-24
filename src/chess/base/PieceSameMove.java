package chess.base;

import chess.Board;
import chess.Box;

import java.util.List;

public abstract class PieceSameMove extends Piece {

    public PieceSameMove(boolean white) {
        super(white);
    }

    private boolean isVerticalMove(Box start, Box end) {
        return (end.getColumn() - start.getColumn()) == 0;
    }

    private boolean isHorizontalMove(Box start, Box end) {
        return (end.getRow() - start.getRow()) == 0;
    }

    private boolean isValidMove(Box start, Box end) {
        return isVerticalMove(start, end) || isHorizontalMove(start, end);
    }

    private boolean isPathClear(Board board, Box start, Box end) {
        boolean isClear = true;
        if (isVerticalMove(start, end)) {
            int initial = Math.min(start.getRow(), end.getRow());
            int destiny = Math.max(start.getRow(), end.getRow());
            for (int i = initial + 1; i < destiny; i++) {
                if (board.boxes[i][start.getColumn()].getPiece() != null) {
                    isClear = false;
                    break;
                }
            }
            return isClear;
        }
        if (isHorizontalMove(start, end)) {
            int initial = Math.min(start.getColumn(), end.getColumn());
            int destiny = Math.max(start.getColumn(), end.getColumn());
            for (int i = initial + 1; i < destiny; i++) {
                if (board.boxes[start.getRow()][i].getPiece() != null) {
                    isClear = false;
                    break;
                }
            }
            return isClear;
        }
        return isClear;
    }


   private boolean isPathClear2(Board board, Box start, Box end) {

        boolean f1 = (end.getRow() - start.getRow() < 0);
        boolean isClear = true;

        int row = end.getRow();
        int column = end.getColumn();
        while ((f1 ? (row < start.getRow()) : (row > start.getRow()))) {

            if (column > start.getColumn())
                column--;
            else
                column++;

            if (f1) {
                row++;
            } else {
                row--;
            }

            if (start.getRow() != row && start.getColumn() != column) {
                if (!board.boxes[row][column].isBoxVacant()) {
                    isClear = false;
                    break;
                }
            }
        }
        return isClear;
    }

    public boolean rookMoves(Board board, Box start, Box end) {
        if (isStartAndEndAreSame(start, end)) return CANNOT_MOVE;
        if (isValidMove(start, end)) {
            if (!isPathClear(board, start, end)) return CANNOT_MOVE;
            if (end.isBoxVacant()) return CAN_MOVE;
            if (isSamePiece(start, end)) return CANNOT_MOVE;
            return CAN_MOVE;
        }
        return CANNOT_MOVE;
    }

    public boolean bishopMoves(List<String> validMoves, Board board, Box start, Box end) {
        if (isStartAndEndAreSame(start, end)) return CANNOT_MOVE;
        if (validMoves.contains(getMove(start, end))) {
            if (!isPathClear2(board, start, end)) return CANNOT_MOVE;
            if (end.isBoxVacant()) return CAN_MOVE;
            if (isSamePiece(start, end)) return CANNOT_MOVE;
            return CAN_MOVE;
        }
        return CANNOT_MOVE;
    }
}
