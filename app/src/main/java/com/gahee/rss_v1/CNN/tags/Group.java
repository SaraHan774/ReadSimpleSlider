package com.gahee.rss_v1.CNN.tags;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "group", strict = false)
public class Group implements Serializable {

    //media:group -> media:content -> first url is the biggest image

    @ElementList(inline = true, entry = "content", required = false)
    private List<Content> content;

    public List<Content> getContent() {
        return content;
    }

    @Root(name = "content", strict = false)
    public static class Content{

        @Attribute(name = "url")
        private String url;

        public String getUrl() {
            return url;
        }
    }

}
