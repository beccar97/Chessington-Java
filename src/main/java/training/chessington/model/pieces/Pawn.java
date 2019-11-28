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
        ArrayList<Move> moves  = new ArrayList<>();
        moves.addAll(getStandardMoves(from, board));
        moves.addAll(getCapturingMoves(from, board));
        return moves;
    }

    private ArrayList<Move> getStandardMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Coordinates> potentialCoords = new ArrayList<>();

        if (this.getColour() == PlayerColour.WHITE) {
            if (from.getRow() == 6) {
                potentialCoords.add(from.plus(-2, 0));
            }
            potentialCoords.add(from.plus(-1, 0));
        }

        if (this.getColour() == PlayerColour.BLACK) {
            if (from.getRow() == 1) {
                potentialCoords.add(from.plus(2, 0));
            }
            potentialCoords.add(from.plus(1, 0));
        }

        potentialCoords.forEach(coordinates -> {
            if (coordinates.isOnBoard()
                    && board.get(coordinates) == null) {
                moves.add(new Move(from, coordinates));
            }
        });
        return moves;
    }

    private ArrayList<Move> getCapturingMoves(Coordinates from, Board board) {
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
