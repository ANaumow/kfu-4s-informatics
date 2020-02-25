package ru.naumow.task.proxy;

import ru.naumow.task.Task;
import ru.naumow.task.TaskLoggerProxy;
import ru.naumow.task.TaskPermitProxy;
import ru.naumow.task.proxy.permission.UserType;

import java.io.Writer;
import java.util.EnumMap;
import java.util.List;

public class TaskProxyFactory {

    public TaskLoggerProxy newLoggerProxyInstance(Task task, Writer writer) {
        return new TaskLoggerProxyImpl(task, writer);
    }

    public TaskPermitProxy newPermitProxyInstance(Task task, EnumMap<UserType, List<String>> permissionMap) {
        return new TaskPermitProxyImpl(task, permissionMap);
    }

    public TaskPermitProxy newPermitProxyInstance(Task task, EnumMap<UserType, List<String>> permissionMap, UserType type) {
        TaskPermitProxy permitProxy = newPermitProxyInstance(task, permissionMap);
        permitProxy.associateWith(type);
        return permitProxy;
    }

}
