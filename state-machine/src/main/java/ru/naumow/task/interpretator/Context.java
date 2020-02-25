package ru.naumow.task.interpretator;

import ru.naumow.task.TaskRepository;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private Map<String, Object> attributes = new HashMap<>();

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public <T> T getAttribute(String key, Class<T> tClass) {
        try {
            return tClass.cast(getAttribute(key));
        } catch (NullPointerException e) {
            throw new IllegalStateException("No such key found in context: " + key);
        }
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
