package ru.naumow.geo.interpretator;

import ru.naumow.geo.interpretator.core.ComponentWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CommandExecutor {

    private Context context;

    private List<ComponentWrapper> componentWrapperList;

    public CommandExecutor(Context context, Object... components) {
        this.context = context;
        this.componentWrapperList = new ArrayList<>();
        for (Object component : components) {
            componentWrapperList.add(new ComponentWrapper(component));
        }
    }

    public void execute(String command) {
        for (ComponentWrapper component : componentWrapperList) {
            try {
                if (component.respond(context, command)) {
                    return;
                }
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
