package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;
import java.util.logging.LogManager;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {
    @Test
    public void whiteKingCanMoveOneInAnyDir() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(5, 4);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
            for (int colDiff = -1; colDiff <= 1; colDiff++) {
                if (!(colDiff == 0 && rowDiff == 0)) assertThat(moves).contains(new Move(coords, coords.plus(rowDiff, colDiff)));
            }
        }
    }

    @Test
    public void blackKingCanMoveOneInAnyDir() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(5, 4);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
            for (int colDiff = -1; colDiff <= 1; colDiff++) {
                if (!(colDiff == 0 && rowDiff == 0)) assertThat(moves).contains(new Move(coords, coords.plus(rowDiff, colDiff)));
            }
        }
    }

    @Test
    public void kingCannotMoveIntoSpaceContainingPieceOfSameColour() {
        // Arrange
        Board board = Board.empty();

        Piece whiteKing = new King(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(5, 4);
        board.placePiece(whiteCoords, whiteKing);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(5, 5);
        board.placePiece(whitePawnCoords, whitePawn);

        Piece blackKing = new King(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(2, 4);
        board.placePiece(whiteCoords, blackKing);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(2, 5);
        board.placePiece(blackPawnCoords, blackPawn);

        // Act
        List<Move> whiteMoves = whiteKing.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackKing.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords));
    }

    @Test
    public void kingCanTakePiecesOfOppositeColor() {
        // Arrange
        Board board = Board.empty();

        Piece whiteKing = new King(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(5, 4);
        board.placePiece(whiteCoords, whiteKing);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(5, 5);
        board.placePiece(blackPawnCoords, blackPawn);

        Piece blackKing = new King(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(2, 4);
        board.placePiece(whiteCoords, blackKing);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(2, 5);
        board.placePiece(whitePawnCoords, whitePawn);


        // Act
        List<Move> whiteMoves = whiteKing.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackKing.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteCoords, blackPawnCoords));
        assertThat(blackMoves).contains(new Move(blackCoords, whitePawnCoords));
    }
}
