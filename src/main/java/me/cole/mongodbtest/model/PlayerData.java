package me.cole.mongodbtest.model;

public class PlayerData {

    private String uuid;
    private String rank;

    public PlayerData(String uuid, String rank) {
        this.uuid = uuid;
        this.rank = rank;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
