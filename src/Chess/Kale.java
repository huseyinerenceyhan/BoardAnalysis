package Chess;

public class Kale extends ChessPiece {
    public Kale(Colour colour, Type type) {
        super(colour, type);
    }

    /* calculateAttackableSquares method for the Kale class works like the following

      call the attackMultipleCardinal method which attacks like the following
          1-  attack Horizontal both sides
                    marked with hh
          2- attack Vertical both sides
                    marked with vv

               -- -- -- -- vv -- -- --
               -- -- -- -- vv -- -- --
               -- -- -- -- vv -- -- --
               -- -- -- -- vv -- -- --
               -- -- -- -- vv -- -- --
               hh hh hh hh kb hh hh hh
               -- -- -- -- vv -- -- --
               -- -- -- -- vv -- -- --

    */

    @Override
    public void calculateAttackableSquares(ChessPiece[][] board) {
        attackMultipleCardinal(board);
    }




}
