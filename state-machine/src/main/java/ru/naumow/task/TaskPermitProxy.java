package ru.naumow.task;

import ru.naumow.task.proxy.permission.UserType;

public interface TaskPermitProxy extends TaskProxy {

    void associateWith(UserType userType);

    void unAssociate();

}
