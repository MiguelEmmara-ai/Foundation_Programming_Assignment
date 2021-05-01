package C_Stage;

/**
 * Created by Miguel Emmara
 */
public class Competitors {
    private String player_name;
    private double player_score;
    private double player_best_score;

    public Competitors() {
        setPlayer_name("");
        setPlayer_score(0);
        setPlayer_best_score(0);
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public double getPlayer_score() {
        return player_score;
    }

    public void setPlayer_score(double player_score) {
        this.player_score = player_score;
    }

    public double getPlayer_best_score() {
        return player_best_score;
    }

    public void setPlayer_best_score(double player_best_score) {
        this.player_best_score = player_best_score;
    }
}
