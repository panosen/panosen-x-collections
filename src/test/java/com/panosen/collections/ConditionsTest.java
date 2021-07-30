package com.panosen.collections;

import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;

public class ConditionsTest {

    @Test
    public void write() {
        Must<String> must = new Must<>();
        must.addSingle("x = 1");
        must.addSingle("y = 2");

        Should<String> should = must.addShould();
        should.addSingle("m = 3");
        should.addSingle("n = 4");

        String actual = parse(must);

        String expected = "(x = 1 && y = 2 && (m = 3 || n = 4))";

        Assert.assertEquals(expected, actual);
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