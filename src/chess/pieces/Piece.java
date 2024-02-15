package chess.pieces;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

import chess.Board;

public class Piece {
    public int col, row;
    public int xPos, yPos;

    public boolean isWhite;
    public String name;
    public int value;

    BufferedImage sheet;
    {
        try {
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("chess/resources/pieces.png"));

        } catch (IOException e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    protected int sheetScale = sheet.getWidth()/6;

    Image sprite;
    Board board;

    public Piece(Board board){
        this.board = board;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(sprite,xPos,yPos,null);
    }
}
