package ru.naumow;

import ru.naumow.task.Task;
import ru.naumow.task.TaskBuilder;
import ru.naumow.task.proxy.TaskProxyFactory;
import ru.naumow.task.proxy.permission.UserType;

import java.io.PrintWriter;
import java.util.EnumMap;
import java.util.List;

public class AppProxy {

    public static void main(String[] args) {
        Task task;
        TaskProxyFactory factory;
        EnumMap<UserType, List<String>> permissionMap;

        permissionMap = new EnumMap<>(UserType.class);
        permissionMap.put(UserType.USER, List.of("setText"));

        task = new TaskBuilder()
                .error("")
                .build();
        factory = new TaskProxyFactory();
        task = factory.newLoggerProxyInstance(task, new PrintWriter(System.out));
        task = factory.newPermitProxyInstance(task, permissionMap, UserType.USER);

        task.setText("ss");
    }

}
