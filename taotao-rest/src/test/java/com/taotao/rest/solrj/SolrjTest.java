package com.taotao.rest.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrjTest {
	@Test
	public void addDocument() throws Exception {
		//创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8080/solr");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "apple");
		document.addField("item_price", 25);
		//把文档写入索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception {
		//创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8080/solr");
		//solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		//提交
		solrServer.commit();
	}
	
	@Test
	public void queryDocument() throws Exception {
		//创建连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8080/solr");
		//创建查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		System.out.println("共查询到记录数："+solrDocumentList.getNumFound());
		for(SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
		
		
	}
}
