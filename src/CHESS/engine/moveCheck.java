package CHESS.engine;

import CHESS.engine.board.AttackMove;
import CHESS.engine.board.Board;
import CHESS.engine.board.MajorMove;
import CHESS.engine.board.Move;
import CHESS.engine.board.Tile;
import CHESS.engine.pieces.Piece;

public class moveCheck {

  public static Move createMoveIfLegal(Board board, Piece piece, int destinationCoordinate) {
    final Tile destinationTile = board.getTile(destinationCoordinate);
    if (!destinationTile.isTileOccupied()) {
      return new MajorMove(board, piece, destinationCoordinate);
    } else {
      final Piece pieceAtDestination = destinationTile.getPiece();
      final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

      if (piece.getPieceAlliance() != pieceAlliance) {
          return new AttackMove(board, piece, destinationCoordinate, pieceAtDestination);
      }
    }
    return null;
  }
}