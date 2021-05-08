package jloosa;

public class ActionPercept {
    
    private final boolean bump;
    private final boolean alive;
    private final boolean scream;
    
    public ActionPercept(boolean bump, boolean alive, boolean scream) {
	this.bump = bump;
	this.alive = alive;
	this.scream = scream;
    }

    /**
     * @return the bump
     */
    public boolean isBump() {
        return bump;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @return the scream
     */
    public boolean isScream() {
        return scream;
    }

}
