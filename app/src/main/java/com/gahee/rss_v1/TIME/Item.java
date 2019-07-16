package com.gahee.rss_v1.TIME;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "item", strict = false)
public class Item implements Serializable {

    @Element(name = "title")
    private String articleTitle;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "description")
    private String articleDesc;

    @Element(name = "thumbnail")
    private Thumbnail thumbnail;

    @Element(name = "origLink")
    private String articleLink;

    @Root(name = "thumbnail", strict = false)
    public static class Thumbnail{

        @Attribute(name = "url")
        private String url;
        public String getUrl() {
            return url;
        }
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getArticleLink() {
        return articleLink;
    }
}
