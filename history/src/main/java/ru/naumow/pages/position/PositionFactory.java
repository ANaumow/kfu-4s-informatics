package ru.naumow.pages.position;

import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;

public interface PositionFactory {

    Position create(Page advPage);

}
