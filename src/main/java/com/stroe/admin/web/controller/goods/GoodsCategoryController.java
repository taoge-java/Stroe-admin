package com.stroe.admin.web.controller.goods;

import java.util.List;

import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.Kv;
import com.jfinal.upload.UploadFile;
import com.stroe.admin.annotation.Inject;
import com.stroe.admin.constant.CommonEnum.LevelType;
import com.stroe.admin.constant.CommonEnum.LogType;
import com.stroe.admin.model.goods.GoodsCategory;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.service.goods.GoodsCategoryService;
import com.stroe.admin.util.ResultCode;
import com.stroe.admin.web.controller.base.BaseController;


/**
 * 品牌分类管理
 * @author taoge
 * @version 1.0
 * @create_at 2017年8月27日
 */
@ControllerBind(controllerKey="/goods/category")
public class GoodsCategoryController extends BaseController{

	@Inject
	private GoodsCategoryService goodsCategoryService;
	
	
	public void index(){
		renderView("/goods/category/index.vm");
	}
	
	/**
	 * 商品类别列表
	 */
    public void list(){
    	int pageNumber = getParaToInt("pageNumber",1);
    	int pageSize = getParaToInt("pageSize",10);
    	String name = getPara("name");
    	int level = getParaToInt("level",0);
    	Kv condition = Kv.by("name", name).set("level",level);
    	Result result = goodsCategoryService.list(pageSize,pageNumber,condition);
		setAttr("page", result.getDefaultModel());
		renderView("/goods/category/list.vm");
	}
    
	public void add(){
		List<GoodsCategory> list = GoodsCategory.dao.find("select id,name from goods_category where level = ?",LevelType.TOP_CATEGORY.getValue());
		setAttr("list", list);
		renderView("/goods/category/add.vm");
	}
	
	/**
	 * 图片上传
	 */
	public void uploadImg(){
		UploadFile uploadFile = getFile("file");
		String path = uploadRename(uploadFile);
		renderJson(new ResultCode(ResultCode.SUCCESS, path));
	}
	
	/**
	 * 删除单条类别
	 */
	public void deleteById(){
		int id = getParaToInt("id");
		Result result = goodsCategoryService.deleteById(id);
		if(result.isSuccess()){
			systemLog("删除类别", LogType.DELETE.getValue());
		}
		renderJson(result.getResultCode());
	}
	
	/**
	 * 批量删除类别
	 */
    public void deleteBatch(){
		String ids = getPara("ids");
		Result result = goodsCategoryService.deleteBatch(ids);
		renderJson(result.getResultCode());
	}
    
	/**
	 * 创建商品类别
	 */
    public void create(){
		GoodsCategory goodsCategory = getModel(GoodsCategory.class, "goodsCategory");
		Result result = goodsCategoryService.create(goodsCategory);
		renderJson(result.getResultCode());
	}

    /**
     * 编辑类别
     */
    public void edit(){
    	int categoryId = getParaToInt(0,0);
    	GoodsCategory goodsCategory = GoodsCategory.dao.findById(categoryId);
    	List<GoodsCategory> list = GoodsCategory.dao.find("select id,name from goods_category where level = ?",LevelType.TOP_CATEGORY.getValue());
		setAttr("list", list);
    	setAttr("goodsCategory", goodsCategory);
    	renderView("/goods/category/edit.vm");
	}
    
    /**
     * 更新类别
     */
    public void update(){
    	int categoryId = getParaToInt(0,0);
    	GoodsCategory category = GoodsCategory.dao.findById(categoryId);
    	String oldName = category.getStr("name");
    	category.getParamters(getParaMap());
    	Result result = goodsCategoryService.update(categoryId,oldName,category);
    	renderJson(result.getResultCode());
	}
}
