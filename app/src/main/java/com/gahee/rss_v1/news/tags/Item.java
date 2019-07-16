package com.gahee.rss_v1.news.tags;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;

@Root(name = "item", strict = false)
public class Item implements Serializable {
    //channel - item - title, link, description, pubdate, thumbnail
    @Element(name = "title", required = false)
    private String title;

    @Path("link")
    @Text(required = false)
    private String link;

    @Element(name = "description", required = false)
    private String description;

    @Element(name = "pubDate", required = false)
    private String pubDate;

    @Element(name = "thumbnail", required = false)
    private Thumbnail thumbnail;

    @Element(name = "group", required = false)
    private Group group;


    public Item(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public Group getGroup() {
        return group;
    }

    @Root(name = "thumbnail", strict = false)
    public static class Thumbnail{

        @Attribute(name = "url")
        private String url;

        public String getUrl() {
            return url;
        }
    }


}
