package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves =  new ArrayList<>();
        ArrayList<Coordinates> potentialCoords = getPossibleCoords(from);

        potentialCoords.forEach(coordinates -> {
            if (coordinates.isOnBoard()){
                Piece currPiece = board.get(coordinates);
                if (currPiece == null) moves.add(new Move(from, coordinates));
                else {
                    if (currPiece.getColour() != this.getColour()) {
                        moves.add(new Move(from, coordinates));
                    }
                }
            }
        });
        return moves;
    }


    private ArrayList<Coordinates> getPossibleCoords(Coordinates from) {
        ArrayList<Coordinates> coords = new ArrayList<>();

        for (int rowDiff = -1; rowDiff <= 1; rowDiff++){
            for (int colDiff = -1; colDiff <= 1; colDiff++) {
                if (!(colDiff == 0 && rowDiff == 0)) coords.add(from.plus(rowDiff, colDiff));
            }
        }

        return coords;
    }
}
