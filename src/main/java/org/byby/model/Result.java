package org.byby.model;

import java.util.List;

public class Result {
    private List<PriceItem> asks;
    private List<PriceItem> bids;

    public Result(List<PriceItem> asks, List<PriceItem> bids) {
        this.asks = asks;
        this.bids = bids;
    }

    public List<PriceItem> getAsks() {
        return asks;
    }

    public void setAsks(List<PriceItem> asks) {
        this.asks = asks;
    }

    public List<PriceItem> getBids() {
        return bids;
    }

    public void setBids(List<PriceItem> bids) {
        this.bids = bids;
    }
}
