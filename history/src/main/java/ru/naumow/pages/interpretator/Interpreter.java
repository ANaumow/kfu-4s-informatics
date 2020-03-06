package ru.naumow.pages.interpretator;



import ru.naumow.pages.interpretator.core.ComponentWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private Context context;

    private List<ComponentWrapper> componentWrapperList;

    public Interpreter(Context context, Object... components) {
        this.context = context;
        this.componentWrapperList = new ArrayList<>();
        for (Object component : components) {
            componentWrapperList.add(new ComponentWrapper(component));
        }
    }

    public void execute(String command) {
        try {
            for (ComponentWrapper component : componentWrapperList) {
                if (component.respond(context, command)) {
                    return;
                }
            }
            throw new IllegalStateException("Unrecognizable");
        } catch (InvocationTargetException | IllegalAccessException | IllegalStateException e) {
            e.printStackTrace();
        }

    }

}
