package CHESS.engine.board;

import CHESS.engine.pieces.Piece;

public class OccupiedTile extends Tile {

  private final Piece pieceOnTile;

  OccupiedTile(int tileCoordinate, Piece piece) {
    super(tileCoordinate);
    this.pieceOnTile = piece;
  }

  @Override
  public boolean isTileOccupied() {
    return true;
  }

  @Override
  public Piece getPiece() {
    return this.pieceOnTile;
  }
}
