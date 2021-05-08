package jloosa;

public class EnvPercept {

    private final boolean hasBreeze;
    private final boolean hasGlitter;
    private final boolean hasStench;

    public EnvPercept(boolean breeze, boolean glitter, boolean stench) {
	this.hasBreeze = breeze;
	this.hasGlitter = glitter;
	this.hasStench = stench;
    }

    public boolean hasBreeze() {
	return hasBreeze;
    }

    public boolean hasGlitter() {
	return hasGlitter;
    }

    public boolean hasStench() {
	return hasStench;
    }

}
