package chess;

import chess.base.Piece;

public class Box {

    private Piece piece;
    private int row;
    private int column;

    public Box(int row, int column, Piece piece) {
        this.setPiece(piece);
        this.setRow(row);
        this.setColumn(column);
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isBoxVacant() {
        return this.getPiece() == null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
