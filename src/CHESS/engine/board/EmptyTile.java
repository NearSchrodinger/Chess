package CHESS.engine.board;

import CHESS.engine.pieces.Piece;

public class EmptyTile extends Tile {
    EmptyTile(int coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
