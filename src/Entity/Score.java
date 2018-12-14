package Entity;

public class Score {

    public int levelScore;

    public Score() {
        this.levelScore = 0;
    }

    public void addPoints(int points) {
        this.levelScore += points;
    }

    public int getLevelScore() {
        return this.levelScore;
    }
}
