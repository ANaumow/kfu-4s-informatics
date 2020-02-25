package ru.naumow.task.proxy;

import ru.naumow.task.State;
import ru.naumow.task.Task;
import ru.naumow.task.TaskLoggerProxy;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class TaskLoggerProxyImpl extends AbstractTaskProxy implements TaskLoggerProxy {

    private final Writer writer;

    TaskLoggerProxyImpl(Task proxied, Writer writer) {
        super(proxied);
        this.writer = writer;
    }

    private void write(String text) {
        try {
            this.writer.append(text).append("\n");
            this.writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void called(String methodName) {
        write("Method " + methodName + " was called");
    }

    private void calledWith(String methodName, Object... args) {
        write("Method " + methodName + " was called with args: " + Arrays.toString(args));
    }

    @Override
    public int getId() {
        called("getId");
        return super.getId();
    }

    @Override
    public void setId(int id) {
        calledWith("setId", id);
        super.setId(id);
    }

    @Override
    public int getDeveloperId() {
        called("getDeveloper");
        return super.getDeveloperId();
    }

    @Override
    public void setDeveloperId(int developerId) {
        calledWith("setDeveloperId", developerId);
        super.setDeveloperId(developerId);
    }

    @Override
    public int getTesterId() {
        called("getTesterId");
        return super.getTesterId();
    }

    @Override
    public void setTesterId(int testerId) {
        calledWith("setTesterId", testerId);
        super.setTesterId(testerId);
    }

    @Override
    public String getText() {
        called("getText");
        return super.getText();
    }

    @Override
    public void setText(String text) {
        calledWith("setText", text);
        super.setText(text);
    }

    @Override
    public String getError() {
        called("getError");
        return super.getError();
    }

    @Override
    public void setError(String error) {
        calledWith("setError", error);
        super.setError(error);
    }

    @Override
    public State getCurrentState() {
        called("getCurrentState");
        return super.getCurrentState();
    }

    @Override
    public void setCurrentState(State currentState) {
        calledWith("setCurrentState", currentState);
        super.setCurrentState(currentState);
    }

    @Override
    public void up(int id) {
        calledWith("up", id);
        super.up(id);
    }

    @Override
    public void down(int id, String text) {
        calledWith("down", text);
        super.down(id, text);
    }

    @Override
    public Task copy() {
        called("copy");
        return super.copy();
    }

    @Override
    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
