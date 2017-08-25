package com.stroe.admin.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.stroe.admin.interceptor.ViewContextInterceptor;
import com.stroe.admin.model.BaseModel;

public class SysConfig extends JFinalConfig{
	
	public static final String BASE_VIEW="/WEB-INF/views";
	
	public static  String redisHost;
	
	public static String uploadPath;
	
	public static String uploadDown;
	
	private static String redisPassword;
	
	private Prop proKit = null;
	
	@Override
	public void configConstant(Constants constants) {
		constants.setViewType(ViewType.VELOCITY);
		constants.setDevMode(true);
		proKit = PropKit.use("config.properties");//加载config配置文件
		redisHost = proKit.get("db.redis.host");
		redisPassword = proKit.get("db.redis.password");
		uploadPath = proKit.get("resource.upload.path");
		uploadDown = proKit.get("resource.upload.domain");
		
		constants.setBaseUploadPath(uploadPath);//设置文件上传路径
		constants.setError404View(BASE_VIEW+"/error/404.vm");
		constants.setError500View(BASE_VIEW+"/error/500.vm");
	}

	
	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new ContextPathHandler("BASE_PATH"));
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new ViewContextInterceptor());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configPlugin(Plugins plugins) {
		/**配置cp3p0数据库连接池**/
		DruidPlugin druid = new DruidPlugin(proKit.get("jdbcUrl"), proKit.get("user"), proKit.get("password"));
		plugins.add(druid);
		//配置数据表自动路由映射
		AutoTableBindPlugin table=new AutoTableBindPlugin(druid);
		table.autoScan(true);
		table.addExcludeClasses(BaseModel.class);
		plugins.add(table);
		//配置redis插件
		RedisPlugin redis=new RedisPlugin("stroe", redisHost,6379,redisPassword);
		plugins.add(redis);
		//配置缓存插件
		plugins.add(new EhCachePlugin());
	}

	@Override
	public void configRoute(Routes routes) {
		routes.setBaseViewPath(BASE_VIEW);
		routes.add(new AutoBindRoutes());
	}

	@Override
	public void afterJFinalStart() {
       
	}


	@Override
	public void configEngine(Engine engine) {
		
	}
}
