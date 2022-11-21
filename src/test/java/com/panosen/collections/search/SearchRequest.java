package com.panosen.collections.search;

public class SearchRequest {

    private String channel;

    private String originKeyword;

    private String suggestKeyword;

    private boolean destSearch;

    private boolean sightSearch;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOriginKeyword() {
        return originKeyword;
    }

    public void setOriginKeyword(String originKeyword) {
        this.originKeyword = originKeyword;
    }

    public String getSuggestKeyword() {
        return suggestKeyword;
    }

    public void setSuggestKeyword(String suggestKeyword) {
        this.suggestKeyword = suggestKeyword;
    }

    public boolean isDestSearch() {
        return destSearch;
    }

    public void setDestSearch(boolean destSearch) {
        this.destSearch = destSearch;
    }

    public boolean isSightSearch() {
        return sightSearch;
    }

    public void setSightSearch(boolean sightSearch) {
        this.sightSearch = sightSearch;
    }
}
