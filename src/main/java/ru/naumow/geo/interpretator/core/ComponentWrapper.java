package ru.naumow.geo.interpretator.core;

import ru.naumow.geo.interpretator.Context;
import ru.naumow.geo.interpretator.core.anotation.CmdMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComponentWrapper {

    private Object component;

    private Map<String, Method> storage;

    public ComponentWrapper(Object component) {
        this.component = component;
        this.storage = List.of(component.getClass().getDeclaredMethods())
                .stream()
                .filter(x -> x.isAnnotationPresent(CmdMapping.class))
                .collect(Collectors.toMap(x -> x.getAnnotation(CmdMapping.class).value(), Function.identity()));
    }

    public boolean respond(Context context, String command) throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<String, Method> entry : storage.entrySet()) {
            if (command.startsWith(entry.getKey())) {
                Method method = entry.getValue();
                boolean accessible = method.canAccess(component);
                method.setAccessible(true);
                method.invoke(component, context, command.substring(entry.getKey().length() + 1).split(" "));
                method.setAccessible(accessible);
                return true;
            }
        }
        return false;
    }

}
