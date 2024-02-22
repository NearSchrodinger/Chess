package CHESS.engine.board;

import CHESS.engine.pieces.Piece;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

  protected final int tileCoordinate;

  private final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

  protected Tile(final int tileCoordinate) {
    this.tileCoordinate = tileCoordinate;
  }

  private Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
    final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

    for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
      emptyTileMap.put(i, new EmptyTile(i));
    }

    return emptyTileMap;
  }

  public Tile createTile(final int tileCoordinate, final Piece piece){
    return piece != null ? new OccupiedTile(tileCoordinate, getPiece()) : EMPTY_TILES.get(tileCoordinate);
  }

  public abstract boolean isTileOccupied();

  public abstract Piece getPiece();
}
