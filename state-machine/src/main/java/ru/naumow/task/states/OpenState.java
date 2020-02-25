package ru.naumow.task.states;

import ru.naumow.task.State;
import ru.naumow.task.Task;

public enum OpenState implements State {

    INSTANCE;

    @Override
    public void up(Task task, int id) {
        task.setDeveloperId(id);
        task.setCurrentState(AssignState.INSTANCE);
    }

    @Override
    public void down(Task task, int id, String text) {
        task.setCurrentState(BackLogState.INSTANCE);
    }

}
