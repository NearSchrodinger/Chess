package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.*;

import chess.pieces.*;;

public class Board extends JPanel {

    public int titleSize = 85;

    int cols = 8;
    int rows = 8;

    ArrayList<Piece> piecelist = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);

    public Board() {
        this.setPreferredSize(new Dimension(cols * titleSize, rows * titleSize));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }

    public Piece getPiece(int col, int row) {

        for (Piece piece : piecelist) {
            if (piece.col == col && piece.row == row)
                return piece;
        }

        return null;
    }

    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * titleSize;
        move.piece.yPos = move.newRow * titleSize;

        capture(move);
    }

    public void capture(Move move) {
        piecelist.remove(move.capture);
    }

    public boolean isValidMove(Move move) {

        if (sameTeam(move.piece, move.capture)) {
            return false;
        }
        return true;
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null)
            return false;

        return p1.isWhite == p2.isWhite;
    }

    public void addPieces() {
        piecelist.add(new Rook(this, 0, 0, false));
        piecelist.add(new Knight(this, 1, 0, false));
        piecelist.add(new Bishop(this, 2, 0, false));
        piecelist.add(new Queen(this, 3, 0, false));
        piecelist.add(new King(this, 4, 0, false));
        piecelist.add(new Bishop(this, 5, 0, false));
        piecelist.add(new Knight(this, 6, 0, false));
        piecelist.add(new Rook(this, 7, 0, false));

        piecelist.add(new Pawn(this, 0, 1, false));
        piecelist.add(new Pawn(this, 1, 1, false));
        piecelist.add(new Pawn(this, 2, 1, false));
        piecelist.add(new Pawn(this, 3, 1, false));
        piecelist.add(new Pawn(this, 4, 1, false));
        piecelist.add(new Pawn(this, 5, 1, false));
        piecelist.add(new Pawn(this, 6, 1, false));
        piecelist.add(new Pawn(this, 7, 1, false));

        piecelist.add(new Rook(this, 0, 7, true));
        piecelist.add(new Knight(this, 1, 7, true));
        piecelist.add(new Bishop(this, 2, 7, true));
        piecelist.add(new Queen(this, 3, 7, true));
        piecelist.add(new King(this, 4, 7, true));
        piecelist.add(new Bishop(this, 5, 7, true));
        piecelist.add(new Knight(this, 6, 7, true));
        piecelist.add(new Rook(this, 7, 7, true));

        piecelist.add(new Pawn(this, 0, 6, true));
        piecelist.add(new Pawn(this, 1, 6, true));
        piecelist.add(new Pawn(this, 2, 6, true));
        piecelist.add(new Pawn(this, 3, 6, true));
        piecelist.add(new Pawn(this, 4, 6, true));
        piecelist.add(new Pawn(this, 5, 6, true));
        piecelist.add(new Pawn(this, 6, 6, true));
        piecelist.add(new Pawn(this, 7, 6, true));

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 105, 53));
                g2d.fillRect(c * titleSize, r * titleSize, titleSize, titleSize);
            }
        for (Piece piece : piecelist) {
            piece.paint(g2d);
        }
    }
}
