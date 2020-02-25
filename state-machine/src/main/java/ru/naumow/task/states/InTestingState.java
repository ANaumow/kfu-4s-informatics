package ru.naumow.task.states;

import ru.naumow.task.State;
import ru.naumow.task.Task;

public enum InTestingState implements State {

    INSTANCE;

    @Override
    public void up(Task task, int id) {
        task.setDeveloperId(-1);
        task.setTesterId(-1);
        task.setCurrentState(ClosedState.INSTANCE);
    }

    @Override
    public void down(Task task, int id, String text) {
        task.setError(text);
        task.setCurrentState(AssignState.INSTANCE);
    }

}
