package ru.naumow.task.states;

import ru.naumow.task.State;
import ru.naumow.task.Task;

public enum AssignState implements State {

    INSTANCE;

    @Override
    public void up(Task task, int id) {
        task.setCurrentState(InProgressState.INSTANCE);
    }

    @Override
    public void down(Task task, int id, String text) {
        task.setDeveloperId(-1);
        task.setCurrentState(OpenState.INSTANCE);
    }

}
