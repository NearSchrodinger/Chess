package CHESS.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import CHESS.engine.Alliance;
import CHESS.engine.moveCheck;
import CHESS.engine.board.Board;
import CHESS.engine.board.BoardUtils;
import CHESS.engine.board.Move;

public class King extends Piece{

  private final static int [] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

  King(int piecePosition, Alliance pieceAlliance) {
      super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(Board board) {
    final List<Move> legalMoves = new ArrayList<>();

    for(int currentCandidateeOffset: CANDIDATE_MOVE_COORDINATES){
      int candidateDestinationCoordinate = this.piecePosition + currentCandidateeOffset;

      if (isFirstColumnExclusion(this.piecePosition, currentCandidateeOffset) || 
      isEightColumnExclusion(this.piecePosition, currentCandidateeOffset)){
        continue;
      }

      if (BoardUtils.isValidTitleCoordinate(candidateDestinationCoordinate)){
        Move move = moveCheck.createMoveIfLegal(board, this, candidateDestinationCoordinate);
				if (move != null) {
					legalMoves.add(move);
				}
      }
    }
    return legalMoves;    
  }

  private boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
    return BoardUtils.FIRST_COLUMN[currentPosition] &&
    (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
  }

  private boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGHT_COLUMN[currentPosition] && 
    (candidateOffset == 9 || candidateOffset == 1 || candidateOffset == -7);
	}
    
}
