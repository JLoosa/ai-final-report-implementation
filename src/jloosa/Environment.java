package jloosa;

import java.util.Random;

public class Environment {

    public static final int nScoreGrabGold = 1000;
    public static final int nScoreFireArrow = -10;
    public static final int nScoreKillWumpus = 100;

    public static final float fChancePit = 0.2f;
    public static final float fChanceGold = 0.13f;
    public static final float fChanceWumpus = 0.15f;

    private final boolean[][] bArrWumpus;
    private final boolean[][] bArrGold;
    private final boolean[][] bArrPit;
    private final int sizeX, sizeY;

    public Environment(final int sizeX, final int sizeY) {
	this.sizeX = sizeX;
	this.sizeY = sizeY;
	this.bArrWumpus = new boolean[sizeY][sizeX];
	this.bArrGold = new boolean[sizeY][sizeX];
	this.bArrPit = new boolean[sizeY][sizeX];
    }
    
    private boolean checkBounds(int x, int y) {
	return 0 <= x && x < sizeX && 0 <= y && y < sizeY;
    }

    private boolean checkIndex(boolean[][] bArr, int x, int y) {
	if (!checkBounds(x, y)) return false;
	return bArr[y][x];
    }

    private boolean checkAdjacent(boolean[][] bArr, int x, int y) {
	return checkIndex(bArr, x, y) || checkIndex(bArr, x + 1, y) || checkIndex(bArr, x - 1, y) || checkIndex(bArr, x, y + 1) || checkIndex(bArr, x, y - 1);
    }

    private void setIndex(boolean[][] bArr, int x, int y, boolean value) {
	if (!checkBounds(x, y)) return;
	bArr[y][x] = value;
    }

    public void buildEnvironment(Random random) {
	for (int y = 0; y < sizeY; y++) {
	    for (int x = 0; x < sizeX; x++) {
		setIndex(bArrWumpus, x, y, random.nextFloat() < fChanceWumpus);
		setIndex(bArrGold, x, y, random.nextFloat() < fChanceGold);
		setIndex(bArrPit, x, y, random.nextFloat() < fChancePit);
	    }
	}
    }

    public EnvPercept getEnvPercept(int x, int y) {
	boolean breeze = checkAdjacent(bArrPit, x, y);
	boolean stench = checkAdjacent(bArrWumpus, x, y);
	boolean glitter = checkIndex(bArrGold, x, y);
	return new EnvPercept(breeze, glitter, stench);
    }
    
    public ActionPercept getActionPercept(Action action, Player player) {
	switch (action) {
	case Forward:
	    return actionForward(player);
	case Shoot:
	    return actionShoot(player);
	case TurnLeft:
	    return actionTurnLeft(player);
	case TurnRight:
	    return actionTurnRight(player);
	default:
	    break;
	}
	return null;
    }

    private ActionPercept actionShoot(Player player) {
	// TODO Auto-generated method stub
	return null;
    }

    private ActionPercept actionTurnRight(Player player) {
	player.setDirection(player.getDirection().getRight());
	boolean alive = checkIndex(bArrPit, player.getNPosX(), player.getNPosY()) || checkIndex(bArrWumpus, player.getNPosX(), player.getNPosY());
	return new ActionPercept(false, alive, false);
    }

    private ActionPercept actionTurnLeft(Player player) {
	player.setDirection(player.getDirection().getLeft());
	boolean alive = checkIndex(bArrPit, player.getNPosX(), player.getNPosY()) || checkIndex(bArrWumpus, player.getNPosX(), player.getNPosY());
	return new ActionPercept(false, alive, false);
    }

    private ActionPercept actionForward(Player player) {
	Direction playerDirection = player.getDirection();
	int px = player.getNPosX()+ playerDirection.getDx();
	int py = player.getNPosY()+ playerDirection.getDy();
	boolean bump = !checkBounds(px , py);
	if (!bump) {
	    player.setNPosX(px);
	    player.setNPosY(py);
	}
	boolean alive = checkIndex(bArrPit, player.getNPosX(), player.getNPosY()) || checkIndex(bArrWumpus, player.getNPosX(), player.getNPosY());
	return new ActionPercept(bump, alive, false);
    }

    public void printEnvironmentArray() {
	for (int y = 0; y < sizeY; y++) {
	    System.out.println("-".repeat(6 * sizeX + 1));
	    System.out.print("| ");
	    for (int x = 0; x < sizeX; x++) {
		System.out.print(checkIndex(bArrWumpus, x, y) ? "W" : " ");
		System.out.print(checkIndex(bArrGold, x, y) ? "G" : " ");
		System.out.print(checkIndex(bArrPit, x, y) ? "H" : " ");
		System.out.print(" | ");
	    }
	    System.out.println();
	}
	System.out.println("-".repeat(6 * sizeX + 1));
    }

}
