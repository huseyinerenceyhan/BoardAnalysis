package Chess;

public class Sah extends ChessPiece {
    public Sah(Colour colour, Type type) {
        super(colour, type);
    }
 /* calculateAttackableSquares method for the Sah class works like the following


               -- -- -- -- -- -- -- --
               -- -- -- -- -- -- -- --
               -- -- -- -- -- -- -- --
               -- -- -- -- -- -- -- --
               -- -- -- 11 12 13 -- --
               -- -- -- 21 sw 22 -- --
               -- -- -- 31 32 33 -- --
               -- -- -- -- -- -- -- --
               We have 3 different loops for each line
               Example: 32 means 3rd loop 2nd iteration

    */


    @Override
    public void calculateAttackableSquares(ChessPiece[][] board) {
        int x = this.getLocationX();
        int y = this.getLocationY();
        // for first loop
        int offsetX = -1;
        int offsetY = 1;


        for (int i = 0; i < 3; i++) {
            if (isInBounds(x + offsetX) && isInBounds(y + offsetY)) {
                ChessPiece piece = board[y + offsetY][x + offsetX];
                if (piece != null) {
                    tryToHalvePiece(piece);
                }

            }
            offsetX++;
        }
        // for the second loop
        offsetY--;
        for (int i = -1; i < 2; i += 2) { // i is increased by 2 to skip i=0 which is the same x as the Sah
            offsetX = i;
            if (isInBounds(x + offsetX) && isInBounds(y + offsetY)) {

                ChessPiece piece = board[y + offsetY][x + offsetX];
                if (piece != null) {
                    tryToHalvePiece(piece);
                }
            }


        }
        // for the 3rd loop
        offsetX = -1;
        offsetY--;
        for (int i = 0; i < 3; i++) {
            if (isInBounds(x + offsetX) && isInBounds(y + offsetY)) {
                ChessPiece piece = board[y + offsetY][x + offsetX];
                if (piece != null) {
                    tryToHalvePiece(piece);
                }

            }
            offsetX++;
        }


    }

}

