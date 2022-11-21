package com.panosen.collections;

public final class Single<TValue> extends Condition<TValue> {

    private TValue value;

    public Single() {
    }

    public Single(TValue value) {
        this.value = value;
    }

    public static <T> Single<T> of(T value) {
        return new Single<>(value);
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }
}
