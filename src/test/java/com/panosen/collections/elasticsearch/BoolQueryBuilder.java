package com.panosen.collections.elasticsearch;

public class BoolQueryBuilder extends QueryBuilder {
    public BoolQueryBuilder must(QueryBuilder parse) {
        return this;
    }

    public BoolQueryBuilder should(QueryBuilder parse) {
        return this;
    }
}
