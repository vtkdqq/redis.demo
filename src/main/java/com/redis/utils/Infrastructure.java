package com.redis.utils;

import redis.clients.jedis.Jedis;

public class Infrastructure
{
    static String constr = "192.168.100.60";

    public static Jedis getRedis()
    {
        Jedis jedis = new Jedis(constr);
        return jedis;
    }

    public static void main(String[] args)
    {
        Jedis j = Infrastructure.getRedis();
        String output;
        j.set("hello", "world");
        output = j.get("hello");
        System.out.println(output);
    }
}
