package com.panosen.collections;

public final class Single<TValue> extends Condition<TValue> {

    private TValue value;

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }
}
