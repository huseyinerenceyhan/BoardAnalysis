package Chess;

public class ChessPiece {
    public enum Type {
        PIYON(1), AT(3), FIL(3), KALE(5), VEZIR(9), SAH(100);

        private float point;

        Type(float point) {
            this.point = point;
        }

    }

    public enum Colour {BEYAZ, SIYAH}

    private Colour pieceColour;

    private float piecePoint;

    private Type pieceType;

    private int locationX, locationY;

    private boolean pointHalved = false;


    public ChessPiece() {
    }

    public ChessPiece(Colour colour, Type type) {
        this.pieceColour = colour;
        this.pieceType = type;
        this.piecePoint = type.point;
    }

    public void calculateAttackableSquares(ChessPiece[][] board) {
    }

    public void attackMultipleCardinal(ChessPiece[][] board) {

        int x = this.getLocationX();
        int y = this.getLocationY();

        int offsetX = 1;
        int offsetY = 1;

        boolean checkedBothSides;

        while (true) {
            if (isInBounds(y + offsetY)) {
                ChessPiece piece = board[y + offsetY][x];

                if (piece != null) {
                    tryToHalvePiece(piece);
                    if (offsetY < 0) {
                        checkedBothSides = true;
                        break;
                    }
                    offsetY = -1;

                    continue;// to prevent double decrease

                }
                if (offsetY > 0) {
                    offsetY++; // If checking up squares increase offset
                } else {
                    offsetY--; // If checking down squares decrease offset
                }
            } else {
                if (offsetY < 0) {
                    checkedBothSides = true;
                    break;
                }
                offsetY = -1;
            }

        }


        while (true) {
            if (isInBounds(x + offsetX)) {
                ChessPiece piece = board[y][x + offsetX];
                if (piece != null) {
                    tryToHalvePiece(piece);
                    if (offsetX < 0) {
                        checkedBothSides = true;
                        break;
                    }
                    offsetX = -1;

                    continue;// to prevent double decrease

                }

                if (offsetX > 0) {
                    offsetX++; // If checking right squares increase offset
                } else {
                    offsetX--; // If checking left squares increase offset
                }
            } else {

                if (offsetX < 0) {
                    checkedBothSides = true;
                    break;
                }
                offsetX = -1;
            }

        }

    }


    public void attackMultipleDiagonal(ChessPiece[][] board) {
        int x = this.getLocationX();
        int y = this.getLocationY();

        symmetricDiagonal(x, y, board);
        asymmetricDiagonal(x, y, board);

    }

    public void symmetricDiagonal(int x, int y, ChessPiece[][] board) {
        int offsetX = 1;
        int offsetY = 1;

        while (isInBounds(x + offsetX) && isInBounds(y + offsetY)) {
            ChessPiece piece = board[y + offsetY][x + offsetX];
            if (piece != null) {
                tryToHalvePiece(piece);
                break;
            }
            offsetX++;
            offsetY++;
        }
        offsetX = 1;
        offsetY = 1;
        while (isInBounds(x - offsetX) && isInBounds(y - offsetY)) {
            ChessPiece piece = board[y - offsetY][x - offsetX];
            if (piece != null) {
                tryToHalvePiece(piece);
                break;
            }
            offsetX++;
            offsetY++;
        }
    }

    public void asymmetricDiagonal(int x, int y, ChessPiece[][] board) {
        int offsetX = 1;
        int offsetY = 1;

        while (isInBounds(x + offsetX) && isInBounds(y - offsetY)) {
            ChessPiece piece = board[y - offsetY][x + offsetX];
            if (piece != null) {
                tryToHalvePiece(piece);
                break;
            }
            offsetX++;
            offsetY++;
        }
        offsetX = 1;
        offsetY = 1;
        while (isInBounds(x - offsetX) && isInBounds(y + offsetY)) {
            ChessPiece piece = board[y + offsetY][x - offsetX];
            if (piece != null) {
                tryToHalvePiece(piece);
                break;
            }
            offsetX++;
            offsetY++;
        }
    }
/*
* createPiece method works like the Factory design pattern for the ChessPiece subclasses
* */
    public ChessPiece createPiece(String piece) {

        char type = piece.charAt(0);
        char colour = piece.charAt(1);

        switch (colour) {
            case 's':
                this.pieceColour = Colour.SIYAH;
                break;
            case 'b':
                this.pieceColour = Colour.BEYAZ;
                break;
        }

        return switch (type) {
            case 'p' -> new Piyon(pieceColour, Type.PIYON);
            case 'a' -> new At(pieceColour, Type.AT);
            case 'f' -> new Fil(pieceColour, Type.FIL);
            case 'k' -> new Kale(pieceColour, Type.KALE);
            case 'v' -> new Vezir(pieceColour, Type.VEZIR);
            case 's' -> new Sah(pieceColour, Type.SAH);
            default -> new ChessPiece();
        };

    }
    /*
     * tryToHalvePiece method first checks if the piece's point has already been halved
     *      if not it checks the colour of the piece
     *          if it is different it halves its point
     * */
    public void tryToHalvePiece(ChessPiece piece) {
        if (!piece.isPointHalved()) {
            if (piece.getPieceColour() != this.getPieceColour()) {
                float halved = piece.getPiecePoint() / 2;
                piece.setPiecePoint(halved);
                piece.setPointHalved(true);

            }
        }

    }

    public boolean isInBounds(int x) {
        return x >= 0 && x <= 7;
    }

    public Colour getPieceColour() {
        return pieceColour;
    }


    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    /*
      setPieceLocation method uses ASCII to turn the chars to int by subtracting '0'
    */
    public void setPieceLocation(String pieceLocation) {
        this.locationX = pieceLocation.charAt(0) - '0';
        this.locationY = pieceLocation.charAt(1) - '0';

    }

    public float getPiecePoint() {
        return piecePoint;
    }


    public void setPiecePoint(float piecePoint) {
        this.piecePoint = piecePoint;
    }

    public Type getPieceType() {
        return pieceType;
    }

    public boolean isPointHalved() {
        return pointHalved;
    }

    public void setPointHalved(boolean pointHalved) {
        this.pointHalved = pointHalved;
    }

}
