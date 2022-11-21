package com.panosen.collections.search;

import com.panosen.collections.Condition;
import com.panosen.collections.Must;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

@Component
public class SearchRuleA extends SearchRule {

    @Override
    public boolean disabled() {
        return true;
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
