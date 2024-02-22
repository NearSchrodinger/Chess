package CHESS.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import CHESS.engine.Alliance;
import CHESS.engine.moveCheck;
import CHESS.engine.board.AttackMove;
import CHESS.engine.board.Board;
import CHESS.engine.board.BoardUtils;
import CHESS.engine.board.Move;

public class Rook extends Piece {

	private final static int [] CANDIDATE_MOVE_COORDINATES = {-8, -1, 1, 8};

	Rook(int piecePosition, Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
	}

	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
    final List<Move> legalMoves = new ArrayList<>();

			for(int candidateCoordinateOffset: CANDIDATE_MOVE_COORDINATES){
				int candidateDestinationCoordinate = this.piecePosition;
				while(BoardUtils.isValidTitleCoordinate(candidateDestinationCoordinate)){

					if(isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) || 
								isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
						break;
					}

					candidateDestinationCoordinate += candidateCoordinateOffset;

					if(BoardUtils.isValidTitleCoordinate(candidateDestinationCoordinate)){

						Move move = moveCheck.createMoveIfLegal(board, this, candidateDestinationCoordinate);
						if (move != null) {
							legalMoves.add(move);
							if (move instanceof AttackMove) {
								break;
							}
						}
					}
				}
			}
			return legalMoves;
	}
	private boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
	}

	private boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset) {
		return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == 1);
	}
}
