package org.byby.model;

public class Item {
    private String channel;
    private String market;
    private String type;
    private Data data;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Item{" +
                "channel='" + channel + '\'' +
                ", market='" + market + '\'' +
                ", type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
