package com.taotao.search.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.common.utils.TaotaoResult;

public interface ItemService {
	TaotaoResult importItemToIndex() throws SolrServerException, IOException;
}
