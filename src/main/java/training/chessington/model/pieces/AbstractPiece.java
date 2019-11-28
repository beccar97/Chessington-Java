package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }

    ArrayList<Move> getVerticalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int currRow = from.getRow() + 1; currRow <= 7; currRow++) {
            if (checkAndAddMove(from, board, moves, currRow, from.getCol())) break;
        }

        for (int currRow = from.getRow() - 1; currRow >= 0; currRow--) {
            if (checkAndAddMove(from, board, moves, currRow, from.getCol())) break;
        }

        return moves;
    }

    ArrayList<Move> getHorizontalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();

        for (int currCol = from.getCol() + 1; currCol <= 7; currCol++){
            if (checkAndAddMove(from, board, moves, from.getRow(), currCol)) break;
        }

        for (int currCol = from.getCol() -1; currCol >=0; currCol--) {
            if (checkAndAddMove(from, board, moves, from.getRow(), currCol)) break;
        }

        return moves;
    }

    ArrayList<Move> getDiagonalMoves(Coordinates from, Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getOneDiagonalMove(from, board, 1, 1));
        moves.addAll(getOneDiagonalMove(from, board, 1, -1));
        moves.addAll(getOneDiagonalMove(from, board, -1, 1));
        moves.addAll(getOneDiagonalMove(from, board, -1, -1));

        return moves;
    }

    private ArrayList<Move> getOneDiagonalMove(Coordinates from, Board board, int rowChange, int colChange){
        ArrayList<Move> moves = new ArrayList<>();
        int curRow = from.getRow()  + rowChange;
        int curCol = from.getCol() + colChange;

        while ((0 <= curCol && curCol <= 7) && (0<= curRow & curRow <=7)) {
            if (checkAndAddMove(from, board, moves, curRow, curCol)) break;
            curRow += rowChange;
            curCol += colChange;
        }

        return  moves;
    }

    boolean checkAndAddMove(Coordinates from, Board board, ArrayList<Move> moves, int row, int col) {
        Coordinates coords = new Coordinates(row, col);
        Piece piece = board.get(coords);

        if (!coords.isOnBoard() || coords.equals(from)) return false;
        if (piece == null) moves.add(new Move(from, coords));
        else {
            if (piece.getColour() != this.getColour()) moves.add(new Move(from, coords));
            return true;
        }
        return false;
    }
}
