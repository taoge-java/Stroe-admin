package com.jfinal.test;


import java.util.List;

import org.junit.Test;

import com.jfinal.JfinalPlugin;
import com.jfinal.config.Plugins;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import com.stroe.admin.model.system.SystemAdmin;
import com.stroe.admin.util.PackageUtil;

public class JFTest {

	private static final String url = "jdbc:mysql://127.0.0.1/furniture?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	
	private static final String url1 = "jdbc:mysql://127.0.0.1/alone?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
	
	@SuppressWarnings("unused")
	@Test
	public  void jdbcTest() {
	    Plugins plugins = new Plugins();
	    DruidPlugin druid = new DruidPlugin(url, "root","123456");
	    plugins.add(druid);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin("furniture",druid);
		activeRecordPlugin.addMapping("goods_info", SystemAdmin.class);
		plugins.add(activeRecordPlugin);
		
		DruidPlugin druid1 = new DruidPlugin(url1, "root","123456");
	    plugins.add(druid1);
		ActiveRecordPlugin activeRecordPlugin1 = new ActiveRecordPlugin("alone",druid1);
//		activeRecordPlugin.addMapping("goods_info", GoodsInfo.class);
		plugins.add(activeRecordPlugin);
		JfinalPlugin.startPlugins(plugins);
		Record goodsInfo =Db.use("alone").findFirst("select * from goods_info where id=1");;
		System.out.println(goodsInfo);
	}
	
//	public static void main(String[] args) {
//		RedisPlugin redis = new RedisPlugin(CommonConstant.SESSION_CACHE_NAME,"localhost",6379,"taige123456");
//		redis.start();
//		Cache cache = Redis.use(CommonConstant.SESSION_CACHE_NAME);
//		cache.del(cache.keys("*").toArray());
//		UserSession userSession = Redis.use(CommonConstant.SESSION_CACHE_NAME).get("64225a3261594a079b884230e7a0f92e:session_id_key");
//		System.err.println(userSession);
//	}
	
	public static void main(String[] args) {
		List<Class<? extends com.jfinal.template.Directive>> list = PackageUtil.scanPackage("com.stroe.admin.directive");
		System.err.println(list);
	}
}
