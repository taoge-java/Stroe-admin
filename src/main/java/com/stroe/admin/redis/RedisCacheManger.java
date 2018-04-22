package com.stroe.admin.redis;


import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.stroe.admin.constant.CommonConstant;

import redis.clients.jedis.JedisPubSub;

/**
 * redis缓存管理
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年10月9日上午11:34:08
 */
public class RedisCacheManger {
	
	private static final RedisCacheManger redisCacheManger = new RedisCacheManger();
	
	private RedisCacheManger(){}
	
	public static RedisCacheManger getRedisCacheManger() {
		return redisCacheManger;
	}
	
	private boolean hasCache = false;
	
	public boolean hasCache() {
		return hasCache;
	}

	public void setCache(boolean hasCache) {
		this.hasCache = hasCache;
	}

	public  void pub(String channel,String message){
	    Redis.use().getJedis().publish(channel, message);
	}
	
	public  void sub(JedisPubSub jedisPubSub,String channel){
		new Thread(new Runnable() {
			public void run() {
				Redis.use().getJedis().subscribe(jedisPubSub, channel);
			}
		}).start();
	}
	
	public  void close(){
		Redis.use().getJedis().close();
	}
	
	public  String set(String key,Object value){
		return Redis.use(CommonConstant.SESSION_CACHE_NAME).set(key, value);
	}
	
	public  String set(String key,Object value,int seconds){
		return Redis.use(CommonConstant.SESSION_CACHE_NAME).setex(key, seconds, value);
	}
	
	public  <T> T get(String key){
		return Redis.use(CommonConstant.SESSION_CACHE_NAME).get(key);
	}
	
	public  void delete(String key){
		 Redis.use(CommonConstant.SESSION_CACHE_NAME).del(key);
	}
	
	/**
	 * 清除cacheName中的所有缓存
	 */
	public  void deleteAll(){
		Cache cache = Redis.use(CommonConstant.SESSION_CACHE_NAME);
		cache.del(cache.keys("*"));
	}
	
}
