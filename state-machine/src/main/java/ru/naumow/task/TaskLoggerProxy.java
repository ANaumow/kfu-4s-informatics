package ru.naumow.task;

public interface TaskLoggerProxy extends TaskProxy {

    void flush();

    void close();

}
