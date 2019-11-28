package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import javax.crypto.Cipher;
import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getVerticalMoves(from, board));
        moves.addAll(getHorizontalMoves(from, board));
        moves.addAll(getDiagonalMoves(from, board));
        return moves;
    }

    @Override
    ArrayList<Move> getVerticalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        Coordinates upOne = from.plus(1, 0);
        Coordinates downOne = from.plus(-1, 0);

        checkAndAddMove(from, board, moves, upOne.getRow(), upOne.getCol());
        checkAndAddMove(from, board, moves, downOne.getRow(), downOne.getCol());

        return moves;
    }

    @Override
    ArrayList<Move> getHorizontalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        Coordinates leftOne = from.plus(0,-1);
        Coordinates rightOne = from.plus(0, 1);

        checkAndAddMove(from, board, moves, leftOne.getRow(), leftOne.getCol());
        checkAndAddMove(from, board, moves, rightOne.getRow(), rightOne.getCol());

        return moves;
    }

    @Override
    ArrayList<Move> getDiagonalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        Coordinates upLeftOne = from.plus(1, 1);
        Coordinates upRightOne = from.plus(1, -1);
        Coordinates downLeftOne = from.plus(-1, 1);
        Coordinates downRightOne = from.plus(-1, -1);

        checkAndAddMove(from, board, moves, upLeftOne.getRow(), upLeftOne.getCol());
        checkAndAddMove(from, board, moves, upRightOne.getRow(), upRightOne.getCol());
        checkAndAddMove(from, board, moves, downLeftOne.getRow(), downLeftOne.getCol());
        checkAndAddMove(from, board, moves, downRightOne.getRow(), downRightOne.getCol());

        return moves;
    }


}
