package com.panosen.collections.search;

import com.google.common.collect.Lists;
import com.panosen.collections.Condition;
import com.panosen.collections.Must;
import com.panosen.collections.Should;
import com.panosen.collections.Single;
import org.elasticsearch.common.Strings;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EsQueryEngineTest {

    @Autowired
    private SearchRuleA searchRuleA;

    @Autowired
    private SearchRuleB searchRuleB;

    @Autowired
    private SearchRuleC searchRuleC;

    @Test
    public void sample() {

        //搜索请求
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setChannel("app");
        searchRequest.setOriginKeyword("sanya");
        searchRequest.setSuggestKeyword("三亚");

        //中间态的表达式树
        Must<QueryBuilder> queryTree = buildQueryTree(searchRequest);

        //构建最终提交给ES的QueryBuilder
        QueryBuilder finalQueryBuilder = parse(queryTree);

        //提交搜索
        SearchResponse searchResponse = visitEs(finalQueryBuilder);
    }


    /**
     * 构建中间态的搜索树
     */
    private Must<QueryBuilder> buildQueryTree(SearchRequest searchRequest) {

        //多搜索树
        Must<QueryBuilder> must = new Must<>();

        List<SearchRule> searchRuleList = buildSearchRuleList("app");
        if (searchRuleList == null || searchRuleList.isEmpty()) {
            return must;
        }

        for (SearchRule searchRule : searchRuleList) {
            if (searchRule.disabled()) {
                continue;
            }

            Condition<QueryBuilder> condition = searchRule.match(searchRequest);
            if (condition == null) {
                continue;
            }

            must.addCondition(condition);
        }
        return must;
    }

    /**
     * 获取本次需要使用到的规则列表
     * 【扩展1】从数据库中获取SearchRuleList
     * 【扩展2】扩展返回类型，为每个搜索规则增加权重
     * 【扩展3】修改返回类型，不再返回列表，改为返回一个树列表。在数据库中定制每个树
     */
    private List<SearchRule> buildSearchRuleList(String channel) {
        if (Strings.isNullOrEmpty(channel)) {
            return null;
        }

        List<SearchRule> searchRuleList = Lists.newArrayList();

        switch (channel) {
            case "app":
                searchRuleList.add(searchRuleA);
                break;
            case "h5":
                searchRuleList.add(searchRuleB);
                searchRuleList.add(searchRuleC);
                break;
            default:
                searchRuleList.add(searchRuleA);
                searchRuleList.add(searchRuleB);
                searchRuleList.add(searchRuleC);
                break;
        }

        return searchRuleList;
    }

    /**
     * 构建最终提交给ES的QueryBuilder
     */
    private QueryBuilder parse(Condition<QueryBuilder> condition) {
        if (condition instanceof Single) {
            return ((Single<QueryBuilder>) condition).getValue();
        }

        if (condition instanceof Must) {
            Must<QueryBuilder> must = (Must<QueryBuilder>) condition;
            if (must.getItems().size() == 1) {
                return parse(must.getItems().get(0));
            }

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (Condition<QueryBuilder> item : must.getItems()) {
                boolQueryBuilder.must(parse(item));
            }
            return boolQueryBuilder;
        }

        if (condition instanceof Should) {
            Should<QueryBuilder> should = (Should<QueryBuilder>) condition;
            if (should.getItems().size() == 1) {
                return parse(should.getItems().get(0));
            }

            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            for (Condition<QueryBuilder> item : should.getItems()) {
                boolQueryBuilder.should(parse(item));
            }
            return boolQueryBuilder;
        }
        return null;
    }

    private SearchResponse visitEs(QueryBuilder zz) {
        return new SearchResponse();
    }
}
