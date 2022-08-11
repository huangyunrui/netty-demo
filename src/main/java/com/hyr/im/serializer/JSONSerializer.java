package com.hyr.im.serializer;


import com.alibaba.fastjson.JSON;

public class JSONSerializer implements Serializer{
    public static  Serializer INSTANCE = new JSONSerializer();
    /**
     * @return
     */
    @Override
    public byte getJsonSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
