package ru.naumow.task.proxy;

import ru.naumow.task.State;
import ru.naumow.task.Task;
import ru.naumow.task.TaskPermitProxy;
import ru.naumow.task.proxy.permission.UserType;

import java.util.EnumMap;
import java.util.List;

public class TaskPermitProxyImpl extends AbstractTaskProxy implements TaskPermitProxy {

    private final EnumMap<UserType, List<String>> permissionMap;
    private       UserType                        current;

    TaskPermitProxyImpl(Task proxied, EnumMap<UserType, List<String>> permissionMap) {
        super(proxied);
        this.permissionMap = permissionMap;
    }

    private void checkPermission(String methodName) {
        if (current == null)
            throw new IllegalStateException("no user to be associated with");
        if (!permissionMap.containsKey(current))
            throw new IllegalStateException("permissionMap does not contain such user");
        List<String> allowedCommands = permissionMap.get(current);
        if (!allowedCommands.contains(methodName))
            throw new IllegalStateException("current user do have enough permission");
    }

    @Override
    public int getId() {
        checkPermission("getId");
        return super.getId();
    }

    @Override
    public void setId(int id) {
        checkPermission("setId");
        super.setId(id);
    }

    @Override
    public int getDeveloperId() {
        checkPermission("getDeveloperId");
        return super.getDeveloperId();
    }

    @Override
    public void setDeveloperId(int developerId) {
        checkPermission("setDeveloperId");
        super.setDeveloperId(developerId);
    }

    @Override
    public int getTesterId() {
        checkPermission("getTesterId");
        return super.getTesterId();
    }

    @Override
    public void setTesterId(int testerId) {
        checkPermission("setTesterId");
        super.setTesterId(testerId);
    }

    @Override
    public String getText() {
        checkPermission("getText");
        return super.getText();
    }

    @Override
    public void setText(String text) {
        checkPermission("setText");
        super.setText(text);
    }

    @Override
    public String getError() {
        checkPermission("getError");
        return super.getError();
    }

    @Override
    public void setError(String error) {
        checkPermission("setError");
        super.setError(error);
    }

    @Override
    public State getCurrentState() {
        checkPermission("getCurrentState");
        return super.getCurrentState();
    }

    @Override
    public void setCurrentState(State currentState) {
        checkPermission("setCurrentState");
        super.setCurrentState(currentState);
    }

    @Override
    public void up(int id) {
        checkPermission("up");
        super.up(id);
    }

    @Override
    public void down(int id, String text) {
        checkPermission("down");
        super.down(id, text);
    }

    @Override
    public Task copy() {
        checkPermission("copy");
        return super.copy();
    }

    @Override
    public void associateWith(UserType userType) {
        this.current = userType;
    }

    @Override
    public void unAssociate() {
        this.current = null;
    }
}
