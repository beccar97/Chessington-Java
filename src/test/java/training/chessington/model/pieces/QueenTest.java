package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    @Test
    public void whiteQueenCanMoveVertically() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(4, 4);
        board.placePiece(coordinates, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coordinates, board);

        // Assert
        for (int row = 0; row <= 7; row++) {
            if (row != 4) {
                assertThat(moves).contains(new Move(coordinates, new Coordinates(row, 4)));
            }
        }
    }

    @Test
    public void blackQueenCanMoveVertically() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(4, 4);
        board.placePiece(coordinates, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coordinates, board);

        // Assert
        for (int row = 0; row <= 7; row++) {
            if (row != 4) {
                assertThat(moves).contains(new Move(coordinates, new Coordinates(row, 4)));
            }
        }
    }

    @Test
    public void whiteQueenCanMoveHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(4, 4);
        board.placePiece(coordinates, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coordinates, board);

        // Assert
        for (int col = 0; col <= 7; col++) {
            if (col != 4) {
                assertThat(moves).contains(new Move(coordinates, new Coordinates(4, col)));
            }
        }
    }

    @Test
    public void blackQueenCanMoveHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(4, 4);
        board.placePiece(coordinates, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coordinates, board);

        // Assert
        for (int col = 0; col <= 7; col++) {
            if (col != 4) {
                assertThat(moves).contains(new Move(coordinates, new Coordinates(4, col)));
            }
        }
    }

    @Test
    public void whiteQueenCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates coordinates = new Coordinates(4, 4);
        board.placePiece(coordinates, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coordinates, board);

        // Assert
        for (int i = 0; i <= 7; i++) {
            if (i != 4) {
                assertThat(moves).contains(new Move(coordinates, new Coordinates(i, i)));
                if (i != 0) assertThat(moves).contains(new Move(coordinates, new Coordinates(i, 8 - i)));
            }
        }

    }

    @Test
    public void blackQueenCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coordinates = new Coordinates(4, 4);
        board.placePiece(coordinates, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coordinates, board);

        // Assert
        for (int i = 0; i <= 7; i++) {
            if (i != 4) {
                assertThat(moves).contains(new Move(coordinates, new Coordinates(i, i)));
                if (i != 0) assertThat(moves).contains(new Move(coordinates, new Coordinates(i, 8 - i)));
            }
        }
    }

    @Test
    public void queenCannotPassPieceOfSameColourVertically() {
        // Arrange
        Board board = Board.empty();

        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(7, 3);
        board.placePiece(whiteCoords, whiteQueen);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(5, 3);
        board.placePiece(whitePawnCoords, whitePawn);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(0, 3);
        board.placePiece(blackCoords, blackQueen);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(4, 3);
        board.placePiece(blackPawnCoords, blackPawn);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords));
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords.plus(-1, 0)));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords.plus(1, 0)));
    }

    @Test
    public void queenCannotPassPieceOfSameColourHorizontally() {
        // Arrange
        Board board = Board.empty();

        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(7, 3);
        board.placePiece(whiteCoords, whiteQueen);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(7, 1);
        board.placePiece(whitePawnCoords, whitePawn);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(0, 3);
        board.placePiece(blackCoords, blackQueen);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(0, 5);
        board.placePiece(blackPawnCoords, blackPawn);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords));
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords.plus(0, -1)));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords.plus(0, 1)));
    }

    @Test
    public void queenCannotPassPieceOfSameColourDiagonally() {
        // Arrange
        Board board = Board.empty();

        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(7, 3);
        board.placePiece(whiteCoords, whiteQueen);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(4, 6);
        board.placePiece(whitePawnCoords, whitePawn);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(0, 3);
        board.placePiece(blackCoords, blackQueen);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(2, 1);
        board.placePiece(blackPawnCoords, blackPawn);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords));
        assertThat(whiteMoves).doesNotContain(new Move(whiteCoords, whitePawnCoords.plus(1, 1)));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords));
        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackPawnCoords.plus(-1, -1)));
    }

    @Test
    public void queenCanTakePieceVertically() {
        // Arrange
        Board board = Board.empty();

        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(7, 3);
        board.placePiece(whiteCoords, whiteQueen);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(5, 3);
        board.placePiece(blackPawnCoords, blackPawn);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(0, 3);
        board.placePiece(blackCoords, blackQueen);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(4, 3);
        board.placePiece(whitePawnCoords, whitePawn);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteCoords, blackPawnCoords));
        assertThat(blackMoves).contains(new Move(blackCoords, whitePawnCoords));
    }

    @Test
    public void queenCanTakePieceHorizontally() {
        // Arrange
        Board board = Board.empty();

        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(7, 3);
        board.placePiece(whiteCoords, whiteQueen);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(7, 7);
        board.placePiece(blackPawnCoords, blackPawn);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(0, 3);
        board.placePiece(blackCoords, blackQueen);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(0, 1);
        board.placePiece(whitePawnCoords, whitePawn);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteCoords, blackPawnCoords));
        assertThat(blackMoves).contains(new Move(blackCoords, whitePawnCoords));
    }

    @Test
    public void queenCanTakePieceDiagonally() {
        // Arrange
        Board board = Board.empty();

        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(7, 3);
        board.placePiece(whiteCoords, whiteQueen);

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackPawnCoords = new Coordinates(4, 6);
        board.placePiece(blackPawnCoords, blackPawn);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(0, 3);
        board.placePiece(blackCoords, blackQueen);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whitePawnCoords = new Coordinates(2, 1);
        board.placePiece(whitePawnCoords, whitePawn);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteCoords, blackPawnCoords));
        assertThat(blackMoves).contains(new Move(blackCoords, whitePawnCoords));
    }

}
