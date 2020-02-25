package ru.naumow.task.states;

import ru.naumow.task.State;
import ru.naumow.task.Task;

public enum ResolvedState implements State {

    INSTANCE;

    @Override
    public void up(Task task, int id) {
        task.setTesterId(id);
        task.setCurrentState(InTestingState.INSTANCE);
    }

    @Override
    public void down(Task task, int id, String text) {
        task.setCurrentState(InProgressState.INSTANCE);
    }

}
