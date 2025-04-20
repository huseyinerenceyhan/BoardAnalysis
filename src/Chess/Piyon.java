package Chess;

public class Piyon extends ChessPiece {
    public Piyon(Colour colour, Type type) {
        super(colour, type);
    }
    /* calculateAttackableSquares method for the Piyon class works like the following

            if the Piyon is black try to attack downwards  diagonals by 1
            if the Piyon is white try to attack upwards  diagonals by 1

               -- -- -- -- -- -- -- --
               -- -- -- -- pb -- -- --
               -- -- -- xx -- xx -- --
               -- -- -- -- -- -- -- --
               -- -- -- xx -- xx -- --
               -- -- -- -- pw -- -- --
               -- -- -- -- -- -- -- --
               -- -- -- -- -- -- -- --

    */

    @Override
    public void calculateAttackableSquares(ChessPiece[][] board) {
        // direction setting
        int offsetY = 1;//up for white
        if (this.getPieceColour() == Colour.SIYAH) {
            offsetY = -1;//down for black
        }
        int x = this.getLocationX();
        int y = this.getLocationY();

        int offsetX = 1;

        if (isInBounds(y + offsetY) && isInBounds(x + offsetX)) {
            ChessPiece piece = board[y + offsetY][x + offsetX];
            if (piece != null) {
                tryToHalvePiece(piece);
            }
        }
        offsetX = -1;
        if (isInBounds(y + offsetY) && isInBounds(x + offsetX)) {
            ChessPiece piece = board[y + offsetY][x + offsetX];
            if (piece != null) {
                tryToHalvePiece(piece);
            }
        }


    }

}

