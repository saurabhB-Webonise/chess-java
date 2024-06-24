import chess.Board;
import chess.Box;
import chess.base.Piece;
import chess.pieces.Pawn;
import chess.utils.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Piece> killedList = new ArrayList<>();

    public static void makeMove(int toRow, int toColumn, Board board, Box box) {

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

    public static void display(Board board) {
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


    public static void main(String[] args) {


        Board board = new Board();
        board.initialPoint();
        display(board);
        System.out.println("After Move");
        boolean isloopRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isloopRunning) {
            System.out.println("To Exit  Press q");
            int toRow = 0;
            int toColum = 0;
            int fromRow = 0;
            int fromColumn = 0;
            try {
                System.out.println("Enter index of start piece");
                String fromIndex = scanner.nextLine();
                if (fromIndex.equals("q")) {
                    isloopRunning = false;
                    break;
                }
                fromRow = Integer.parseInt(fromIndex.charAt(0) + "");
                fromColumn = Integer.parseInt(fromIndex.charAt(1) + "");
                System.out.println("Enter index of end box ");
                String toIndex = scanner.nextLine();

                if (toIndex.equals("q")) {
                    isloopRunning = false;
                    break;
                }
                toRow = Integer.parseInt(toIndex.charAt(0) + "");
                toColum = Integer.parseInt(toIndex.charAt(1) + "");

            } catch (Exception e) {


            }

            if (isloopRunning) {
                try {
                    makeMove(toRow, toColum, board, board.getBox(fromRow, fromColumn));
                    display(board);
                    for (Piece piece : killedList) {
                        System.out.println("piece name: " + piece.getClass().getName() + " | isKilled: " + piece.isKilled() + " | isWhite: " + piece.isWhite());
                    }
                } catch (Exception e) {
                    System.out.println("Index out of bound :" + fromRow + "" + fromColumn);
                }
            }
        }
    }
}



