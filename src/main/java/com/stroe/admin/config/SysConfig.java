package com.stroe.admin.config;

import java.util.List;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.RenderingTimeHandler;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.stroe.admin.constant.CommonConstant;
import com.stroe.admin.directive.annotation.Directive;
import com.stroe.admin.model.BaseModel;
import com.stroe.admin.redis.RedisCacheManger;
import com.stroe.admin.redis.RedisListener;
import com.stroe.admin.service.base.BaseService;
import com.stroe.admin.service.base.DefaultResult;
import com.stroe.admin.service.base.Result;
import com.stroe.admin.spring.SpringBeanManger;
import com.stroe.admin.util.ClassUtil;
import com.stroe.admin.util.PackageUtil;
import com.stroe.admin.web.handler.ContextPathHandler;
import com.stroe.admin.web.handler.SessionHandler;
import com.stroe.admin.web.handler.WebSocketHandler;
import com.stroe.admin.web.interceptor.AopInterceptor;
import com.stroe.admin.web.interceptor.PermissionInterceptor;
import com.stroe.admin.web.interceptor.ViewContextInterceptor;

/**
 * Jfinal Aip引导式配置
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年6月8日 下午9:13:31
 */
public  class SysConfig extends JFinalConfig{
	
	private static final Log LOG = Log.getLog(SysConfig.class);
	
	private static final RedisCacheManger redisCacheManger = RedisCacheManger.getRedisCacheManger();
	
	public final static String BASE_VIEW = "/WEB-INF/views";//页面存放路径
	
	public static String redisHost; // redis主机
	
	public static String channels;//redis订阅频道
	
	public static String redisPassword; // redis密码
	
	public static String resourceUpload;//文件上传路径
	
	public static String resourceDown;
	
	public static String cookie_name;
	
	public static String  weixinToken;
	

	@Override
	public void configConstant(Constants constants) {
		 constants.setDevMode(true);
		 constants.setViewType(ViewType.JFINAL_TEMPLATE);
		 constants.setEncoding("utf-8");
		 JFinal.me().getConstants().setError404View(BASE_VIEW+"/common/404.vm");
		 JFinal.me().getConstants().setError500View(BASE_VIEW+"/common/500.vm");
		 PropKit.use("config.properties");//加载配置文件
		 channels=PropKit.get("redis.channels").trim();
		 redisPassword = PropKit.get("redis.password").trim();
		 redisHost = PropKit.get("redis.host").trim();
		 resourceUpload=PropKit.get("resource.upload.path").trim();
		 resourceDown=PropKit.get("resource.upload.path").trim();
		 weixinToken=PropKit.get("weixin.token").trim();
		// cookie_name=PropKit.get("cookie.name").trim();
		 constants.setBaseDownloadPath(resourceUpload);
	}
	@Override
	public void configRoute(Routes routes) {
		routes.add(new AutoBindRoutes());
	}
	
	
	@Override
	public void configEngine(Engine engine) {
		List<Class<? extends com.jfinal.template.Directive>> list = PackageUtil.scanPackage("com.stroe.admin.directive");
		for (Class<? extends com.jfinal.template.Directive> cla : list) {
			Directive directive = cla.getAnnotation(Directive.class);
			if(directive != null){
				try {
					engine.addDirective(directive.name(), ClassUtil.newInstance(cla));
					LOG.debug("add directive "+ cla.getName());
				} catch (Exception e) {
					LOG.error("add directive "+ directive.name()+" fail", e);
					throw new RuntimeException();
				}
			}
	    }
		engine.addSharedFunction("/WEB-INF/views/macro/left_menu.vm");
		engine.addSharedFunction("/WEB-INF/views/macro/paginate.vm");
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void configPlugin(Plugins plugin) {
		/**
	     * 配置主数据库
	     */
		DruidPlugin primaryDruid = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
	    plugin.add(primaryDruid);
	    AutoTableBindPlugin primaryAtbp = new AutoTableBindPlugin(primaryDruid);
	    primaryAtbp.setBaseSqlTemplatePath(PathKit.getWebRootPath()+"/WEB-INF/classes");
	    primaryAtbp.addSqlTemplate("system.sql");
	    //如果你只想用注解而不想让没有注解的model被自动注册，则如下使用
	    primaryAtbp.autoScan(false);
	    primaryAtbp.addExcludeClasses(BaseModel.class);
	    primaryAtbp.setShowSql(true);
	    plugin.add(primaryAtbp);
 	   
	    plugin.add(new EhCachePlugin());//配置缓存插件
	    // 配置redis插件
	    RedisPlugin redis = new RedisPlugin(CommonConstant.SESSION_CACHE_NAME,redisHost,6379,redisPassword);
	    redis.getJedisPoolConfig().setMaxTotal(200);
	    redis.getJedisPoolConfig().setMaxIdle(200);
	    plugin.add(redis);
	    redisCacheManger.setCache(true);//设置启用分布式缓存
	    //aop自动注入插件
	    AopBeanPlugin beanPlugin=new AopBeanPlugin();
	    beanPlugin.setPackageName("com.stroe.admin.service");
	    beanPlugin.addExcludeClasses(BaseService.class);
	    beanPlugin.addExcludeClasses(DefaultResult.class);
	    beanPlugin.addExcludeClasses(Result.class);
	    beanPlugin.addExcludeClasses(BaseService.class);
	    plugin.add(beanPlugin);
	    
	}
	
	@Override
	public void configInterceptor(Interceptors interceptors) {
		interceptors.add(new PermissionInterceptor());
		interceptors.add(new ViewContextInterceptor());
		interceptors.add(new AopInterceptor(SpringBeanManger.getContext()));
		//interceptors.add(new ShiroInterceptor());
	}
	
	@Override
	public void configHandler(Handlers handlers) {
		handlers.add(new ContextPathHandler());
		handlers.add(new RenderingTimeHandler());
		handlers.add(new WebSocketHandler());
		handlers.add(new SessionHandler());
	}
	
	/**
	 * 初始化系统数据
	 */
	@Override
	public void afterJFinalStart() {
		new Thread(new Runnable() {
			@Override
			public void run() {
		         RedisListener redisListener = new RedisListener();
		         Redis.use().getJedis().subscribe(redisListener, channels);//订阅频道
				 LOG.info("消息订阅成功");
			}
		 }).start();
	}
	
}
