package com.panosen.collections;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * of Must or Should
 */
public abstract class Conditions<TValue> extends Condition<TValue> {

    protected List<Condition<TValue>> items;

    public List<Condition<TValue>> getItems() {
        return items;
    }

    public void setItems(List<Condition<TValue>> items) {
        this.items = items;
    }

    public Conditions<TValue> addCondition(Condition<TValue> single) {
        if (this.items == null) {
            this.items = Lists.newArrayList();
        }
        this.items.add(single);
        return this;
    }

    public Must<TValue> addMust() {
        if (this.items == null) {
            this.items = Lists.newArrayList();
        }
        Must<TValue> must = new Must<>();
        this.items.add(must);
        return must;
    }

    public Should<TValue> addShould() {
        if (this.items == null) {
            this.items = Lists.newArrayList();
        }
        Should<TValue> should = new Should<>();
        this.items.add(should);
        return should;
    }
}
