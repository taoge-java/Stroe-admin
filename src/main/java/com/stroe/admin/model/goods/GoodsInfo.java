package com.stroe.admin.model.goods;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;

/**
 * 商品信息表
 * @author zengjintao
 * @version 1.0
 * @createTime 2018年3月31日下午1:18:42
 */
@TableBind(tableName = "goods_info")
public class GoodsInfo extends BaseModel<GoodsInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final GoodsInfo dao = new GoodsInfo();

}
