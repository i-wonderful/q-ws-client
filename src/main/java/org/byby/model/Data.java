package org.byby.model;

import java.util.Arrays;

public class Data  {
    private double time;
    private long checksum;
    private String action;
    private double[][] bids;
    private double[][] asks;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getChecksum() {
        return checksum;
    }

    public void setChecksum(long checksum) {
        this.checksum = checksum;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double[][] getBids() {
        return bids;
    }

    public void setBids(double[][] bids) {
        this.bids = bids;
    }

    public double[][] getAsks() {
        return asks;
    }

    public void setAsks(double[][] asks) {
        this.asks = asks;
    }

    @Override
    public String toString() {
        return "Data{" +
                "time=" + time +
                ", checksum=" + checksum +
                ", action='" + action + '\'' +
                ", bids=" + Arrays.deepToString(bids) +
                ", asks=" + Arrays.deepToString(asks) +
                '}';
    }
}
