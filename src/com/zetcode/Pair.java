package com.zetcode;
class Pair {
    Pair parent;
    private int x;
    private int y;


    public Pair( int x, int y,Pair parent) {
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Pair getParent() {
        return parent;
    }

    public void setParent(Pair parent) {
        this.parent = parent;
    }


    public boolean equals(Pair obj) {
        return this.x == obj.x && this.y == obj.y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}