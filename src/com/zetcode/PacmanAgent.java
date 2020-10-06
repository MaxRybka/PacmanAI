package com.zetcode;

import java.util.*;

public class PacmanAgent implements Agent {
    private ArrayDeque<Pair> frontier = new ArrayDeque<>();
    private ArrayList<Pair> visited = new ArrayList<>();
    private Pair start_pair;
    private Board board;
    private int counter;
    private ArrayList<Long> mem;


    private long startCalcTime;

    public PacmanAgent(Pair start_pair,Board board) {
        this.board = board;
        this.start_pair = start_pair;
        frontier.add(start_pair);
    }

    @Override
    public Stack<Pair> startAgent(){
        startCalcTime = System.currentTimeMillis();
        while(!found(frontier.peek())){
            Pair pair=frontier.remove();
            //System.out.println(pair.toString());
            if (!visited.contains(pair)) {
                visited.add(pair);
                frontier.addAll(successor(pair));
            }
            if (frontier.isEmpty())return null;
        }
        System.out.println("Frontier size : " + frontier.size()
        + "\nVisited size : " + visited.size());
        return findBackPath(frontier.peek());
    }

    private Stack<Pair> findBackPath(Pair pair){
        Stack<Pair> result = new Stack<>();
        result.add(pair);
        Pair currPair = pair;
        System.out.println(currPair.toString());
        while(!currPair.equals(start_pair)){
            currPair = currPair.getParent();
            result.add(currPair);
            System.out.println(currPair.toString());
        }

        //set calculation time
        System.out.println(counter);
        //System.out.println(Collections.max(mem));
        board.SetMoves(counter);
        board.SetCalcTime(System.currentTimeMillis() - startCalcTime);
        return result;
    }

    @Override
    public ArrayList<Pair> successor(Pair pos) {

        //add memory needed at that moment
        Runtime runtime = Runtime.getRuntime();
        long memoryMax = runtime.maxMemory();
        System.out.println(memoryMax);
        Long mem1=memoryMax - runtime.freeMemory();
        //mem.add(mem1);
        System.out.println("memory spent:"+mem1);
        //mem.add(mem1);
        //count moves
        counter++;
        ArrayList<Pair> result = new ArrayList<Pair>();
        short ch =board.getCh(pos);
        if((ch & 1) == 0){
            Pair newPair = new Pair(pos.getX()-1,pos.getY(),pos);
            if (!visited.contains(newPair))result.add(newPair);
        }
        if((ch & 4) == 0){
            Pair newPair = new Pair(pos.getX()+1,pos.getY(),pos);
            if (!visited.contains(newPair))result.add(newPair);
            //result.add(board.getDPair(pos,1,0));
        }
        if((ch & 2) == 0){
            Pair newPair = new Pair(pos.getX(),pos.getY()-1,pos);
            if (!visited.contains(newPair))result.add(newPair);
            //result.add(board.getDPair(pos,0,-1));
        }
        if((ch & 8) == 0){
            Pair newPair = new Pair(pos.getX(),pos.getY()+1,pos);
            if (!visited.contains(newPair))result.add(newPair);
            //result.add(board.getDPair(pos,0,1));
        }
        return result;
    }

    @Override
    public boolean found(Pair pair) {
        return board.isEnd(pair);
    }
}
