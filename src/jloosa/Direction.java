package jloosa;

public enum Direction {
    North(0,  1),
    East (1,  0),
    South(0, -1),
    West (-1, 0);
    
    private final short dx, dy;
    
    private Direction(int dx, int dy) {
	this.dx = (short) dx;
	this.dy = (short) dy;
    }
    
    public Direction getRight() {
	switch(this) {
	case North: return East;
	case East: return South;
	case South: return West;
	case West: return North;
	default:
	    return null;
	}
    }
    
    public Direction getLeft() {
	switch(this) {
	case North: return West;
	case East: return North;
	case South: return East;
	case West: return South;
	default:
	    return null;
	}
    }

    public short getDx() {
        return dx;
    }

    public short getDy() {
        return dy;
    }
    
}
