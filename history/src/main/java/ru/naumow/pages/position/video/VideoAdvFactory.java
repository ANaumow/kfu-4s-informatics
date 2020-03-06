package ru.naumow.pages.position.video;

import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;
import ru.naumow.pages.position.PositionFactory;

public class VideoAdvFactory implements PositionFactory {
    @Override
    public Position create(Page adv) {
        return new VideoAdvPosition(adv);
    }
}
