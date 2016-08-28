package com.taotao.search.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.search.mapper.ItemSearchDao;
import com.taotao.search.mapper.ItemSearchDaoImpl;
import com.taotao.search.pojo.SearchResult;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

	
	@Autowired
	private ItemSearchDao itemSearchDao;
	
	@Override
	public SearchResult searchItem(String queryString, Integer page, Integer rows) throws Exception {
		//创建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		//查询条件
		solrQuery.setQuery(queryString);
		//分页条件
		solrQuery.setStart((page -1) * rows);
		solrQuery.setRows(rows);
		//高亮显示
		solrQuery.setHighlight(true);
		//设置高亮显示的域
		solrQuery.addHighlightField("item_title");
		//高亮显示前缀
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		//后缀
		solrQuery.setHighlightSimplePost("</em>");
		//设置默认搜索域
		solrQuery.set("df", "item_keywords");
		
		//执行查询
		SearchResult result = itemSearchDao.searchItem(solrQuery);
		
		//计算分页
		Long recordCount = result.getRecordCount();
		int pageCount = (int) (recordCount / rows);
		if (recordCount % rows > 0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		
		return result;
	}

}
