package com.redis.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisList
{
    private static final String HOST = "localhost";
    private static final int PORT = 6379;

    public void teest()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);
        Jedis jedis = pool.getResource();
        // DEL 移除给定的一个或多个key。如果key不存在，则忽略该命令。
        jedis.del("mykey");

        // TTL 返回给定key的剩余生存时间(time to live)(以秒为单位)
        jedis.ttl("stringlists");

        // PERSIST key 移除给定key的生存时间。
        jedis.persist("foo");

        jedis.set("name", "wangjun1");
        jedis.set("id", "123456");
        jedis.set("address", "guangzhou");

        Set keys = jedis.keys("*");// 列出所有的key，查找特定的key如：redis.keys("foo")
        Iterator t1 = keys.iterator();
        while (t1.hasNext())
        {
            Object obj1 = t1.next();
            System.out.println(obj1);
        }

        jedis.lpush("sort", "1");
        jedis.lpush("sort", "4");
        jedis.lpush("sort", "6");
        jedis.lpush("sort", "3");
        jedis.lpush("sort", "0");

        List<String> list = jedis.sort("sort");// 默认是升序
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println(list.get(i));
        }

        jedis.hset("website", "google", "www.google.cn");
        jedis.hset("website", "baidu", "www.baidu.com");
        jedis.hset("website", "sina", "www.sina.com");

        List list2 = jedis.hmget("website", "google", "baidu", "sina");

        for (int i = 0; i < list2.size(); i++)
        {
            System.out.println(list2.get(i));
        }

        System.out.println(jedis.hget("website", "baidu"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("cardid", "123456");
        map.put("username", "jzkangta");
        jedis.hmset("hash", map);

        Map<String, String> map2 = jedis.hgetAll("hash");
        for (Map.Entry entry : map2.entrySet())
        {
            System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
        }

        System.out.println(jedis.hget("hash", "cardid"));

    }

    private void redis_list()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);
        Jedis jedis = pool.getResource();

        jedis.lpush("list", "abc");
        jedis.lpush("list", "xzc");
        jedis.lpush("list", "erf");
        jedis.lpush("list", "bnh");

        // LRANGE key start
        // stop返回列表key中指定区间内的元素，区间以偏移量start和stop指定。下标(index)参数start和stop都以0为底，也就是说，以0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。你也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推。
        List<String> list3 = jedis.lrange("list", 0, -1);
        for (String str : list3)
        {
            System.out.println(str);
        }
    }

    private void redis_set()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);
        Jedis jedis = pool.getResource();

        // SADD key member [member ...]将member元素加入到集合key当中。
        jedis.sadd("testSet", "s1");
        jedis.sadd("testSet", "s2");
        jedis.sadd("testSet", "s3");
        jedis.sadd("testSet", "s4");
        jedis.sadd("testSet", "s5");

        // SREM key member移除集合中的member元素。
        jedis.srem("testSet", "s5");

        // SMEMBERS key返回集合key中的所有成员。
        Set set = jedis.smembers("testSet");
        Iterator t1 = set.iterator();
        while (t1.hasNext())
        {
            Object obj1 = t1.next();
            System.out.println(obj1);
        }

        // SISMEMBER key member判断member元素是否是集合key的成员。是（true），否则（false）
        System.out.println(jedis.sismember("testSet", "s4"));
    }

    public static void main(String[] args)
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);
        Jedis jedis = pool.getResource();
        jedis.auth("root");
        jedis.flushDB();
        
        Set keys = jedis.keys("*");// 列出所有的key，查找特定的key如：redis.keys("foo")
        Iterator t1 = keys.iterator();
        while (t1.hasNext())
        {
            Object obj1 = t1.next();
            System.out.println(obj1);
        }
    }
}
