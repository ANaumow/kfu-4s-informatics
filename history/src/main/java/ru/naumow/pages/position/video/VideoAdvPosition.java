package ru.naumow.pages.position.video;

import ru.naumow.pages.browser.Browser;
import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;

public class VideoAdvPosition implements Position {

    private Page adv;

    public VideoAdvPosition(Page adv) {
        this.adv = adv;
    }

    @Override
    public void gotoAdv(Browser browser) {
        browser.link(adv);
    }

    @Override
    public void show(Browser browser) {
        this.gotoAdv(browser);
    }
}
