package Chess;

public class Fil extends ChessPiece{
    public Fil(Colour colour,Type type){
        super(colour,type);
    };

    /* calculateAttackableSquares method for the Fil class works like the following

      call the attackMultipleDiagonal method which attacks like the following
       1- attack the symmetric diagonals by calling symmetricDiagonal
                iterate till hit a piece or end of board
                marked with sy
            check offsets +1,+1 and -1,-1
       2- attack the asymmetric diagonals calling asymmetricDiagonal
                iterate till hit a piece or end of board
                marked with as
            check offsets +1,-1 and -1,+1

               -- -- -- -- -- -- -- --
               as -- -- -- -- -- -- --
               -- as -- -- -- -- -- sy
               -- -- as -- -- -- sy --
               -- -- -- as -- sy -- --
               -- -- -- -- fb -- -- --
               -- -- -- sy -- as -- --
               -- -- sy -- -- -- as --


    */

    @Override
    public void calculateAttackableSquares(ChessPiece[][] board) {
        attackMultipleDiagonal(board);
    }
}
