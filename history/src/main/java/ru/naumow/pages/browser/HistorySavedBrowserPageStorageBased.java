package ru.naumow.pages.browser;

import ru.naumow.pages.page.Page;
import ru.naumow.pages.position.Position;
import ru.naumow.pages.storage.PageStorage;

import java.util.Deque;
import java.util.LinkedList;

public class HistorySavedBrowserPageStorageBased implements HistorySavedBrowser {

    private PageStorage pageStorage;

    private Page     currentPage;
    private Position currentPosition;

    private Deque<Memento> history;
    private Deque<Memento> forwardHistory;

    public HistorySavedBrowserPageStorageBased(PageStorage pageStorage) {
        this.pageStorage = pageStorage;
        this.history = new LinkedList<>();
        this.forwardHistory = new LinkedList<>();
    }

    private void clearForwardHistory() {
        this.forwardHistory.clear();
    }

    @Override
    public void goTo(String pageName) {
        clearForwardHistory();
        history.push(new Memento());
        this.currentPage = pageStorage.getPage(pageName);
    }

    @Override
    public void link(Page page) {
        clearForwardHistory();
        history.push(new Memento());
        this.currentPage = page;
    }

    @Override
    public void gotoPosition(int i) {
        this.currentPosition = this.currentPage.getPositions().get(i);
        if (currentPosition != null)
            this.currentPosition.show(this);
    }

    @Override
    public Page getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public Position getCurrentPosition() {
        return this.currentPosition;
    }

    @Override
    public void back() {
        if (history.isEmpty())
            throw new IllegalStateException("history is empty");
        forwardHistory.push(new Memento());
        Memento memento = history.pop();
        memento.recover();
    }

    @Override
    public void forward() {
        if (forwardHistory.isEmpty())
            throw new IllegalStateException("nowhere to go forward to");
        history.push(new Memento());
        Memento memento = forwardHistory.pop();
        memento.recover();
    }

    private class Memento {
        private Page     page;
        private Position position;

        public Memento() {
            this.page = currentPage;
            this.position = currentPosition;
        }

        private void recover() {
            currentPage = page;
            currentPosition = position;
        }
    }

}
