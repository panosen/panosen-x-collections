package com.panosen.collections.elasticsearch;

public class QueryBuilders {
    public static QueryBuilder termQuery(String name, String originKeyword) {
        return new QueryBuilder();
    }

    public static RangeQueryBuilder rangeQuery(String fieldName) {
        return new RangeQueryBuilder();
    }

    public static BoolQueryBuilder boolQuery() {
        return new BoolQueryBuilder();
    }
}
