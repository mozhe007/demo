package me.jdto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseDTO {
    @JsonIgnore
    protected JsonObject jsonObject;

    public BaseDTO() {
        jsonObject = new JsonObject();
    }

    public BaseDTO(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(String key) {
        return jsonObject.getString(key);
    }

    public void setString(String key, String value) {
        jsonObject.put(key, value);
    }

}