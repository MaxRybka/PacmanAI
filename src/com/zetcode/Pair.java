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

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Pair or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Pair)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Pair pair = (Pair) obj;

        return this.x == pair.x && this.y == pair.y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}