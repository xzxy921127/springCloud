package com.xzxy.commons.http;

import com.xzxy.commons.json.Json;

public class HttpRespEntity {
    private byte[] content;

    public HttpRespEntity(byte[] content) {
        this.content = content;
    }


    public String toStr() throws Exception{
        return new String(content, "UTF-8");
    }

    public <T> T toGene(Class<T> clazz) throws Exception{
        String str = new String(content, "UTF-8");
        T t = Json.fromJson(Json.parse(str), clazz);
        return t;
    }

    public byte[] getRaw(){
        return content;
    }
}