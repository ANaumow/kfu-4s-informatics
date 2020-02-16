package ru.naumow.geo.interpretator.core;

import ru.naumow.geo.GeoNode;
import ru.naumow.geo.Tree;
import ru.naumow.geo.interpretator.Context;
import ru.naumow.geo.interpretator.core.anotation.CmdMapping;

import java.util.Arrays;
import java.util.Iterator;

public class Component {

    @CmdMapping("return children")
    private void returnChildren(Context context, String[] args) {
        if (args.length != 2)
            throw new IllegalArgumentException("Not enough arguments: expected 2, found: " + args.length);

        Tree tree = context.getAttribute("tree", Tree.class);
        Iterator<GeoNode> iterator = tree.getIterator();

        while (iterator.hasNext()){
            GeoNode cur = iterator.next();
            /*System.out.println(cur.getPath());
            System.out.println(args[1]);*/
            if (cur.getPath().matches(".*" + args[1] + "$")) {
                cur.getChildren().forEach(x -> System.out.println(x.getName()));
                return;
            }
        }

        /*Tree tree = context.getAttribute("tree", Tree.class);*/

    }

    @CmdMapping("add")
    private void add(Context context, String[] args) {

    }

    @CmdMapping("save")
    private void save(Context context, String[] args) {

    }

    @CmdMapping("delete")
    private void delete(Context context, String[] args) {

    }

}
