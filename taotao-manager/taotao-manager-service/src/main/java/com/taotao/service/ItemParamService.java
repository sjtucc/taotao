package com.taotao.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	TaotaoResult getItemParamByCid(long cid);   //保存商品分类时，判断是否已存在
	TaotaoResult insertItemParam(TbItemParam itemParam);
}
