package jloosa;

public class Player {

    private int nPosX, nPosY;
    private Direction direction;
    private boolean bAlive;
    private int nScore, nArrowsUsed;
    
    public Player() {
	setNPosX(0);
	setNPosY(0);
	setDirection(Direction.North);
	setAlive(true);
	setNScore(0);
	setNArrowsUsed(0);
    }
    
    public int getNPosX() {
        return nPosX;
    }
    public void setNPosX(int nPosX) {
        this.nPosX = nPosX;
    }
    public int getNPosY() {
        return nPosY;
    }
    public void setNPosY(int nPosY) {
        this.nPosY = nPosY;
    }
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public boolean isAlive() {
        return bAlive;
    }
    public void setAlive(boolean bAlive) {
        this.bAlive = bAlive;
    }
    public int getNScore() {
        return nScore;
    }
    public void setNScore(int nScore) {
        this.nScore = nScore;
    }
    public int getNArrowsUsed() {
        return nArrowsUsed;
    }
    public void setNArrowsUsed(int nArrowsUsed) {
        this.nArrowsUsed = nArrowsUsed;
    }
    
}
