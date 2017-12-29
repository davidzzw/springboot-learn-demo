package com.zzw.util;

import com.zzw.bean.ContentSearchBean;
import com.zzw.constant.SearchConstant;
import com.zzw.entity.ContentEntity;
import com.zzw.enums.ContentSearchTermEnum;
import com.zzw.repository.ContentRepository;
import com.zzw.vo.PageBean;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElasticSearchUtil {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    private ContentRepository contentRepository;

    /**
     * 搜索内容分词
     */
    protected List<String> handlingSearchContent(String searchContent) {

        List<String> searchTermResultList = new ArrayList<>();
        // 按逗号分割，获取搜索词列表
        List<String> searchTermList = Arrays.asList(searchContent.split(SearchConstant.STRING_TOKEN_SPLIT));

        // 如果搜索词大于 1 个字，则经过 IK 分词器获取分词结果列表
        searchTermList.forEach(searchTerm -> {
            // 搜索词 TAG 本身加入搜索词列表，并解决 will 这种问题
            searchTermResultList.add(searchTerm);
            // 获取搜索词 IK 分词列表
            searchTermResultList.addAll(getIkAnalyzeSearchTerms(searchTerm));
        });

        return searchTermResultList;
    }

    /**
     * 调用 ES 获取 IK 分词后结果
     */
    protected List<String> getIkAnalyzeSearchTerms(String searchContent) {
        AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(),
                AnalyzeAction.INSTANCE, SearchConstant.INDEX_NAME, searchContent);
        ikRequest.setTokenizer(SearchConstant.TOKENIZER_IK_MAX);
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();

        // 循环赋值
        List<String> searchTermList = new ArrayList<>();
        ikTokenList.forEach(ikToken -> {
            searchTermList.add(ikToken.getTerm());
        });

        return handlingIkResultTerms(searchTermList);
    }

    /**
     * 如果分词结果：洗发水（洗发、发水、洗、发、水）
     * - 均为词，保留
     * - 词 + 字，只保留词
     * - 均为字，保留字
     */
    private List<String> handlingIkResultTerms(List<String> searchTermList) {
        Boolean isPhrase = false;
        Boolean isWord = false;
        for (String term : searchTermList) {
            if (term.length() > SearchConstant.SEARCH_TERM_LENGTH) {
                isPhrase = true;
            } else {
                isWord = true;
            }
        }

        if (isWord & isPhrase) {
            List<String> phraseList = new ArrayList<>();
            searchTermList.forEach(term -> {
                if (term.length() > SearchConstant.SEARCH_TERM_LENGTH) {
                    phraseList.add(term);
                }
            });
            return phraseList;
        }

        return searchTermList;
    }

    /**
     * 构造查询条件
     */
    private void buildMatchQuery(BoolQueryBuilder queryBuilder, List<String> searchTermList) {
        for (String searchTerm : searchTermList) {
            for (ContentSearchTermEnum searchTermEnum : ContentSearchTermEnum.values()) {
                queryBuilder.should(QueryBuilders.matchPhraseQuery(searchTermEnum.getName(), searchTerm));
            }
        }
        queryBuilder.minimumShouldMatch(SearchConstant.MINIMUM_SHOULD_MATCH);
    }

    /**
     * 构建筛选条件
     */
    private void buildFilterQuery(BoolQueryBuilder boolQueryBuilder, Integer type, String category) {
        // 内容类型筛选
        if (type != null) {
            BoolQueryBuilder typeFilterBuilder = QueryBuilders.boolQuery();
            typeFilterBuilder.should(QueryBuilders.matchQuery(SearchConstant.TYPE_NAME, type).setLenient(true));
            boolQueryBuilder.filter(typeFilterBuilder);
        }

        // 内容类别筛选
        if (!StringUtils.isEmpty(category)) {
            BoolQueryBuilder categoryFilterBuilder = QueryBuilders.boolQuery();
            categoryFilterBuilder.should(QueryBuilders.matchQuery(SearchConstant.CATEGORY_NAME, category).setLenient(true));
            boolQueryBuilder.filter(categoryFilterBuilder);
        }
    }

    public PageBean searchContent(ContentSearchBean contentSearchBean) {

        Integer pageNumber = contentSearchBean.getPageNumber();
        Integer pageSize = contentSearchBean.getPageSize();

        PageBean<ContentEntity> resultPageBean = new PageBean<>();
        resultPageBean.setPageNumber(pageNumber);
        resultPageBean.setPageSize(pageSize);

        // 构建搜索短语
        String searchContent = contentSearchBean.getSearchContent();
        List<String> searchTermList = handlingSearchContent(searchContent);

        // 构建查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        buildMatchQuery(boolQueryBuilder, searchTermList);

        // 构建筛选条件
        buildFilterQuery(boolQueryBuilder, contentSearchBean.getType(), contentSearchBean.getCategory());

        // 构建分页、排序条件

        Pageable pageable =  new PageRequest(pageNumber, pageSize);
        if (!StringUtils.isEmpty(contentSearchBean.getOrderName())) {
            pageable =  new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, contentSearchBean.getOrderName());;
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(boolQueryBuilder).build();

        Page<ContentEntity> contentPage = contentRepository.search(searchQuery);

        resultPageBean.setResult(contentPage.getContent());
        resultPageBean.setTotalCount((int) contentPage.getTotalElements());
        resultPageBean.setTotalPage((int) contentPage.getTotalElements() / resultPageBean.getPageSize() + 1);
        return resultPageBean;
    }
}
