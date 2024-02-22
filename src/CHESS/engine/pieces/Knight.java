package CHESS.engine.pieces;

import CHESS.engine.Alliance;
import CHESS.engine.moveCheck;
import CHESS.engine.board.Board;
import CHESS.engine.board.BoardUtils;
import CHESS.engine.board.Move;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

  List<Move> legalMoves = new ArrayList<>();

  private final int[] CANDIDATE_MOVE_COORDINATES = {
    -17,
    -15,
    -10,
    -6,
    6,
    10,
    15,
    17,
  };

  Knight(int piecePosition, Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(Board board) {
    for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
      int candidateDestinationCoordinate =
        this.piecePosition + currentCandidateOffset;

      if (BoardUtils.isValidTitleCoordinate(candidateDestinationCoordinate)) {
        if (
          isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
          isSecondColumnExclusion(
            currentCandidateOffset,
            currentCandidateOffset
          ) ||
          isSeventhColumnExclusion(
            currentCandidateOffset,
            currentCandidateOffset
          ) ||
          isEightColumnExclusion(currentCandidateOffset, currentCandidateOffset)
        ) {
          continue;
        }

        Move move = moveCheck.createMoveIfLegal(board, this, candidateDestinationCoordinate);
				if (move != null) {
					legalMoves.add(move);
				}

      }
    }

    return legalMoves;
  }

  private boolean isFirstColumnExclusion(
    final int currentPosition,
    final int candidateOffset
  ) {
    return (
      BoardUtils.FIRST_COLUMN[currentPosition] &&
      (
        (candidateOffset == -17) ||
        (candidateOffset == -10) ||
        (candidateOffset == 6) ||
        (candidateOffset == 15)
      )
    );
  }

  private boolean isSecondColumnExclusion(
    final int currentPosition,
    final int candidateOffset
  ) {
    return (
      BoardUtils.SECOND_COLUMN[currentPosition] &&
      ((candidateOffset == -10) || (candidateOffset == 6))
    );
  }

  private boolean isSeventhColumnExclusion(
    final int currentPosition,
    final int candidateOffset
  ) {
    return (
      BoardUtils.SEVENTH_COLUMN[currentPosition] &&
      ((candidateOffset == -6) || (candidateOffset == 10))
    );
  }

  private boolean isEightColumnExclusion(
    final int currentPosition,
    final int candidateOffset
  ) {
    return (
      BoardUtils.EIGHT_COLUMN[currentPosition] &&
      (
        (candidateOffset == -15) ||
        (candidateOffset == -6) ||
        (candidateOffset == 10) ||
        (candidateOffset == 17)
      )
    );
  }
}
