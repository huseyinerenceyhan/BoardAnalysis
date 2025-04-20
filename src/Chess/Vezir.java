package Chess;

public class Vezir extends ChessPiece {
    public Vezir(Colour colour, Type type) {
        super(colour, type);
    }

      /* calculateAttackableSquares method for the Vezir class works like the following


      call the attackMultipleDiagonal method which attacks like the following
       1- attack the symmetric diagonals by calling symmetricDiagonal
                iterate till hit a piece or end of board
                marked with sy
            check offsets +1,+1 and -1,-1
       2- attack the asymmetric diagonals calling asymmetricDiagonal
                iterate till hit a piece or end of board
                marked with as
            check offsets +1,-1 and -1,+1

      call the attackMultipleCardinal method which attacks like the following
          1-  attack Horizontal both sides
                    marked with hh
          2- attack Vertical both sides
                    marked with vv

               -- -- -- -- vv -- -- --
               -- -- -- -- vv -- -- --
               as -- -- -- vv -- -- sy
               -- as -- -- vv -- sy --
               -- -- as -- vv sy -- --
               hh hh hh as kb hh hh hh
               -- -- -- sy vv -- -- --
               -- -- sy -- vv as -- --

    */

    @Override
    public void calculateAttackableSquares(ChessPiece[][] board) {
        attackMultipleDiagonal(board);
        attackMultipleCardinal(board);
    }
}
