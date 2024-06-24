import chess.Board;
import chess.Box;
import chess.base.Piece;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {


        Board board = new Board();
        board.initialPoint();
        board.display(board);

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
                    board.makeMove(toRow, toColum, board, board.getBox(fromRow, fromColumn));
                    System.out.println("After Move");
                    board.display(board);
                    for (Piece piece : board.killedList) {
                        System.out.println("piece name: " + piece.getClass().getName() + " | isKilled: " + piece.isKilled() + " | isWhite: " + piece.isWhite());
                    }
                } catch (Exception e) {
                    System.out.println("Index out of bound :" + fromRow + "" + fromColumn);
                }
            }
        }
    }
}



