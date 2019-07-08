package com.gahee.rss_v1.CNN.tags;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "channel", strict = false)
public class Channel implements Serializable {

    @Element(name = "title", required = false)
    private String title;

    @Element(name = "pubdate", required = false)
    private String pubdate;

    @ElementList(inline = true, entry = "item", required = false)
    private List<Item> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Channel : Items : " + item ;
    }
}
