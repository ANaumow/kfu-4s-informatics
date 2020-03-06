package ru.naumow.pages.page;

import ru.naumow.pages.position.Position;

import java.util.List;
import java.util.Map;

public interface Page {

    List<Page> getLinks();

    String getName();

    Map<Integer, Position> getPositions();

}
