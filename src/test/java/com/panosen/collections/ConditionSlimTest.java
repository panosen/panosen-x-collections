package com.panosen.collections;

import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;

public class ConditionSlimTest {

    @Test
    public void slim() {
        Must<String> must = new Must<>();
        must.addMust().addShould().addSingle("x = 1");

        Should<String> should = must.addShould();
        should.addShould().addMust().addSingle("m = 3");

        String actual = parse(must);

        String expected = "(x = 1 && m = 3)";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void slim1() {
        Must<String> must = new Must<>();
        must.addSingle("x = 1");

        Condition<String> condition = must.slim();

        Assert.assertTrue(condition instanceof Single);
    }

    @Test
    public void slim2() {
        Should<String> should = new Should<>();
        should.addMust().addSingle("x = 1");

        Condition<String> condition = should.slim();

        Assert.assertTrue(condition instanceof Single);
    }

    private String parse(Condition<String> condition) {
        if (condition instanceof Single) {
            return ((Single<String>) condition).getValue();
        }

        if (condition instanceof Must) {
            return parse((Must<String>) condition, " && ");
        }

        if (condition instanceof Should) {
            return parse((Should<String>) condition, " || ");
        }

        return "";
    }

    private String parse(Conditions<String> conditions, String link) {
        if (conditions.getItems() == null || conditions.getItems().isEmpty()) {
            return "";
        }
        String text = conditions.getItems().stream().map(this::parse).filter(v -> !Strings.isNullOrEmpty(v)).collect(Collectors.joining(link));
        if (conditions.getItems().size() == 1) {
            return text;
        }
        return "(" + text + ")";
    }
}