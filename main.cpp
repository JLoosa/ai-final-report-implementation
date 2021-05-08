
struct Environment {

	void doMoveForward(Player* player) {
		int nx = player->position.x + getDeltaX(player->direction);
		int ny = player->position.y + getDeltaY(player->direction);
		if (nx < 0 || nx >= nWorldSize || ny < 0 || ny >= nWorldSize) {
			ear.bPlayerBumpedWall = true;
		}
		else {
			player->position.x = nx;
			player->position.y = ny;
			ear.bPlayerDied = holes[nx][ny] || wumpus[nx][ny];
		}
	}


	EnvActionResponse* performAction(Action action, Player* player) {
		ear.bPlayerBumpedWall = false;
		ear.bPlayerHearedScream = false;
		ear.bPlayerDied = false;
		switch (action) {
		case Forward:
			doMoveForward(player);
			ear.bPlayerDied = true;
			break;
		case TurnLeft:
			player->direction = getTurnLeft(player->direction);
			break;
		case TurnRight:
			player->direction = getTurnRight(player->direction);
			break;
		case Grab:
			if (gold[player->position.x][player->position.y]) player->nScore += nScoreGold;
			gold[player->position.x][player->position.y] = false;
			break;
		case Shoot:
			// TODO Method stub
			break;
		default:
			break;
		}
		return &ear;
	}

};

struct KnowledgeBase {
private:
	EnvActionResponse actionQuery;
public:
	void tellPercept(EnvPosPerception *percept, int t) {
		// TODO Method stub
	}

	void tellResponce(EnvActionResponse *resp, int t) {
		// TODO Method stub
	}

	Action ask(int t) {
		// TODO Method stub
		return Forward;
	}

};

int main(int argc, char **argv) {
	srand(0xDEADBEEF);

	// Create the world struct
	Environment environment;
	// Build the world
	environment.build();

	KnowledgeBase knowledgeBase;
	Action action;
	EnvPosPerception *epp;
	EnvActionResponse *ear;
	Player player;

	int time = 0;
	while (player.bAlive) {
		// Look at environment
		epp = environment.getPerceptSentence(&player.position);
		// Update knowledge base
		knowledgeBase.tellPercept(epp, time);
		// Get an action
		action = knowledgeBase.ask(time);
		// Perform the action
		ear = environment.performAction(action, &player);
		// Update knowledge base
		knowledgeBase.tellResponce(ear, time);
		// Increment time
		time++;
	}

}
