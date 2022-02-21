package com.company;

import java.util.LinkedList;

public class Piece {

    public enum Name {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
    }

    int x, y, xp, yp;
    boolean isWhite;
    Name nameEnum;
    LinkedList<Piece> ps;

    public Piece(int xp, int yp, boolean isWhite, Name nameEnum, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.ps = ps;
        this.nameEnum = nameEnum;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        Piece piece = Chess.getPiece(xp * 64, yp * 64);
        if (piece != null) {
            if (!piece.isWhite) {
                piece.capture();
            } else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }
        this.xp = xp;
        this.yp = yp;
    }

    public void capture() {
        ps.remove(this);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "xp=" + xp +
                ", yp=" + yp +
                ", color=" + (isWhite ? "White" : "Black") +
                ", nameEnum=" + nameEnum +
                '}';
    }
}
