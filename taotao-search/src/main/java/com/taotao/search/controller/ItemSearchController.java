package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.ItemSearchService;

@Controller
public class ItemSearchController {
	
	@Autowired
	private ItemSearchService itemSearchService;

	@RequestMapping("/query")
	@ResponseBody
	public TaotaoResult search(@RequestParam(value = "q") String queryString,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "60") Integer rows) throws UnsupportedEncodingException {
		
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件是必须的参数");
		}
		queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		SearchResult result = null;
		try {
			result = itemSearchService.searchItem(queryString, page, rows);
			 
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return TaotaoResult.ok(result);
	}
}

