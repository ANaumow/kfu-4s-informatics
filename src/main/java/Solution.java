import ru.naumow.geo.GeoNode;
import ru.naumow.geo.GeoNodeXmlParser;
import ru.naumow.geo.Tree;
import ru.naumow.geo.interpretator.CommandExecutor;
import ru.naumow.geo.interpretator.Context;
import ru.naumow.geo.interpretator.core.Component;
import ru.naumow.geo.iterator.IteratorDfs;

import java.util.Iterator;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.run();
    }

    private void run() {
        GeoNodeXmlParser parser = new GeoNodeXmlParser();
        GeoNode root = parser.parse(Solution.class.getResource("data.xml"));
        Tree tree = new Tree(root, new IteratorDfs(root));
        /*Iterator<GeoNode> iterator = tree.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getPath());
        }*/

        Context context = new Context();
        context.addAttribute("tree", tree);
        CommandExecutor commandExecutor = new CommandExecutor(context, new Component());
        commandExecutor.execute("return children street leningradskaya");

    }

}
