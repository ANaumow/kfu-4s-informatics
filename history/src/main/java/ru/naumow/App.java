package ru.naumow;

import ru.naumow.pages.browser.HistorySavedBrowser;
import ru.naumow.pages.browser.HistorySavedBrowserPageStorageBased;
import ru.naumow.pages.storage.JsonPageStorage;
import ru.naumow.pages.interpretator.Context;
import ru.naumow.pages.interpretator.Interpreter;
import ru.naumow.pages.interpretator.components.BrowserOperations;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        JsonPageStorage storage = new JsonPageStorage();

        storage.load(App.class.getClassLoader().getResource("pages.json"));

        HistorySavedBrowser browser = new HistorySavedBrowserPageStorageBased(storage);

        Context context = new Context();
        context.addAttribute("browser", browser);

        Interpreter interpreter = new Interpreter(context, new BrowserOperations());

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("exit")) {
            interpreter.execute(input);
            String name = browser.getCurrentPage().getName();
            System.out.println("current page is " + name);
        }

    }

}
