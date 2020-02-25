package ru.naumow;

import ru.naumow.task.TaskRepository;
import ru.naumow.task.interpretator.Context;
import ru.naumow.task.interpretator.Interpreter;
import ru.naumow.task.interpretator.components.TaskOperations;

import java.util.Scanner;

public class AppInterpreter {

    public static void main(String[] args) {
        TaskRepository taskRepository = new TaskRepository(); // just for IDs

        Context context = new Context();
        context.addAttribute("taskRepository", taskRepository);

        Interpreter interpreter = new Interpreter(context, new TaskOperations());
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("exit")) {
            interpreter.execute(input);
        }
    }

}
