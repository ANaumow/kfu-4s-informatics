package ru.naumow.pages.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.naumow.pages.page.Page;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class JsonPageStorage implements PageStorage {

    private Set<Page> pages;

    @Override
    public Page getPage(String name) {
        return pages.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public void load(URL url) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            RawPageData rawPageData = mapper.readValue(url, RawPageData.class);
            pages = rawPageData.convert();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
