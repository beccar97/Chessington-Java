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

    private ArrayList<Move> getVerticalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int currRow = from.getRow() + 1; currRow <= 7; currRow++) {
            if (checkMove(from, board, moves, currRow, from.getCol())) break;
        }

        for (int currRow = from.getRow() - 1; currRow >= 0; currRow--) {
            if (checkMove(from, board, moves, currRow, from.getCol())) break;
        }

        return moves;
    }

    private ArrayList<Move> getHorizontalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int currCol = from.getCol() + 1; currCol <= 7; currCol++){
            if (checkMove(from, board, moves, from.getRow(), currCol)) break;
        }

        for (int currCol = from.getCol() -1; currCol >=0; currCol--) {
            if (checkMove(from, board, moves, from.getRow(), currCol)) break;
        }

        return moves;
    }

    private boolean checkMove(Coordinates from, Board board, ArrayList<Move> moves, int currRow, int currCol) {
        Coordinates coords = new Coordinates(currRow, currCol);
        Piece piece = board.get(coords);

        if (piece == null) moves.add(new Move(from, coords));
        else {
            if (piece.getColour() != this.getColour()) moves.add(new Move(from, coords));
            return true;
        }
        return false;
    }
}
