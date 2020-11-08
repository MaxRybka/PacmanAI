package minimax;

public class Ghost {
    private Pair currentGridPosition;
    private Pair startGridPosition;
    private int speed;
    private int x,y,dx,dy;
    private int pixelBlockSize;
    private MinimaxGhostAgent agent;

    public Ghost(Pair startGridPosition, int speed, MinimaxGhostAgent agent, int blockSize) {
        this.currentGridPosition = startGridPosition;
        this.startGridPosition = startGridPosition;
        this.speed = speed;
        this.agent = agent;
        this.pixelBlockSize = blockSize;
        this.x = startGridPosition.getX()*blockSize;
        this.y = startGridPosition.getY()*blockSize;
    }

    public void makeMove(){
        setCurrentGridPosition(agent.MinimaxDecision(currentGridPosition));
        int x = getPixelX() / pixelBlockSize;
        int y = getPixelY() / pixelBlockSize;
        setDirections(currentGridPosition.getX() - x, currentGridPosition.getY() - y);
    }

    public Pair getCurrentGridPosition() {
        return currentGridPosition;
    }

    public void setCurrentGridPosition(Pair currentGridPosition) {
        this.currentGridPosition = currentGridPosition;
    }

    public Pair getStartGridPosition() {
        return startGridPosition;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public MinimaxGhostAgent getAgent() {
        return agent;
    }

    public void setAgent(MinimaxGhostAgent agent) {
        this.agent = agent;
    }

    public int getPixelX() {
        return x;
    }

    public int getPixelY() {
        return y;
    }

    public void setPixelPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDirections(int dx, int dy) {
        this.dy = dy;
        this.dx = dx;
    }
}
