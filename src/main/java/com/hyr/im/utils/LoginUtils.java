package com.hyr.im.utils;

import com.hyr.im.common.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class LoginUtils {
    public static void markAsLogin(Channel context){
        context.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel context){
         Attribute<Boolean> login = context.attr(Attributes.LOGIN);
         return login.get() != null;
    }
}
