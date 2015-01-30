package com.redis.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.redisson.Config;
import org.redisson.Redisson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.resis.bean.User;

public class RedisExample
{
    private static final String HOST = "192.168.110.134";
    private static final int PORT = 6379;

    public void mains()
    {
        // 1.初始化
        Config config = new Config();
        config.setConnectionPoolSize(10);
        config.addAddress("192.168.110.131:6379");
        Redisson redisson = Redisson.create(config);
        System.out.println("reids连接成功...");
        System.out.println(redisson.getSet("name"));

        Jedis jedis = new Jedis(HOST, PORT);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);

        jedis.set("fucking", "fucking");
        System.out.println(jedis.get("fucking"));
        jedis.append("fucking", "update fucking");
        System.out.println(jedis.get("fucking"));

        jedis.set("key001", "value001");
        jedis.set("key002", "value002");
        jedis.set("key003", "value003");
        System.out.println("已新增的3个键值对如下：");
        System.out.println(jedis.get("key001"));
        System.out.println(jedis.get("key002"));
        System.out.println(jedis.get("key003"));

        System.out.println("=============删=============");
        System.out.println("删除key003键值对：" + jedis.del("key003"));
        System.out.println("获取key003键对应的值：" + jedis.get("key003"));

        System.out.println("=============改=============");
        // 1、直接覆盖原来的数据
        System.out.println("直接覆盖key001原来的数据：" + jedis.set("key001", "value001-update"));
        System.out.println("获取key001对应的新值：" + jedis.get("key001"));
        // 2、直接覆盖原来的数据
        System.out.println("在key002原来值后面追加：" + jedis.append("key002", "+appendString"));
        System.out.println("获取key002对应的新值" + jedis.get("key002"));

