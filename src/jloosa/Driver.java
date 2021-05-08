package jloosa;

import java.util.Random;

public class Driver {

    public static void main(String[] args) {
	Random random = new Random();
	Player player = new Player();
	Environment environment = new Environment(5, 5);
	environment.buildEnvironment(random);
	environment.printEnvironmentArray();
	
	Action action;
	EnvPercept ep;
	ActionPercept ap;
	do {
	    ep = environment.getEnvPercept(player.getNPosX(), player.getNPosY());
	    player.tell(ep);
	    action = player.ask();
	    ap = environment.getActionPercept(action, player);
	    player.tell(ap);
	} while (ap.isAlive());
    }

}
