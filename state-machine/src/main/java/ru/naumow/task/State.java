package ru.naumow.task;

public interface State {

    void up(Task task, int id);

    void down(Task task, int id, String text);


}
