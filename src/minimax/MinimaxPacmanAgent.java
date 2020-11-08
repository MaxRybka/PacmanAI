package minimax;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

import static java.lang.Math.abs;

public class MinimaxPacmanAgent {

    private int depth = 6;
    private float maxPathLength = 0;
    private Board board;

    public MinimaxPacmanAgent(Board board) {
        this.board = board;

        maxPathLength = (float)(Math.sqrt(2) * board.getNBlocks());
    }

    private Stack<Pair> pathToClosest = null;

    public Pair MinimaxDecision(Pair currentPair) {
        float maxValue = -Float.MAX_VALUE;
        boolean isDeadEnd = true;

        ArrayList<Pair> availableMoves = successor(currentPair);
        Pair result = availableMoves.get(0);
        for (Pair move:availableMoves) {
            //depth = 0;
            float currentValue = MinValue(move,0,0, (ArrayList<Pair>) board.getGhostPositions().clone(), maxValue);

            float value =  currentValue + maxUtility(move,board.getGhostPositions());
            if(value > maxValue){
                maxValue = value;
                result = move;
            }

            //check for deadEnd
            if(value!=0) isDeadEnd = false;
        }

        //in case there is no targets or ghosts nearby try to find the path to closest targsset
        if(isDeadEnd){
            if(pathToClosest == null)
                pathToClosest = getDirection(currentPair,availableMoves);

            result = pathToClosest.pop();
            if(pathToClosest.isEmpty()) pathToClosest = null;
        }else{
            //delete stack
            pathToClosest = null;
        }

//        System.out.println("\nTarget value : " + maxValue + " result : " + result.toString());
//        System.out.println("\n");
        return result;
    }

    private float MaxValue(Pair currentPair, int depth, ArrayList<Pair> ghostPositions, float minValue){
        float value = 0;

        if(depthReached(depth))
            return maxUtility(currentPair,ghostPositions);

        ArrayList<Pair> availableMoves = successor(currentPair);
        for (int i = 0; i < availableMoves.size(); i++) {
            Pair move = availableMoves.get(i);

            if (i==0) {
                value = MinValue(move, 0, depth + 1, ghostPositions, -Float.MAX_VALUE);
            }
            else {
                float currentValue = MinValue(move, 0, depth + 1, ghostPositions, value);

                value = Float.max(value, currentValue);
            }

            if(value>minValue) break;
        }

        //System.out.println("\nMax value : " + value + "\nUtility value : " +maxUtility(currentPair,ghostPositions) +"\nCurrentPair : " + currentPair.toString());
        return value + maxUtility(currentPair,ghostPositions);
    }

    private float MinValue(Pair currentPair, int ghostCounter, int depth, ArrayList<Pair> ghostPositions, float maxValue){
        Pair ghostPair = ghostPositions.get(ghostCounter);

        float value = 0;
        float utilityValue = 0;

        //calculate utility value for current ghost position
        ArrayList<Pair> availableMoves = successor(ghostPair);
        for (Pair move: availableMoves) {
            utilityValue+= minUtility(move,currentPair) * (1f/availableMoves.size());
        }

        if(depthReached(depth))return utilityValue;

        for (int i = 0; i < availableMoves.size(); i++) {
            ghostPositions.set(ghostCounter,availableMoves.get(i));

            if (ghostCounter < (ghostPositions.size()-1)) {
                if(i==0) {
                    value = MinValue(currentPair, ghostCounter + 1, depth + 1, ghostPositions,-Float.MAX_VALUE);
                }
                else {
                    value = Float.min(value, MinValue(currentPair, ghostCounter + 1, depth + 1, ghostPositions,-Float.MAX_VALUE));
                }
            }
            else {
                if(i==0) value = MaxValue(currentPair, depth + 1, ghostPositions,Float.MAX_VALUE);
                else value = Float.min(value, MaxValue(currentPair, depth + 1, ghostPositions,value));

                if(value<maxValue) break;
            }
        }
        //System.out.println("\nMin value : " + value + "\nCurrentPair : " + currentPair.toString()+ "\nUtility value : " + utilityValue);
        return value + utilityValue;
    }

    // -100 - die from ghost
    // 10 - find point
    // 0 - nothing changes
    private float maxUtility(Pair state, ArrayList<Pair> ghostPositions) {
        float res = 0;
        if (ghostPositions.contains(state))
            res-=100;
        if (board.isEnd(state))
            res+=10;
        //calculate value depending on targets
//        for (Pair targetPos: board.getTargets()) {
//            float val = (maxPathLength - (abs(targetPos.getX() - state.getX()) + abs(targetPos.getY() - state.getY())))/100;
//            res += val;
//        }
        return res;
    }

    // -100 - kill pacman
    // 0 - nothing changes
    private int minUtility(Pair state, Pair pacmanPos) {
        if (pacmanPos.equals(state))
            return -100;
        return 0;
    }

    private boolean depthReached(int depth){
        return depth > this.depth;
    }

    private Stack<Pair> getDirection(Pair pacmanPos, ArrayList<Pair> successors){
        HeuristicPairComparator targetComparator = new HeuristicPairComparator(pacmanPos);
        PriorityQueue<Pair> closestTargets = new PriorityQueue<Pair>(targetComparator){{
            addAll(board.getTargets());
        }};

        HeuristicPacmanAgent agent = new HeuristicPacmanAgent(pacmanPos,closestTargets.peek(),board);

        return  agent.startAgent();
    }

    private ArrayList<Pair> successor(Pair pos) {
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
}
