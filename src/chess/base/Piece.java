package chess.base;

import chess.Board;
import chess.Box;

abstract public class Piece {
    private boolean killed = false;
    private boolean white = false;
    private String name = "";
    public final boolean CAN_MOVE = true;
    public final boolean CANNOT_MOVE = false;

    public Piece(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isSamePiece(Box start, Box end) {
        return start.getPiece().isWhite() == end.getPiece().isWhite();
    }

    public String getMove(Box start,Box end){
        return (end.getRow() - start.getRow()) + "" + (end.getColumn() - start.getColumn());
    }

    public boolean isStartAndEndAreSame(Box start, Box end) {
        return (end.getRow() == start.getRow() && end.getColumn() == start.getColumn());
    }

    public abstract boolean canMove(Board board, Box start, Box end);

    public abstract String getName();
}
