package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getVerticalMoves(from, board));
        moves.addAll(getHorizontalMoves(from, board));
        return moves;
    }

    @Override
    ArrayList<Move> getDiagonalMoves(Coordinates from, Board board) {
        return new ArrayList<>();
    }

}
