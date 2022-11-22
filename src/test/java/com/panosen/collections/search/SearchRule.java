package com.panosen.collections.search;

import com.panosen.collections.Condition;
import com.panosen.collections.elasticsearch.QueryBuilder;

public abstract class SearchRule {

    public abstract boolean disabled();

    public abstract Condition<QueryBuilder> match(SearchRequest searchRequest);
}
