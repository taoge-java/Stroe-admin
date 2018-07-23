package com.stroe.admin.service.goods;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.stroe.admin.annotation.Bean;
import com.stroe.admin.model.goods.GoodsCategory;
import com.stroe.admin.service.base.BaseService;
import com.stroe.admin.service.base.DefaultResult;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.util.StrKit;

/**
 * 品牌分类管理业务层
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@Bean
public class GoodsCategoryService extends BaseService{

	private static final Log LOG = Log.getLog(GoodsCategoryService.class);
	
	/**
	 * 类别列表
	 * @param pageNumber
	 * @return
	 */
	public Result list(int pageSize,int pageNumber,Kv condition){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS);
		try {
			StringBuilder context = new StringBuilder(" from goods_category where 1=1");
			List<Object> params = new ArrayList<Object>();
			//名称搜索
			if(StrKit.isNotEmpty(condition.getStr("name"))){
				context.append(" and name = ?");
				params.add(condition.getStr("name"));
			}
			//分类级别
			if(condition.getInt("level")>0){
				context.append(" and level = ?");
				params.add(condition.getInt("level"));
			}
			Page<GoodsCategory> page = GoodsCategory.dao.paginate(pageNumber, pageSize, "select *", context.toString(),params.toArray());
			result.setDefaultModel(page);
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAIL);
			LOG.error("查询异常",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 创建商品类别
	 * @param goodsCategory
	 * @return
	 */
	public Result create(GoodsCategory goodsCategory){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"创建成功");
		try {
			GoodsCategory category = GoodsCategory.dao.findFirst("select id from goods_category where name=?",goodsCategory.getStr("name"));
			if(category != null){
				resultCode = new ResultCode(ResultCode.FAIL,"类别已存在");
				result.setResultCode(resultCode);
				return result;
			}
			if(StrKit.isEmpty(goodsCategory.getInt("parent_id"))){
				goodsCategory.set("parent_id", 0);
			}
			goodsCategory.set("created_at", new Date());
			goodsCategory.save();
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAIL,"操作异常");
			LOG.error("操作异常",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 更新商品类别
	 * @param goodsCategory
	 * @return
	 */
	public Result update(int categoryId,String oldName,GoodsCategory goodsCategory){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"更新成功");
		try {
			if(!oldName.equals(goodsCategory.getStr("name"))){
				GoodsCategory category = GoodsCategory.dao.findFirst("select id from GoodsCategory where name=?",goodsCategory.getStr("name"));
			    if(category != null){
			    	resultCode = new ResultCode(ResultCode.FAIL,"该类别已存在");
			    	result.setResultCode(resultCode);
			    	return result;
			    }
			}
			goodsCategory.set("updated_at", new Date());
			goodsCategory.update();
		} catch (Exception e) {
			resultCode = new ResultCode(ResultCode.FAIL,"操作异常");
			LOG.error("操作异常",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 删除类别
	 * @param id
	 * @return
	 */
	public Result deleteById(int id){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"删除成功");
		try {
			GoodsCategory goodsCategory = GoodsCategory.dao.findById(id);
			result.setModel("goodsCategory", goodsCategory);
			GoodsCategory.dao.deleteById(id);
		}catch(Exception e){
			resultCode = new ResultCode(ResultCode.FAIL,"删除失败");
			LOG.error("删除失败",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
	
	/**
	 * 批量删除类别
	 * @param ids
	 * @return
	 */
	public Result deleteBatch(String ids){
		Result result = new DefaultResult();
		ResultCode resultCode = new ResultCode(ResultCode.SUCCESS,"批量删除成功");
		try {
			Db.update("delete from goods_category where id in(?)", ids.substring(0,ids.length()-1));
		}catch(Exception e){
			resultCode = new ResultCode(ResultCode.FAIL,"批量删除失败");
			LOG.error("批量删除失败",e);
		}
		result.setResultCode(resultCode);
		return result;
	}
}
