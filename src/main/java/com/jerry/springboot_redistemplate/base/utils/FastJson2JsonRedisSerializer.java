package com.jerry.springboot_redistemplate.base.utils;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import com.alibaba.fastjson.JSON;
import java.nio.charset.Charset;

public class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T>
{
    public static final Charset DEFAULT_CHARSET=Charset.forName("utf8");

    public Class<T> clazz;
    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
       if (t==null)
       {
           return new byte[0];
       }
       return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
      if(bytes==null||bytes.length<=0)
      {
          return null;
      }
      String str=new String(bytes,DEFAULT_CHARSET);
      return (T)JSON.parseObject(str,clazz);
    }
}
