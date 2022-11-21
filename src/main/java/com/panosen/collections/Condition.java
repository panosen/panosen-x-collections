package com.panosen.collections;

/**
 * abstract Condition
 */
public abstract class Condition<TValue> {

    /**
     * 返回一个精简后的Condition，减少层次的嵌套
     */
    public Condition<TValue> slim() {
        return slim(this);
    }

    /**
     * 返回一个精简后的数
     */
    private static <T> Condition<T> slim(Condition<T> _this) {
        if (_this instanceof Single) {
            return _this;
        }

        if (_this instanceof Must) {
            Must<T> must = (Must<T>) _this;
            if (must.getItems() == null || must.getItems().isEmpty()) {
                return null;
            }
            if (must.getItems().size() == 1) {
                return slim(must.getItems().get(0));
            }

            Must<T> newMust = new Must<>();
            for (Condition<T> item : must.getItems()) {
                newMust.addCondition(slim(item));
            }
            return newMust;
        }

        if (_this instanceof Should) {
            Should<T> should = (Should<T>) _this;
            if (should.getItems() == null || should.getItems().isEmpty()) {
                return null;
            }
            if (should.getItems().size() == 1) {
                return slim(should.getItems().get(0));
            }

            Should<T> newShould = new Should<>();
            for (Condition<T> item : should.getItems()) {
                newShould.addCondition(slim(item));
            }
            return newShould;
        }
        return null;
    }
}
