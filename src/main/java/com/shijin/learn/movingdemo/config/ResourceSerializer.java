package com.shijin.learn.movingdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

public class ResourceSerializer implements RedisSerializer<Object> {
  private static final Logger LOGGER = LogManager.getLogger(ResourceSerializer.class);
  
  @Override
  public byte[] serialize(Object t) throws SerializationException {
    LOGGER.debug("serialize... {}", t);
    Assert.notNull(t, "The class must not be null");
    if (t instanceof ByteArrayResource) {
      return ((ByteArrayResource)t).getByteArray();
    } else {
      return null;
    }
    
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    LOGGER.debug("deserialize... length={}", bytes!=null?bytes.length:0);
    return new ByteArrayResource(bytes);
  }

  
}
