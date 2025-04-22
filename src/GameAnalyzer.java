import Chess.ChessPiece;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameAnalyzer {
    private ChessPiece[][] chessBoard = new ChessPiece[8][8];
    private final ArrayList<ChessPiece> blackPieces = new ArrayList<>();
    private final ArrayList<ChessPiece> whitePieces = new ArrayList<>();

    private float whiteScore = 0;
    private float blackScore = 0;

/*
* initializeBoard function works like the following
* 1- get and open the .txt file
* 2- parse the file and create necessary pieces
* 3- put them in the chessBoard
* 4- put null if square is empty
* */
    public void initializeBoard() {
        System.out.println("\nPlease put your files in the \"boards\" folder.\nIf you already have please write the file's name without the extension.\nExample:board1\n");

        String fileName = "";
        File file;
        Scanner sc = new Scanner(System.in);
        String filename = "boards\\";
        fileName = filename.concat(sc.nextLine().trim() + ".txt");

        file = new File(fileName);

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";

            for (int y = 7; y >= 0; y--) {
                line = bufferedReader.readLine();
                line = line.replaceAll(" ", ""); // remove the spaces for parsing
                int offset = 0;
                for (int x = 0; x < 8; x++) {
                    String s = line.substring(offset, offset + 2); // read 2 character at a time
                    if (s.equals("--")) {
                        chessBoard[y][x] = null; // y and x values are reversed to fit the chessboard format
                        offset += 2;
                        continue;
                    }
                    ChessPiece piece = new ChessPiece().createPiece(s);
                    String location = x + String.valueOf(y);
                    chessBoard[y][x] = piece;
                    piece.setPieceLocation(location);
                    offset += 2;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not open the file.");
            initializeBoard();
        } catch (IOException | StringIndexOutOfBoundsException e) {
            System.err.println("File is not correctly formatted.");
        }


    }
    /*
    * initializePlayers function works like the following
    * 1- parse through the chessBoard
    * 2- put the pieces to the correct teams list according to their colour
    * */

    public void initializePlayers() {
        ChessPiece bluePrint = new ChessPiece();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                ChessPiece square = chessBoard[x][y];
                if (square != null) {
                    if (square.getPieceColour() == ChessPiece.Colour.SIYAH) {

                        blackPieces.add(square);
                    } else if (square.getPieceColour() == ChessPiece.Colour.BEYAZ) {

                        whitePieces.add(square);
                    }
                }

            }
        }


    }

    /*
     * setAttacks function works like the following
     * 1- parse through both teams list
     * 2- call each pieces' attack function
     * */
    public void setAttacks() {
        for (ChessPiece piece : blackPieces) {
            piece.calculateAttackableSquares(chessBoard);
        }

        for (ChessPiece piece : whitePieces) {
            piece.calculateAttackableSquares(chessBoard);
        }
    }

    /*
     * calculateScores function works like the following
     * 1- parse through both teams list
     * 2- add each pieces' point to the according team's score
     * 3- print each pieces location and point
     * 4- print each teams total score
     * */
    public void calculateScores() {
        for (ChessPiece piece : blackPieces) {
            blackScore += piece.getPiecePoint();
               System.out.println(piece.getPieceColour()+" "+piece.getPieceType()+" "+piece.getPiecePoint()+" X: "+piece.getLocationX()+" Y: "+piece.getLocationY());
        }
        System.out.println("\nBLACK SCORE:\t" + blackScore + "\n");
        for (ChessPiece piece : whitePieces) {
            whiteScore += piece.getPiecePoint();
               System.out.println(piece.getPieceColour()+" "+piece.getPieceType()+" "+piece.getPiecePoint()+" X: "+piece.getLocationX()+" Y: "+piece.getLocationY());

        }
        System.out.println("\nWHITE SCORE:\t" + whiteScore);
    }

    public void analyze() {
        initializeBoard();
        initializePlayers();
        setAttacks();
        calculateScores();
    }

    public void printBoard() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                ChessPiece piece = chessBoard[y][x];
                if (piece == null) {
                    System.out.print("--");
                    continue;
                }
                System.out.print(piece.getPieceType());
            }
            System.out.println();
        }
    }

}

