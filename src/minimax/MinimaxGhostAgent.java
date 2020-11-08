package minimax;

import java.util.ArrayList;
import java.util.Random;

public class MinimaxGhostAgent {
    private int depth = 6;
    private Board board;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public MinimaxGhostAgent(Board board) {
        this.board = board;
    }


    public Pair MinimaxDecision(Pair currentPair) {
        float maxValue = -Float.MAX_VALUE;
        boolean isDeadEnd = true;

        ArrayList<Pair> availableMoves = successor(currentPair);
        Pair result = availableMoves.get(0);
        for (int i = 0; i < availableMoves.size(); i++) {
            Pair move = availableMoves.get(i);
            //depth = 0;
            float value = MinValue(move, 0, this.board.getPacmanCurrentPosition(), maxValue) + maxUtility(move, this.board.getPacmanCurrentPosition());

            //check for deadEnd
            if (i!=0 && value != maxValue) isDeadEnd = false;

            if (value > maxValue) {
                maxValue = value;
                result = move;
            }


        }

        //in case there is no pacman nearby make random move
        if(isDeadEnd){
            result = availableMoves.get(getRandomInt(0,availableMoves.size()-1));
        }
//        System.out.println("\nTarget value : " + maxValue + " result : " + result.toString());
//        System.out.println("\n");

        return result;
    }

    private float MaxValue(Pair currentPair, int depth, Pair pacmanPosition, float minValue){
        float value = 0;

        if(depthReached(depth))
            return maxUtility(currentPair,pacmanPosition);

        ArrayList<Pair> availableMoves = successor(currentPair);
        for (int i = 0; i < availableMoves.size(); i++) {
            Pair move = availableMoves.get(i);

            if (i==0) value = MinValue(move, depth + 1, pacmanPosition, -Float.MAX_VALUE);
            else value = Float.max(value, MinValue(move, depth + 1, pacmanPosition , value));

            if(value>minValue) break;
        }

        //System.out.println("\nMax value : " + value + "\nUtility value : " +maxUtility(currentPair,ghostPositions) +"\nCurrentPair : " + currentPair.toString());
        return value + maxUtility(currentPair,pacmanPosition);
    }

    private float MinValue(Pair currentPair, int depth, Pair pacmanPosition, float maxValue){

        float value = 0;
        float utilityValue = minUtility(pacmanPosition,currentPair);

        //calculate utility value for current pacman position
        ArrayList<Pair> availableMoves = successor(pacmanPosition);
        for (Pair move: availableMoves) {
            utilityValue += minUtility(move,currentPair) * (1f/availableMoves.size());
        }

        if(depthReached(depth))return utilityValue;

        for (int i = 0; i < availableMoves.size(); i++) {
            Pair move = availableMoves.get(i);

            if(i==0) value = MaxValue(currentPair, depth + 1, move,Float.MAX_VALUE);
            else value = Float.min(value, MaxValue(currentPair, depth + 1, move,value));

            if(value<maxValue) break;
        }
        //System.out.println("\nMin value : " + value + "\nCurrentPair : " + currentPair.toString()+ "\nUtility value : " + utilityValue);
        return value + utilityValue;
    }

    // 100 - kill pacman
    // 0 - nothing changes
    private int maxUtility(Pair state, Pair pacmanPosition) {
        if (pacmanPosition.equals(state))
            return 100;
        return 0;
    }

    // 100 - ghost kills pacman
    // -10 - pacman eats target
    // 0 - nothing changes
    private int minUtility(Pair state, Pair ghostPos) {
        if (ghostPos.equals(state))
            return 100;
        if(board.isEnd(state))
            return -10;
        return 0;
    }

    private boolean depthReached(int depth){
        return depth > this.depth;
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

    public static int getRandomInt( int min, int max ){
        Random randomGenerator;
        randomGenerator = new Random();
        return  randomGenerator.nextInt( max+1 ) + min ;
    }
}
