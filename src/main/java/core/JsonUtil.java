package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonUtil {

    private static Gson gson = new GsonBuilder().create();

    private JsonUtil() {}

    public static <T> T fromJson(String json, Class<T> type) {
        return gson.fromJson(json, type);
    }

    public static boolean isJsonValid(String json) {
        if (json == null) return false;
        try {
            return new JsonParser().parse(json).isJsonObject();
        } catch (JsonSyntaxException e) {
            return false;
        }
    }
}
