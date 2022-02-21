package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Chess {

    public static final LinkedList<Piece> pieces = new LinkedList<>();
    public static final Chess GAME = new Chess();
    public static Piece SELECTED_PIECE = null;
    public static Frame FRAME;
    public final Image[] imgs = new Image[12];

    public static void main(String[] args) {
        GAME.start();
    }

    public void start() {
        try {
            loadImages();
            setupPieces();
            initializeFrame();
        } catch (Exception e) {
            System.out.println("Fail to run the game! \n Cause: " + e.getMessage());
        }
    }

    public static Piece getPiece(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        return pieces.stream()
                .filter(p -> p.xp == xp && p.yp == yp)
                .findFirst()
                .orElse(null);
    }

    private void loadImages() throws IOException {
        BufferedImage all = ImageIO.read(new File("./src/resources/pieces.png"));
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
    }

    private void initializeFrame() {
        FRAME = new JFrame("Chess");
        FRAME.setBounds(10, 10, 512, 512);
        FRAME.setUndecorated(true);
        FRAME.setResizable(false);
        FRAME.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean isWhite = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (isWhite) {
                            g.setColor(new Color(235, 235, 208));
                        } else {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        isWhite = !isWhite;
                    }
                    isWhite = !isWhite;
                }
                drawPieces(g, this);
            }
        };
        FRAME.add(panel);
        FRAME.setVisible(true);
        FRAME.addMouseListener(new CustomListeners());
        FRAME.addMouseMotionListener(new CustomListeners());
        FRAME.addKeyListener(new CustomListeners());
    }

    private void setupPieces() {
        //Pawns
        for (int i = 0; i < 8; i++) {
            new Piece(i, 6, true, Piece.Name.PAWN, pieces);
            new Piece(i, 1, false, Piece.Name.PAWN, pieces);
        }

        //White Pieces
        new Piece(0, 7, true, Piece.Name.ROOK, pieces);
        new Piece(1, 7, true, Piece.Name.KNIGHT, pieces);
        new Piece(2, 7, true, Piece.Name.BISHOP, pieces);
        new Piece(3, 7, true, Piece.Name.QUEEN, pieces);
        new Piece(4, 7, true, Piece.Name.KING, pieces);
        new Piece(5, 7, true, Piece.Name.BISHOP, pieces);
        new Piece(6, 7, true, Piece.Name.KNIGHT, pieces);
        new Piece(7, 7, true, Piece.Name.ROOK, pieces);

        //Black Pieces
        new Piece(0, 0, false, Piece.Name.ROOK, pieces);
        new Piece(1, 0, false, Piece.Name.KNIGHT, pieces);
        new Piece(2, 0, false, Piece.Name.BISHOP, pieces);
        new Piece(3, 0, false, Piece.Name.QUEEN, pieces);
        new Piece(4, 0, false, Piece.Name.KING, pieces);
        new Piece(5, 0, false, Piece.Name.BISHOP, pieces);
        new Piece(6, 0, false, Piece.Name.KNIGHT, pieces);
        new Piece(7, 0, false, Piece.Name.ROOK, pieces);
    }

    private void drawPieces(Graphics g, JPanel observer) {
        for (Piece p : pieces) {
            int ind = switch (p.nameEnum) {
                case KING -> 0;
                case QUEEN -> 1;
                case BISHOP -> 2;
                case KNIGHT -> 3;
                case ROOK -> 4;
                case PAWN -> 5;
            };
            if (!p.isWhite) {
                ind += 6;
            }
            g.drawImage(imgs[ind], p.xp * 64, p.yp * 64, observer);
        }
    }
}
