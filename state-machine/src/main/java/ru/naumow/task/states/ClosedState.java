package ru.naumow.task.states;

import ru.naumow.task.State;
import ru.naumow.task.Task;

public enum ClosedState implements State {

    INSTANCE;

    @Override
    public void up(Task task, int id) {

    }

    @Override
    public void down(Task task, int id, String text) {

    }
}
