package velocity;

import com.alibaba.fastjson.JSON;

public class JsonUtils {
    private static final JsonUtils instance = new JsonUtils();

    public static JsonUtils getInstance() {
        return instance;
    }

    public String toJson(Object object) {
        return JSON.toJSONString(object);
    }
}
