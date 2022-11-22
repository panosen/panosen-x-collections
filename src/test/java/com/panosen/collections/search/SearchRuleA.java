package com.panosen.collections.search;

import com.google.common.base.Strings;
import com.panosen.collections.Condition;
import com.panosen.collections.Must;
import com.panosen.collections.elasticsearch.QueryBuilder;
import com.panosen.collections.elasticsearch.QueryBuilders;

//@Component
public class SearchRuleA extends SearchRule {

    @Override
    public boolean disabled() {
        return false;
    }

    @Override
    public Condition<QueryBuilder> match(SearchRequest searchRequest) {
        if (Strings.isNullOrEmpty(searchRequest.getOriginKeyword())) {
            return null;
        }
        Must<QueryBuilder> must = new Must<>();
        must.addSingle(QueryBuilders.termQuery("name", searchRequest.getOriginKeyword()));
        return must;
    }
}
