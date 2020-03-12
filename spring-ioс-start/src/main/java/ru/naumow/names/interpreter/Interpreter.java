package ru.naumow.names.interpreter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.naumow.names.storage.NameEntryRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class Interpreter {

    private InterpreterCore core = new InterpreterCore();
    private Environment     env  = new Environment();

    private NameEntryRepository repository;

    @Autowired
    public void setRepository(NameEntryRepository repository) {
        this.repository = repository;
        this.env.addAttribute("nameStorage", this.repository);
    }

    public void handle(String input) {
        String[] temp = input.split(" ");
        String curCommand = null;
        String[] args = new String[0];

        if (temp.length == 1){
            curCommand = temp[0];
        } else if (temp.length > 1) {
            curCommand = temp[0];
            args = Arrays.copyOfRange(temp, 1, temp.length);
        }

        if (curCommand == null)
            throw new IllegalArgumentException("command is null");

        Method[] methods = this.core.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
            if (method.isAnnotationPresent(CmdMapping.class)) {
                String expected = method.getAnnotation(CmdMapping.class).value();
                if (curCommand.equals(expected)) {
                    method.setAccessible(true);
                    try {
                        method.invoke(core, env, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        method.setAccessible(false);
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
    }


}
