package CHESS.engine.board;

import CHESS.engine.pieces.Piece;

public class AttackMove extends Move{

    final Piece AttackedPiece;
    
    public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.AttackedPiece = attackedPiece;
    }
    
}
