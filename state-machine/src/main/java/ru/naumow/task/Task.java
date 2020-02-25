package ru.naumow.task;

public interface Task extends Copyable<Task>, Cloneable {

    int getId();

    void setId(int id);

    int getDeveloperId();

    void setDeveloperId(int developerId);

    int getTesterId();

    void setTesterId(int testerId);

    String getText();

    void setText(String text);

    String getError();

    void setError(String error);

    State getCurrentState();

    void setCurrentState(State currentState);

    void up(int id);

    void down(int id, String text);
}
