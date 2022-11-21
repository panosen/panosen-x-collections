package com.panosen.collections;

import com.google.common.collect.Lists;

public final class Should<TValue> extends Conditions<TValue> {

    /**
     * 添加简单条件
     */
    public Should<TValue> addSingle(TValue value) {
        if (this.items == null) {
            this.items = Lists.newArrayList();
        }
        Single<TValue> single = new Single<>();
        single.setValue(value);
        this.items.add(single);
        return this;
    }

    /**
     * 添加简单条件
     */
    public Should<TValue> addSingle(Single<TValue> single) {
        if (this.items == null) {
            this.items = Lists.newArrayList();
        }
        this.items.add(single);
        return this;
    }
}