        System.out.println("一次性新增key201,key202,key203,key204及其对应值："
                + jedis.mset("key201", "value201", "key202", "value202", "key203", "value203", "key204", "value204"));
        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："
                + jedis.mget("key201", "key202", "key203", "key204"));
        System.out.println("一次性删除key201,key202：" + jedis.del(new String[] { "key201", "key202" }));
        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："
                + jedis.mget("key201", "key202", "key203", "key204"));
        System.out.println();

        jedis.hset("test", "1", "cbdfsadsa");
        jedis.hset("test", "2", "2222222");
        // jedis.set("names", "你好");
        System.out.println(jedis.getSet("names", "猪猪"));
    }

    public void hast()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);

        Jedis jedis = pool.getResource();
        jedis.auth("123456789");

        System.out.println(jedis.hkeys("test"));
        System.out.println(jedis.hvals("test"));
        System.out.println(jedis.hgetAll("test"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("cardid", "123456");
        map.put("username", "jzkangta");
        jedis.hmset("map_test", map);
        System.out.println(jedis.hgetAll("map_test"));
    }

    private void redis_list()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);

        Jedis jedis = pool.getResource();
        jedis.auth("123456789");
        // 在头添加
        jedis.lpush("list_test", "hadoop", "hbase", "hive", "pig", "zookeeper");
        // 在末尾添加
        jedis.rpush("list_test", "java", "struts");
        // 返回长度
        jedis.llen("list_test");

        // 取值
        List<String> list = jedis.lrange("list_test", 0, -1);
        for (String string : list)
        {
            System.out.println(string);
        }

        jedis.lset("list1", 1, "10");
        jedis.hincrBy("list_test", "1", 1);

        jedis.set("c_t_1", "1");

        jedis.incr("c_t_1");

    }

    private void redis_set()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);

        Jedis jedis = pool.getResource();
        jedis.auth("123456789");
        // 添加
        jedis.sadd("sname", "minxr");
        jedis.sadd("sname", "jarorwar");
        jedis.sadd("sname", "闵晓荣");
        jedis.sadd("sname", "noname");

        jedis.sadd("set2", "minxr");
        jedis.sadd("set2", "j李四");
        jedis.sadd("set2", "张三");
        jedis.sadd("set2", "noname");

        System.out.println(jedis.smembers("sname"));

        System.out.println(jedis.spop("sname"));

        System.out.println(jedis.smembers("sname"));
        System.out.println(jedis.smembers("set2"));

        System.out.println(jedis.sdiffstore("sname", "set2"));

        // System.out.println(jedis.scard("sname"));// 返回集合的元素个数
        jedis.sdiffstore("n_set", "sname", "minxr");
        System.out.println(jedis.smembers("n_set"));

        System.out.println(jedis.lindex("list_test", 1));
        System.out.println(jedis.llen("list_test"));

        System.out.println("---" + jedis.sismember("sname", "minxr"));// 判断 minxr 是否是sname集合的元素
        System.out.println(jedis.srandmember("sname"));

        System.out.println(jedis.scard("sname"));

        jedis.sadd("city", "北京", "上海", "重启", "武汉");
        System.out.println("取出最上面的：" + jedis.spop("city"));
        System.out.println("随机取出一个值" + jedis.srandmember("city"));
        jedis.sadd("city2", "北京", "武汉", "河北", "张家界", "辽林");
        System.out.println("交集：" + jedis.sinter("city", "city1"));
        System.out.println("并集：" + jedis.sunion("city", "city1"));
        System.out.println("差集：" + jedis.sdiff("city", "city1"));

        Set<String> set = jedis.smembers("city");
        for (String str : set)
        {
            System.out.println(str);
        }
        System.out.println("-------------->" + jedis.smembers("city"));
    }

    private void redis_zset()
    {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);

        Jedis jedis = pool.getResource();
        jedis.auth("123456789");

        jedis.zadd("sose", 0, "car");
        jedis.zadd("sose", 0, "bike");

        jedis.zadd("sose", 0, "car");
        jedis.zincrby("sose", 4, "Hello");
        jedis.zrank("sose", "Hello");
        System.out.println(jedis.zrevrank("sose", "Hello"));
        System.out.println(jedis.zrevrange("sose", 0, -1));

        System.out.println(jedis.zrangeByScore("sose", 1, 3));
        System.out.println("集合中的个数--->" + jedis.zcard("sose"));

        Set<String> sose = jedis.zrange("sose", 0, -1);
        for (String str : sose)
        {
            System.out.println(str);
        }

        jedis.hset("Mcity", "c1", "北京");
        System.out.println("取值：" + jedis.hget("Mcity", "c1"));
        Map<String, String> mcity = new HashMap<String, String>();
        mcity.put("c1", "桂林");
        mcity.put("c2", "天津");
        mcity.put("c3", "合肥");
        jedis.hmset("Mcity2", mcity);
        List<String> list = jedis.hmget("Mcity2", "c1", "c2", "c3");
        for (String string : list)
        {
            System.out.println(string);
        }
        System.out.println(jedis.hlen("Mcity2"));

    }

    public static void main(String[] args)
    {

        JedisPool pool = new JedisPool(new JedisPoolConfig(), HOST);
        Jedis jedis = pool.getResource();
        jedis.auth("123456789");
        
        try
        {
            // jedis.set("key301", "测试五");
            // System.out.println(jedis.get("key301"));
            // System.out.println(jedis.del("key301"));
            // System.out.println("原先key301不存在时，新增key301：" + jedis.setnx("key301", "value301"));
            // System.out.println(jedis.get("key301"));
            // jedis.setnx("key301", "value302_new");
            // System.out.println(jedis.get("key301"));

            // 设置key的有效期，并存储数据
            // System.out.println("新增key303，并指定过期时间为2秒" + jedis.setex("key303", 2, "key303-2second"));
            // System.out.println("获取key303对应的值：" + jedis.get("key303"));

            // 2.测试Set集合
            // jedis.lpush("stringlists", "vector");
            // jedis.lpush("stringlists", "ArrayList");
            // jedis.lpush("stringlists", "vector");
            // jedis.lpush("stringlists", "vector");
            // jedis.lpush("stringlists", "LinkedList");
            // jedis.lpush("stringlists", "MapList");
            // jedis.lpush("stringlists", "SerialList");
            // jedis.lpush("stringlists", "HashList");
            // jedis.lpush("numberlists", "3");
            // jedis.lpush("numberlists", "1");
            // jedis.lpush("numberlists", "5");
            // jedis.lpush("numberlists", "2");

            System.out.println("所有元素-stringlists：" + jedis.lrange("stringlists", 0, -1));
            System.out.println("所有元素-numberlists：" + jedis.lrange("numberlists", 0, -1));
            // System.out.println(jedis.lrange("list_test", 0, -1));

            System.out.println("----->" + jedis.zrevrange("myzset ", 0, -1));
            // jedis.zrem("zset1", "c3");
            // System.out.println("----->" + jedis.zrange("zset1", 0, -1));

            // sose zrangeByScore
            System.out.println(jedis.zrangeByScore("zset1", 0, 1));

            // System.out.println(jedis.get("sose"));
            User user = new User();
            user.setId("1001");
            user.setName("dengwenjun");
            user.setPassword("123456");

        }
        finally

        {
            pool.returnResource(jedis);
        }
        pool.destroy();

    }
}
