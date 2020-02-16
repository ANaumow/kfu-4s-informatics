package ru.naumow.geo.interpretator;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<String, Object> attributes = new HashMap<>();

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public <T> T getAttribute(String key, Class<T> tClass) {
        return tClass.cast(getAttribute(key));
    }

    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    @Override
    public String toString() {
        return "Context{" +
                "attributes=" + attributes +
                '}';
    }
}
