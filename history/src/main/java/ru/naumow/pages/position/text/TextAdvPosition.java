package ru.naumow.pages.position.text;

import ru.naumow.pages.browser.Browser;
import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;

public class TextAdvPosition implements Position {

    private Page page;

    public TextAdvPosition(Page page) {
        this.page = page;
    }

    @Override
    public void gotoAdv(Browser browser) {
        browser.link(page);
    }

    @Override
    public void show(Browser browser) {
        // just "show"
    }
}
