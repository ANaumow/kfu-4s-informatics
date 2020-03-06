package ru.naumow.pages.storage;

import ru.naumow.pages.position.text.TextAdvFactory;
import ru.naumow.pages.position.video.VideoAdvFactory;
import ru.naumow.pages.page.Page;
import ru.naumow.pages.page.PageImpl;
import ru.naumow.pages.position.Position;
import ru.naumow.pages.position.PositionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RawPageData {

    private List<Entry> pages;

    public List<Entry> getPages() {
        return pages;
    }

    public void setPages(List<Entry> pages) {
        this.pages = pages;
    }

    public Set<Page> convert() {
        Set<Page> converted = new HashSet<>();
        for (Entry page : pages) {
            converted.add(new PageImpl(page.getName()));
        }
        for (Entry entry : pages) {
            Page page = converted.stream().filter(x -> x.getName().equals(entry.getName())).findFirst().get();
            for (String link : entry.getLinks()) {
                page.getLinks().add(converted.stream().filter(x -> x.getName().equals(link)).findFirst().get());
            }
            initPositions(new VideoAdvFactory(), entry.getVideoAdv(), converted, page);
            initPositions(new TextAdvFactory(), entry.getTextAdv(), converted, page);
        }
        return converted;
    }

    private void initPositions(PositionFactory factory, List<Entry.RawPos> list, Set<Page> pages, Page current) {
        for (Entry.RawPos rawPos : list) {
            Page adv = pages.stream().filter(x -> x.getName().equals(rawPos.getAdv())).findFirst().get();
            Position position = factory.create(adv);
            current.getPositions().put(rawPos.pos, position);
        }
    }

    public static class Entry {
        private String       name;
        private List<String> links;
        private List<RawPos> textAdv;
        private List<RawPos> videoAdv;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getLinks() {
            return links;
        }

        public void setLinks(List<String> links) {
            this.links = links;
        }

        public List<RawPos> getTextAdv() {
            return textAdv;
        }

        public void setTextAdv(List<RawPos> textAdv) {
            this.textAdv = textAdv;
        }

        public List<RawPos> getVideoAdv() {
            return videoAdv;
        }

        public void setVideoAdv(List<RawPos> videoAdv) {
            this.videoAdv = videoAdv;
        }

        public static class RawPos {
            private Integer pos;
            private String  adv;

            public Integer getPos() {
                return pos;
            }

            public void setPos(Integer pos) {
                this.pos = pos;
            }

            public String getAdv() {
                return adv;
            }

            public void setAdv(String adv) {
                this.adv = adv;
            }
        }
    }

}
