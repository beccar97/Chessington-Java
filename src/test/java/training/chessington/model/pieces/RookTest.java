package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {
    @Test
    public void whiteRookCanMoveVerticallyOnEmptyBoard() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(7, 0);
        board.placePiece(coordinates, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        for (int i = -1; i >= -7; i--) {
            assertThat(moves).contains(new Move(coordinates, coordinates.plus(i, 0)));
        }
    }

    @Test
    public void blackRookCanMoveVerticallyOnEmptyBoard() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(0, 0);
        board.placePiece(coordinates, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        for (int i = 1; i <= 7; i++) {
            assertThat(moves).contains(new Move(coordinates, coordinates.plus(i, 0)));
        }
    }

    @Test
    public void whiteRookCanMoveHorizontallyOnEmptyBoard() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(7, 0);
        board.placePiece(coordinates, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        for (int i = 1; i <= 7; i++) {
            assertThat(moves).contains(new Move(coordinates, coordinates.plus(0, i)));
        }
    }

    @Test
    public void blackRookCanMoveHorizontallyOnEmptyBoard() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(0, 0);
        board.placePiece(coordinates, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        for (int i = 1; i <= 7; i++) {
            assertThat(moves).contains(new Move(coordinates, coordinates.plus(0, i)));
        }
    }

    @Test
    public void whiteRookCannotMovePastPieceOfSameColour() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(7, 0);
        board.placePiece(coordinates, rook);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoordinates = new Coordinates(7, 3);
        board.placePiece(pawnCoordinates, pawn);

        Piece secondPawn = new Pawn(PlayerColour.WHITE);
        Coordinates secondPawnCoordinates = new Coordinates(5, 0);
        board.placePiece(secondPawnCoordinates, secondPawn);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coordinates, pawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, pawnCoordinates.plus(0, 1)));
        assertThat(moves).doesNotContain(new Move(coordinates, secondPawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, secondPawnCoordinates.plus(-1, 0)));
    }

    @Test
    public void blackRookCannotMovePastPieceOfSameColour() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(0, 0);
        board.placePiece(coordinates, rook);

        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates pawnCoordinates = new Coordinates(0, 3);
        board.placePiece(pawnCoordinates, pawn);

        Piece secondPawn = new Pawn(PlayerColour.BLACK);
        Coordinates secondPawnCoordinates = new Coordinates(5, 0);
        board.placePiece(secondPawnCoordinates, secondPawn);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coordinates, pawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, pawnCoordinates.plus(0, 1)));
        assertThat(moves).doesNotContain(new Move(coordinates, secondPawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, secondPawnCoordinates.plus(1, 0)));
    }

    @Test
    public void whiteRookCanTakeBlackPiece() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(7, 0);
        board.placePiece(coordinates, rook);

        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates pawnCoordinates = new Coordinates(7, 3);
        board.placePiece(pawnCoordinates, pawn);

        Piece secondPawn = new Pawn(PlayerColour.BLACK);
        Coordinates secondPawnCoordinates = new Coordinates(5, 0);
        board.placePiece(secondPawnCoordinates, secondPawn);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        assertThat(moves).contains(new Move(coordinates, pawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, pawnCoordinates.plus(0, 1)));
        assertThat(moves).contains(new Move(coordinates, secondPawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, secondPawnCoordinates.plus(-1, 0)));
    }

    @Test
    public void blackRookCanTakeWhitePiece() {
        // Arrange
        Board board = Board.empty();

        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(0, 0);
        board.placePiece(coordinates, rook);

        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates pawnCoordinates = new Coordinates(0, 3);
        board.placePiece(pawnCoordinates, pawn);

        Piece secondPawn = new Pawn(PlayerColour.WHITE);
        Coordinates secondPawnCoordinates = new Coordinates(5, 0);
        board.placePiece(secondPawnCoordinates, secondPawn);

        // Act
        List<Move> moves = rook.getAllowedMoves(coordinates, board);

        // Assert
        assertThat(moves).contains(new Move(coordinates, pawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, pawnCoordinates.plus(0, 1)));
        assertThat(moves).contains(new Move(coordinates, secondPawnCoordinates));
        assertThat(moves).doesNotContain(new Move(coordinates, secondPawnCoordinates.plus(1, 0)));
    }


}
