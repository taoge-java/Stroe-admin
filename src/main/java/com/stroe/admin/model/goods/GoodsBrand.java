package com.stroe.admin.model.goods;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.stroe.admin.model.BaseModel;

/**
 * 商品品牌表
 * @author zengjintao
 * @version 1.0
 * @createTime 2018年3月31日下午1:20:09
 */
@TableBind(tableName = "goods_brand")
public class GoodsBrand extends BaseModel<GoodsBrand>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final GoodsBrand dao = new GoodsBrand();

}
