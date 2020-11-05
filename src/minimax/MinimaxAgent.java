package minimax;

import java.util.ArrayList;

public class MinimaxAgent{

    private Board board;
    //private int depth = 0;

    public MinimaxAgent(Board board) {
        this.board = board;
    }

    public Pair MinimaxDecision(Pair currentPair) {
//        inputs: state, �������� ���� ���
//        v <- Max-Value(state)
//        return �� action � ������ Successors(state) � ��������� v
        float maxValue = 0;

        ArrayList<Pair> availableMoves = successor(currentPair);
        Pair result = availableMoves.get(0);
        for (Pair move:availableMoves) {
            //depth = 0;
            float value = MinValue(move,board.getGhostPositions().size(),0, board.getGhostPositions());
            if(value > maxValue){
                maxValue = value;
                result = move;
            }
        }

        System.out.println("Target value : " + maxValue);
        return result;
    }

    public float MaxValue(Pair currentPair, int depth, ArrayList<Pair> ghostPositions){
//        if Terminal-Test(state) then return Utility(state)
//        v <- -∞
//        for a,s in Successors(state) do
//            v <- Max(v, Min-Value(s))
//        return v
        float value = 0;
        if(depthReached(depth))return maxUtility(currentPair,ghostPositions);
        ArrayList<Pair> availableMoves = successor(currentPair);
        for (Pair move:availableMoves) {
            value = Float.max(value,MinValue(move,board.getGhostPositions().size(),depth+1, ghostPositions));
        }

        return value + maxUtility(currentPair,ghostPositions);
    }

    public float MinValue(Pair currentPair, int ghostCounter, int depth, ArrayList<Pair> ghostPositions){
//        if Terminal-Test(state) then return Utility(state)
//        v <- ∞
//        for a,s in Successors(state) do
//            v <- Min(v, Max-Value(s))
//        return v
        //TODO: move ghosts and find successor for each ghost

        float value = 0;
        if(depthReached(depth))return minUtility(currentPair);

        ArrayList<Pair> availableMoves = successor(currentPair);
        for (int i = 0; i < availableMoves.size(); i++) {
            Pair move = availableMoves.get(i);
            if (ghostCounter != 1)
                value = Float.min(value, MinValue(move, ghostCounter - 1, depth+1));
            else
                value = Float.min(value, MaxValue(move,depth+1));
        }

        return value + minUtility(currentPair);
    }

    // 10 - find point
    // -100 - die from ghost
    // 0 - nothing changes
    //
    public int maxUtility(Pair state, ArrayList<Pair> ghostPositions) {
        if (board.getGhostPositions().contains(state)) return -100;
        if (board.isEnd(state)) return 10;
        return 0;
    }

    // 100 - kill pacman
    // 0 - nothing changes
    public int minUtility(Pair state) {
        if (board.getPacmanPosition().equals(state)) return -100;
        return 0;
    }


    public boolean depthReached(int depth){
        return depth > 10;
    }

    public ArrayList<Pair> successor(Pair pos) {
        ArrayList<Pair> result = new ArrayList<Pair>();
        short ch = board.getCh(pos);
        if((ch & 1) == 0){
            Pair newPair = new Pair(pos.getX()-1,pos.getY(),pos);
            result.add(newPair);
        }
        if((ch & 4) == 0){
            Pair newPair = new Pair(pos.getX()+1,pos.getY(),pos);
            result.add(newPair);
        }
        if((ch & 2) == 0){
            Pair newPair = new Pair(pos.getX(),pos.getY()-1,pos);
            result.add(newPair);
        }
        if((ch & 8) == 0){
            Pair newPair = new Pair(pos.getX(),pos.getY()+1,pos);
            result.add(newPair);
        }
        return result;
    }

    public boolean found(Pair pos) {
        return false;
    }
}
