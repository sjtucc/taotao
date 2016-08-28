package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EUTreeNode> getCatList(Long parentId) {
		
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//根据parentid查询子节点
		criteria.andParentIdEqualTo(parentId);
		//返回子节点列表
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//将类型转换为EUTreeNode
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbItemCat tb : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tb.getId());
			node.setState(tb.getIsParent()?"closed":"open"); //是叶子结点就是closed，不是就是open
			node.setText(tb.getName());
			resultList.add(node);
		}
		return resultList;

	}

}
