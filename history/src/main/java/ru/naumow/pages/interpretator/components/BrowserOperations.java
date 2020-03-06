package ru.naumow.pages.interpretator.components;

import ru.naumow.pages.browser.Browser;
import ru.naumow.pages.browser.HistorySavedBrowser;
import ru.naumow.pages.page.Page;
import ru.naumow.pages.interpretator.Context;
import ru.naumow.pages.interpretator.core.anotation.CmdMapping;
import ru.naumow.pages.interpretator.core.util.Commands;

public class BrowserOperations {

    // goto <название станицы>
    @CmdMapping("goto")
    private void goTo(Context context, String[] args) {
        Commands.requireSize(args, 1);

        Browser browser = context.getAttribute("browser", Browser.class);
        String pageName = args[0];

        browser.goTo(pageName);
    }

    // link <название станицы>
    @CmdMapping("link")
    private void link(Context context, String[] args) {
        Commands.requireSize(args, 1);

        Browser browser = context.getAttribute("browser", Browser.class);
        String pageName = args[0];

        Page page = browser.getCurrentPage().getLinks().stream()
                .filter(p -> p.getName().equals(pageName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no page with that name"));

        browser.link(page);
    }

    // back
    @CmdMapping("back")
    private void back(Context context, String[] args) {
        Commands.requireSize(args, 0);
        HistorySavedBrowser browser = context.getAttribute("browser", HistorySavedBrowser.class);
        browser.back();
    }

    // forward
    @CmdMapping("forward")
    private void forward(Context context, String[] args) {
        Commands.requireSize(args,0);
        HistorySavedBrowser browser = context.getAttribute("browser", HistorySavedBrowser.class);
        browser.forward();
    }

    // gotoposition <позиция>
    @CmdMapping("gotoposition")
    private void gotoPosition(Context context, String[] args) {
        Commands.requireSize(args, 1);
        Browser browser = context.getAttribute("browser", Browser.class);
        int position = Integer.parseInt(args[0]);
        browser.gotoPosition(position);
    }

    // gotoadv
    @CmdMapping("gotoadv")
    private void gotoAdv(Context context, String[] args) {
        Commands.requireSize(args, 0);
        Browser browser = context.getAttribute("browser", Browser.class);
        browser.getCurrentPosition().gotoAdv(browser);
    }

}
