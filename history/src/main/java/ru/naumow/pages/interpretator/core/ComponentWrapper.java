package ru.naumow.pages.interpretator.core;



import ru.naumow.pages.interpretator.Context;
import ru.naumow.pages.interpretator.core.anotation.CmdMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
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
            String expected = entry.getKey();
            String[] array = command.split(" ");
            String current = array[0];
            String[] args = Arrays.copyOfRange(array, 1, array.length);
            boolean matched = expected.equals(current);

                //System.out.println("matched: " + current + " " + expected);

            if (matched) {
                Method method = entry.getValue();
                boolean accessible = method.canAccess(component);
                method.setAccessible(true);
                method.invoke(component, context, args);
                method.setAccessible(accessible);
                return true;
            }
        }
        return false;
    }

}
