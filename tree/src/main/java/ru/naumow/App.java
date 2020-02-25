package ru.naumow;

import ru.naumow.interpretator.components.Debug;
import ru.naumow.tree.Tree;
import ru.naumow.facade.TreeFileConverter;
import ru.naumow.facade.TreeFileConverterImpl;
import ru.naumow.interpretator.Interpreter;
import ru.naumow.interpretator.Context;
import ru.naumow.interpretator.components.DataCrud;
import ru.naumow.iterator.IteratorDfs;

import java.util.Scanner;

public class App implements Runnable {

    public final static String INPUT_FILENAME  = "src/main/resources/data.xml";
    public final static String OUTPUT_FILENAME = "src/main/resources/data3.json";

    public static final String ATTR_NAME = "name";
    public static final String ATTR_PRIORITY = "priority";

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    public void run() {
        TreeFileConverter converter = TreeFileConverterImpl.getInstance();
        Tree tree = converter.readTree(INPUT_FILENAME);
        tree.setIteratorClass(IteratorDfs.class);

        Context context = new Context();
        context.addAttribute(Context.ATTR_TREE, tree);
        context.addAttribute(Context.ATTR_CONVERTER, converter);
        context.addAttribute(Context.ATTR_INPUT_FILENAME, INPUT_FILENAME);
        context.addAttribute(Context.ATTR_OUTPUT_FILENAME, OUTPUT_FILENAME);

        Interpreter interpreter = new Interpreter(context, new DataCrud(), new Debug());
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("exit")) {
            interpreter.execute(input);
        }
    }

}
