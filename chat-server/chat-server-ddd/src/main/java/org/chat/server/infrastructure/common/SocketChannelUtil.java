package org.chat.server.infrastructure.common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 管理channel工具类
 * @author XiaoRed
 * @date 2023/12/1 22:38
 */
public class SocketChannelUtil {
    /**
     * 存储channel和用户的对应关系，以用户id为key，channel为value
     */
    private static final Map<String, Channel> userChannel = new ConcurrentHashMap<>();
    /**
     * 存储channelId和用户的对应关系，以channelId为key，用户id为值
     */
    private static final Map<String, String> userChannelId = new ConcurrentHashMap<>();
    /**
     * 存储群组及其现有channel的对应关系，以群号为key，channel组为值
     */
    private static final Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    //<==============================单聊相关操作=====================================>
    /**
     * 新的客户端连接建立时，将用户以及channel的对应关系记录下来，方便后续使用
     */
    public static void addChannel(String userId, Channel channel) {
        userChannel.put(userId, channel);
        userChannelId.put(channel.id().toString(), userId);
    }

    /**
     * 根据channelId，将用户-channel的对应记录删除
     */
    public static void removeChannel(String channelId){
        String userId = userChannelId.get(channelId);
        if (null == userId) return;
        userChannel.remove(userId);
    }

    public static void removeUserChannelByUserId(String userId){
        userChannel.remove(userId);
    }

    public static Channel getChannelByUserId(String userId) {
        return userChannel.get(userId);
    }

    //<==============================群组相关操作=====================================>
    /**
     * 新成员加入群组时，往群组中添加新的成员通信管道
     * @param talkId  对话框ID[群号]
     * @param userChannel 群员通信管道
     */
    public synchronized static void addChannelGroup(String talkId, Channel userChannel) {
        ChannelGroup channelGroup = channelGroupMap.get(talkId);
        if (null == channelGroup) {
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(talkId, channelGroup);
        }
        channelGroup.add(userChannel);
    }

    /**
     * 群组中有用户离开，则将他的通信信道从信道组中移除
     * @param talkId  对话框ID[群号]
     * @param userChannel 群员通信管道
     */
    public synchronized static void removeChannelGroup(String talkId, Channel userChannel){
        ChannelGroup channelGroup = channelGroupMap.get(talkId);
        if (null == channelGroup) return;
        channelGroup.remove(userChannel);
    }

    /**
     * 移除channel所在的群组
     */
    public static void removeChannelGroupByChannel(Channel channel){
        for (ChannelGroup next : channelGroupMap.values()) {
            next.remove(channel);
        }
    }

    /**
     * 根据群号，获取群组通信管道
     * @param talkId 对话框ID[群号]
     * @return 群组
     */
    public static ChannelGroup getChannelGroup(String talkId) {
        return channelGroupMap.get(talkId);
    }

}
