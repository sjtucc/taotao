package com.taotao.rest.service;

public interface JedisClient {

	public abstract String get(String key);

	public abstract String set(String key, String value);

	public abstract String hget(String hkey, String key);

	public abstract long hset(String hkey, String key, String value);

	public abstract long incr(String key);

	public abstract long expire(String key, int second);

	public abstract long ttl(String key);
	
	public abstract long del(String key);
	
	public abstract long hdel(String hkey, String key);

}