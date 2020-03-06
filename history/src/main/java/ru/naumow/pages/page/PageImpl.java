package ru.naumow.pages.page;

import ru.naumow.pages.position.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageImpl implements Page {

    private String         name;
    private List<Page>             pages;
    private Map<Integer, Position> positions;

    public PageImpl(String name) {
        this(name, new ArrayList<>(), new HashMap<>());
    }

    public PageImpl(String name, List<Page> pages, Map<Integer, Position> positions) {
        this.name = name;
        this.pages = pages;
        this.positions = positions;
    }

    @Override
    public List<Page> getLinks() {
        return pages;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<Integer, Position> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "PageImpl{" +
                "name='" + name + '\'' +
                ", pages=" + pages.stream().map(Page::getName).collect(Collectors.joining(", ", "[", "]")) +
                ", positions=" + positions +
                '}';
    }
}
