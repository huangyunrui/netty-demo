package com.hyr.im.serializer;

public interface Serializer {
    byte JSON_SERIALIZER  = 1;
    Serializer DEFAULT = new JSONSerializer();

    /**
     *
     * @return
     */
    byte getJsonSerializerAlgorithm();

    byte[] serializer(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
