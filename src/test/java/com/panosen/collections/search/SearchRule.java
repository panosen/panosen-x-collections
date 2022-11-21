package com.panosen.collections.search;

import com.panosen.collections.Condition;
import org.elasticsearch.index.query.QueryBuilder;

public abstract class SearchRule {

    public abstract boolean disabled();

    public abstract Condition<QueryBuilder> match(SearchRequest searchRequest);
}
