package com.gahee.rss_v1.TIME;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "channel", strict = false)
public class Channel implements Serializable {


    @Element(name="title")
    private String title;

    @Element(name = "description")
    private String description;

    @ElementList(inline = true, entry = "item")
    private List<Item> items;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }
}
