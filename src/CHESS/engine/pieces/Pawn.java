package CHESS.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import CHESS.engine.Alliance;
import CHESS.engine.board.AttackMove;
import CHESS.engine.board.Board;
import CHESS.engine.board.BoardUtils;
import CHESS.engine.board.MajorMove;
import CHESS.engine.board.Move;

public class Pawn extends Piece{

  private final static int [] CANDIDATE_MOVE_COORDINATES = {8, 16};

  Pawn(int piecePosition, Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(Board board) {

    final List<Move> legalMoves = new ArrayList<>();

    for(int currentCandidateeOffset: CANDIDATE_MOVE_COORDINATES){
      int candidateDestinationCoordinate = this.piecePosition + 
            (this.pieceAlliance.getDirection() * currentCandidateeOffset);
      
      if (BoardUtils.isValidTitleCoordinate(candidateDestinationCoordinate)){
        continue;
      }
      if (currentCandidateeOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
        
        //Add Pawn Promotions 

        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
      } //pawn Jump
      else if (currentCandidateeOffset == 16 && this.isFirstMove() &&
          (BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack()) ||
          (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite())
          ){
        
        final int behindCandidateDestinationCoordinate = this.piecePosition + 
              (this.getPieceAlliance().getDirection() * 8);
        if (!board.getTile(candidateDestinationCoordinate).isTileOccupied() &&
            !board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()){
          legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
        }
      }//pawn Attack
      else if(currentCandidateeOffset == 7 &&
              !((BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
              (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))
              ){
        if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
          final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
          if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
          }
        }

      } else if(currentCandidateeOffset == 9 &&
              !((BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) ||
              (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()))
              ){
        if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
          final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
          if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){
            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate));
          }
        }
      }


    }
    
    return legalMoves;
  }
    
}
