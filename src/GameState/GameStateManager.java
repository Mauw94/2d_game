package GameState;

import GameState.Levels.Level1State;
import GameState.Levels.Level2State;

import java.awt.*;

public class GameStateManager {
    private GameState[] gameStates;
    private int currentState;

    public static final int NUMGAMESTATES = 6;
    public static final int MENUSTATE = 0;
    public static final int HELPSTATE = 1;
    public static final int DEADSTATE = 2;
    public static final int LEVELENDSTATE = 3;
    public static final int LEVEL1STATE = 4;
    public static final int LEVEL2STATE = 5;

    public GameStateManager() {
        gameStates = new GameState[NUMGAMESTATES];

        currentState = MENUSTATE;
        loadState(currentState);
    }

    private void loadState(int state) {
        switch (state) {
            case MENUSTATE:
                gameStates[state] = new MenuState(this);
                break;
            case HELPSTATE:
                gameStates[state] = new HelpState(this);
                break;
            case DEADSTATE:
                gameStates[state] = new DeadState(this);
                break;
            case LEVELENDSTATE:
                gameStates[state] = new LevelEndState(this);
                break;
            case LEVEL1STATE:
                gameStates[state] = new Level1State(this);
                break;
            case LEVEL2STATE:
                gameStates[state] = new Level2State(this);
                break;
        }
    }

    private void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state) {
        unloadState(state);
        currentState = state;
        loadState(currentState);
    }

    public void update() {
        try {
            gameStates[currentState].update();
        } catch (Exception e) {}
    }

    public void draw(Graphics2D g) {
        try {
            gameStates[currentState].draw(g);
        } catch (Exception e) {}
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }
}
