package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    private int direction;
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
        if (colour.equals(PlayerColour.WHITE)) direction = -1;
        if (colour.equals(PlayerColour.BLACK)) direction = 1;
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves  = new ArrayList<>();
        moves.addAll(getVerticalMoves(from, board));
        moves.addAll(getDiagonalMoves(from, board));
        return moves;
    }

    @Override
    ArrayList<Move> getVerticalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Coordinates> potentialCoords = new ArrayList<>();

        if ((this.getColour() == PlayerColour.WHITE && from.getRow() == 6)
                || (this.getColour() == PlayerColour.BLACK && from.getRow() == 1 )) {
            potentialCoords.add(from.plus(direction * 2, 0));
        }
        potentialCoords.add(from.plus(direction, 0));

        potentialCoords.forEach(coordinates -> {
            if (coordinates.isOnBoard()
                    && board.get(coordinates) == null) {
                moves.add(new Move(from, coordinates));
            }
        });
        return moves;
    }

    @Override
    ArrayList<Move> getHorizontalMoves(Coordinates from, Board board) {
        return new ArrayList<>();
    }

    @Override
    ArrayList<Move> getDiagonalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Coordinates> potentialCoords = new ArrayList<>();

        if (this.getColour() == PlayerColour.WHITE) {
            potentialCoords.add(from.plus(-1, 1));
            potentialCoords.add(from.plus(-1, -1));
        }
        if (this.getColour() == PlayerColour.BLACK) {
            potentialCoords.add(from.plus(1, 1));
            potentialCoords.add(from.plus(1, -1));
        }

        potentialCoords.forEach(coordinates -> {
            if (coordinates.isOnBoard()) {
                Piece piece = board.get(coordinates);
                if (piece != null && piece.getColour() != this.getColour()) {
                    moves.add(new Move(from, coordinates));
                }
            }
        });

        return moves;
    }
}
