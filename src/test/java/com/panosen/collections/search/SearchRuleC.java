package com.panosen.collections.search;

import com.panosen.collections.Condition;
import com.panosen.collections.Single;
import com.panosen.collections.elasticsearch.QueryBuilder;
import com.panosen.collections.elasticsearch.QueryBuilders;

//@Component
public class SearchRuleC extends SearchRule {

    @Override
    public boolean disabled() {
        return false;
    }

    @Override
    public Condition<QueryBuilder> match(SearchRequest searchRequest) {
        String configValue = "true";
        if (configValue.equalsIgnoreCase("ok")) {
            return Single.of(QueryBuilders.termQuery("name", searchRequest.getOriginKeyword()));
        }
        return null;
    }
}
