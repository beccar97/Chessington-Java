package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves =  new ArrayList<>();
        if (this.getColour() == PlayerColour.WHITE) {
            moves.add(new Move(from, from.plus(-1, 0)));
        }
        if (this.getColour() == PlayerColour.BLACK) {
            moves.add(new Move(from, from.plus(1, 0)));
        }
        return moves;
    }
}
