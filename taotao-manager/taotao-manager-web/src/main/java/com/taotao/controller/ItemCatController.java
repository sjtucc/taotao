package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")

public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;


	@RequestMapping("/list")
	@ResponseBody
	//如果id为null是使用默认值，也就是parentid为0的分类列表,第一次easyUI请求id为0
	public List<EUTreeNode> getCatList(@RequestParam(value="id", defaultValue="0") Long parentId) throws Exception {
		List<EUTreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}

}
