package ru.naumow.task;

import java.util.HashMap;
import java.util.Map;

public class TaskRepository {

    private Map<Integer, Task> storage = new HashMap<>();

    private int lastGeneratedId = 0;

    public Task findById(int id) {
        return storage.get(id);
    }

    public void save(Task task) {
        lastGeneratedId++;
        storage.put(lastGeneratedId, task);
        task.setId(lastGeneratedId);
    }

    public void update(Task task) {
        storage.put(task.getId(), task);
    }

    public void delete(Task task) {
        storage.remove(task.getId());
    }

}
