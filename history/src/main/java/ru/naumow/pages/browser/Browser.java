package ru.naumow.pages.browser;

import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;

public interface Browser {

    void goTo(String pageName);

    void link(Page page);

    void gotoPosition(int i);

    Page getCurrentPage();

    Position getCurrentPosition();

}
