package com.stroe.admin.model.goods;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;

/**
 * 商品分类表
 * @author zengjintao
 * @version 1.0
 * @createTime 2018年3月30日下午7:59:42
 */
@TableBind(tableName = "goods_category")
public class GoodsCategory extends BaseModel<GoodsCategory>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final GoodsCategory dao = new GoodsCategory();

}
