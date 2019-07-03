package com.gahee.rss_v1.CNN;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "rss", strict = false)
public class Feedcnn implements Serializable {
    //channel
    //channel - title, pubdate, item
    //channel - item - title, link, description, pubdate, thumbnail
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
