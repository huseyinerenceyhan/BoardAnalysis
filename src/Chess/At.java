package Chess;

public class At extends ChessPiece {

    public At(Colour colour, Type type) {
        super(colour, type);
    }

    /* calculateAttackableSquares method for the At class works like the following

               -- -- -- -- -- -- -- --
               -- -- -- -- -- -- -- --
               -- -- -- 21 -- 31 -- --
               -- -- 11 -- -- -- 41 --
               -- -- -- -- at -- -- --
               -- -- 12 -- -- -- 42 --
               -- -- -- 22 -- 32 -- --
               -- -- -- -- -- -- -- --

              There are 4 iterations each attacking 2 squares
                Example: 32 means 3rd iteration 2nd attacked square
    */

  @Override
    public void calculateAttackableSquares(ChessPiece[][] board) {

        int x = this.getLocationX();
        int y = this.getLocationY();

        //for the 1st iteration
        int offsetX = -2;
        int offsetY = 1;


        for (int i = 0; i < 4; i++) {

            if (isInBounds(x + offsetX) && isInBounds(y + offsetY)) {
                ChessPiece piece = board[y + offsetY][x + offsetX];
                if (piece != null) {
                    tryToHalvePiece(piece);
                }

            }
            if (isInBounds(x + offsetX) && isInBounds(y - offsetY)) {
                ChessPiece piece = board[y - offsetY][x + offsetX];
                if (piece != null) {
                    tryToHalvePiece(piece);
                }

            }
            offsetY++;
            offsetX++;
            // setting offsets correctly for the iterations
            if (offsetX == 0) {
                //this means we are in the same x as the at so skip the offsetX = 0
                offsetX++;
            }
            if (offsetY == 3 && i == 1) {
                offsetY = 2;
            }
            if (i == 2) {
                offsetY = 1;
            }
        }
    }


}
