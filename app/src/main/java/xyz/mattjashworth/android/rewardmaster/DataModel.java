package xyz.mattjashworth.android.rewardmaster;

/**
 * Created by mattjashworth on 14/05/2019.
 * For AttackMaster.
 */

public class DataModel {

    String reward;
    String date;
    String URI;

    public DataModel(String reward, String date, String URI) {
        this.reward=reward;
        this.date=date;
        this.URI=URI;

    }

    public String getReward() {
        return reward;
    }

    public String getDate() {
        return date;
    }


    public String getURI() {
        return URI;
    }

}