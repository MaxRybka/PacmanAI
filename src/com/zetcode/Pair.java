package com.zetcode;

import java.util.Comparator;

import static java.lang.Math.abs;

class Pair {
    Pair parent;
    private int x;
    private int y;

    private int cost = 0;

    public Pair( int x, int y,Pair parent) {
        this.parent = parent;
        this.x = x;
        this.y = y;

        Pair parrent_pair = parent;
        while(parrent_pair != null){
            cost++;
            parrent_pair = parrent_pair.parent;
        }
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

    public int getCost() { return cost; }

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

class HeuristicPairComparator implements Comparator<Pair>{

    private Pair target;

    public HeuristicPairComparator(Pair target) {
        this.target = target;
    }

    @Override
    public int compare(Pair o1, Pair o2) {
        int firstD = abs(target.getX() - o1.getX()) + abs(target.getY() - o1.getY());
        int secondD = abs(target.getX() - o2.getX()) + abs(target.getY() - o2.getY());

        return firstD - secondD;
    }
}

class APairComparator implements Comparator<Pair>{

    private Pair target;

    public APairComparator(Pair target) {
        this.target = target;
    }

    @Override
    public int compare(Pair o1, Pair o2) {
        int firstD = abs(target.getX() - o1.getX()) + abs(target.getY() - o1.getY()) + o1.getCost();
        int secondD = abs(target.getX() - o2.getX()) + abs(target.getY() - o2.getY()) + o2.getCost();

        return firstD - secondD;
    }
}