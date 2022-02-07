package com.company;

import java.util.LinkedList;

public class Piece {

    public enum Name {
        PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING
    }

    int xp;
    int yp;
    boolean isWhite;
    Name nameEnum;
    LinkedList<Piece> ps;

    public Piece(int xp, int yp, boolean isWhite, Name nameEnum, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        this.isWhite = isWhite;
        this.ps = ps;
        this.nameEnum = nameEnum;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        ps.stream().filter(p -> p.xp == xp && p.yp == yp).forEachOrdered(p -> capture());
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
                ", isWhite=" + isWhite +
                ", nameEnum=" + nameEnum +
                '}';
    }
}
