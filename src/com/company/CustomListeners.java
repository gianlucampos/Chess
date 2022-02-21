package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CustomListeners extends KeyAdapter implements MouseListener, MouseMotionListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Chess.SELECTED_PIECE = Chess.getPiece(e.getX(), e.getY());
        if (Chess.SELECTED_PIECE != null) {
            Chess.SELECTED_PIECE.x = e.getX() - 32;
            Chess.SELECTED_PIECE.y = e.getY() - 32;
            Chess.FRAME.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (Chess.SELECTED_PIECE != null) {
            Chess.SELECTED_PIECE.move(e.getX() / 64, e.getY() / 64);
            Chess.FRAME.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Chess.SELECTED_PIECE != null) {
            Chess.SELECTED_PIECE.x = e.getX() - 32;
            Chess.SELECTED_PIECE.y = e.getY() - 32;
            Chess.FRAME.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }
    }
}
