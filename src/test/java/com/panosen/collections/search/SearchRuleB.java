package com.panosen.collections.search;

import com.panosen.collections.Condition;
import com.panosen.collections.Must;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

//@Component
public class SearchRuleB extends SearchRule {

    @Override
    public boolean disabled() {
        return false;
    }

    @Override
    public Condition<QueryBuilder> match(SearchRequest searchRequest) {
        if (Strings.isNullOrEmpty(searchRequest.getSuggestKeyword())) {
            return null;
        }
        Must<QueryBuilder> must = new Must<>();
        must.addSingle(QueryBuilders.termQuery("name", searchRequest.getSuggestKeyword()));
        must.addShould()
                .addSingle(QueryBuilders.rangeQuery("product_count").lt(1))
                .addSingle(QueryBuilders.termQuery("tag", "cheap"));
        return must;
    }
}
