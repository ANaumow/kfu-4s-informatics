package ru.naumow.pages.position.text;

import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;
import ru.naumow.pages.position.PositionFactory;

public class TextAdvFactory implements PositionFactory {
    @Override
    public Position create(Page adv) {
        return new TextAdvPosition(adv);
    }
}
