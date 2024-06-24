package chess;

import chess.base.Piece;
import chess.pieces.*;
import chess.utils.Colors;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int row = 8;
    public int column = 8;
    public Box[][] boxes = new Box[8][8];


    public Box getBox(int row, int column) throws Exception {
        if (row < 0 || row > 8 || column < 0 || column > 8) {
            throw new Exception("Index out of bound");
        }
        return boxes[row][column];
    }


    public  List<Piece> killedList = new ArrayList<>();

    public  void makeMove(int toRow, int toColumn, Board board, Box box) {

        int oldRow = box.getRow();
        int oldColum = box.getColumn();
        Box start = box;
        Box end = board.boxes[toRow][toColumn];
        try {
            if (start.isBoxVacant()) {
                System.out.println("Illegal No Piece picked : " + start.getRow() + start.getColumn());
                return;
            }

            if (!box.getPiece().canMove(board, start, end)) {
                System.out.println("Illegal move : " + end.getRow() + end.getColumn());
                return;
            }

            end.setRow(toRow);
            end.setColumn(toColumn);
            if (end.getPiece() != null) {
                Piece piece = end.getPiece();
                piece.setKilled(true);
                killedList.add(piece);
            }
            end.setPiece(start.getPiece());
            //System.out.println(box.getPiece().getName());
            if (end.getPiece() instanceof Pawn) {
                Pawn pawn = (Pawn) end.getPiece();
                pawn.setInitialMove(false);
            }
            board.boxes[toRow][toColumn] = end;
            board.boxes[oldRow][oldColum].setPiece(null);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Illegal move : " + end.getRow() + end.getColumn());
        }
    }

    public  void display(Board board) {
        try {
            for (int r = 0; r < board.row; r++) {
                for (int c = 0; c < board.column; c++) {
                    Piece piece = board.boxes[r][c].getPiece();
                    if ((r + c) % 2 == 0) {
                        if (piece != null) {
                            System.out.print(Colors.WHITE_BG + (piece.isWhite() ? "" : Colors.GRAY_TEXT) + "\t" + piece.getName() + "\t" + Colors.RESET);
                        } else {
                            System.out.print(Colors.WHITE_BG + Colors.YELLOW_TEXT + "\t" + r + "" + c + "\t" + Colors.RESET);
                        }
                    } else {
                        if (piece != null) {
                            System.out.print(Colors.BLACK_BG + (piece.isWhite() ? "" : Colors.GRAY_TEXT) + "\t" + piece.getName() + "\t" + Colors.RESET);
                        } else {
                            System.out.print(Colors.BLACK_BG + Colors.YELLOW_TEXT + "\t" + r + "" + c + "\t" + Colors.RESET);
                        }
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void initialPoint() {
        // initialize white pieces

        Box box10 = new Box(1, 0, new Pawn(false));
        Box box11 = new Box(1, 1, new Pawn(false));
        Box box12 = new Box(1, 2, new Pawn(false));
        Box box13 = new Box(1, 3, new Pawn(false));
        Box box14 = new Box(1, 4, new Pawn(false));
        Box box15 = new Box(1, 5, new Pawn(false));
        Box box16 = new Box(1, 6, new Pawn(false));
        Box box17 = new Box(1, 7, new Pawn(false));


        // White Pawns
        Box box60 = new Box(6, 0, new Pawn(true));
        Box box61 = new Box(6, 1, new Pawn(true));
        Box box62 = new Box(6, 2, new Pawn(true));
        Box box63 = new Box(6, 3, new Pawn(true));
        Box box64 = new Box(6, 4, new Pawn(true));
        Box box65 = new Box(6, 5, new Pawn(true));
        Box box66 = new Box(6, 6, new Pawn(true));
        Box box67 = new Box(6, 7, new Pawn(true));


        // White Rook,chess.pieces.Knight,chess.pieces.King,Queen,Bishop
        Box box70 = new Box(7, 0, new Rook(true));
        Box box71 = new Box(7, 1, new Knight(true));
        Box box72 = new Box(7, 2, new Bishop(true));
        Box box73 = new Box(7, 3, new Queen(true));
        Box box74 = new Box(7, 4, new King(true));
        Box box75 = new Box(7, 5, new Bishop(true));
        Box box76 = new Box(7, 6, new Knight(true));
        Box box77 = new Box(7, 7, new Rook(true));


        // Black Rook,chess.pieces.Knight,chess.pieces.King,Queen,Bishop
        Box box00 = new Box(0, 0, new Rook(false));
        Box box01 = new Box(0, 1, new Knight(false));
        Box box02 = new Box(0, 2, new Bishop(false));
        Box box03 = new Box(0, 3, new Queen(false));
        Box box04 = new Box(0, 4, new King(false));
        Box box05 = new Box(0, 5, new Bishop(false));
        Box box06 = new Box(0, 6, new Knight(false));
        Box box07 = new Box(0, 7, new Rook(false));


        boxes[0][0] = box00;
        boxes[0][1] = box01;
        boxes[0][2] = box02;
        boxes[0][3] = box03;
        boxes[0][4] = box04;
        boxes[0][5] = box05;
        boxes[0][6] = box06;
        boxes[0][7] = box07;

        //...
        boxes[1][0] = box10;
        boxes[1][1] = box11;
        boxes[1][2] = box12;
        boxes[1][3] = box13;
        boxes[1][4] = box14;
        boxes[1][5] = box15;
        boxes[1][6] = box16;
        boxes[1][7] = box17;
        //...

        // initialize white pieces
        boxes[7][0] = box70;
        boxes[7][1] = box71;
        boxes[7][2] = box72;
        boxes[7][3] = box73;
        boxes[7][4] = box74;
        boxes[7][5] = box75;
        boxes[7][6] = box76;
        boxes[7][7] = box77;

        boxes[6][0] = box60;
        boxes[6][1] = box61;
        boxes[6][2] = box62;
        boxes[6][3] = box63;
        boxes[6][4] = box64;
        boxes[6][5] = box65;
        boxes[6][6] = box66;
        boxes[6][7] = box67;


        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                if (boxes[r][c] == null) {
                    boxes[r][c] = new Box(r, c, null);
                }
            }
        }
    }
}
