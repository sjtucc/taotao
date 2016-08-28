package com.taotao.search.mapper;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

public interface ItemSearchDao {
	SearchResult searchItem(SolrQuery solrQuery) throws Exception ;
}
