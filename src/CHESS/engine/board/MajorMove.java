package CHESS.engine.board;

import CHESS.engine.pieces.Piece;

public class MajorMove extends Move{

    public MajorMove(Board board, Piece movedPiece, int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
    
}
