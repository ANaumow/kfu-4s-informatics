package ru.naumow.task.proxy;

import ru.naumow.task.State;
import ru.naumow.task.Task;
import ru.naumow.task.TaskProxy;

public abstract class AbstractTaskProxy implements TaskProxy {

    protected final Task proxied;

    public AbstractTaskProxy(Task proxied) {
        this.proxied = proxied;
    }

    @Override
    public Task getProxied() {
        return proxied;
    }

    @Override
    public int getId() {
        return getProxied().getId();
    }

    @Override
    public void setId(int id) {
        getProxied().setId(id);
    }

    @Override
    public int getDeveloperId() {
        return getProxied().getDeveloperId();
    }

    @Override
    public void setDeveloperId(int developerId) {
        getProxied().setDeveloperId(developerId);
    }

    @Override
    public int getTesterId() {
        return getProxied().getTesterId();
    }

    @Override
    public void setTesterId(int testerId) {
        getProxied().setTesterId(testerId);
    }

    @Override
    public String getText() {
        return getProxied().getText();
    }

    @Override
    public void setText(String text) {
        getProxied().setText(text);
    }

    @Override
    public String getError() {
        return getProxied().getError();
    }

    @Override
    public void setError(String error) {
        getProxied().setError(error);
    }

    @Override
    public State getCurrentState() {
        return getProxied().getCurrentState();
    }

    @Override
    public void setCurrentState(State currentState) {
        getProxied().setCurrentState(currentState);
    }

    @Override
    public void up(int id) {
        getProxied().up(id);
    }

    @Override
    public void down(int id, String text) {
        getProxied().down(id, text);
    }

    @Override
    public Task copy() {
        return getProxied().copy();
    }
}
