package com.stroe.admin.redis;

import com.jfinal.plugin.redis.Redis;
import com.stroe.admin.constant.CommonConstant;

public class RedisUtil {
	
	public static void pub(String channel,String message){
	    Redis.use().getJedis().publish(channel, message);
	}
	
	public static void sub(String channel){
	    Redis.use().getJedis().subscribe(new RedisListener(), channel);
	}
	
	public static void close(){
		Redis.use().getJedis().close();
	}
	
	public static String set(String key,Object value){
		return Redis.use(CommonConstant.SESSION_CACHE_NAME).set(key, value);
	}
	
	public static String set(String key,Object value,int seconds){
		return Redis.use(CommonConstant.SESSION_CACHE_NAME).setex(key, seconds, value);
	}
	
	public static <T> T get(String key){
		return Redis.use(CommonConstant.SESSION_CACHE_NAME).get(key);
	}
	
	public static void delete(String key){
		 Redis.use(CommonConstant.SESSION_CACHE_NAME).del(key);
	}
}
