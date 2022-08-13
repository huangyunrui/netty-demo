package com.hyr.im.common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtils {
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    private static final Map<Integer, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if (hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(String userName){
        return userIdChannelMap.get(userName);
    }


    public static void addChannelGroup(Integer groupId, ChannelGroup channelGroup){
        channelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(Integer groupId){
        return channelGroupMap.get(groupId);
    }
}
