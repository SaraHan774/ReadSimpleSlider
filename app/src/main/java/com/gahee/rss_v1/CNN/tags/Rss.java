package com.gahee.rss_v1.CNN.tags;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)
public class Rss implements Serializable {

    //rss - channel
    //rss - channel - title, pubdate, item
    //rss - channel - item - title, link, description, pubdate, thumbnail
        //item -> repeating

    @Element(name = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
