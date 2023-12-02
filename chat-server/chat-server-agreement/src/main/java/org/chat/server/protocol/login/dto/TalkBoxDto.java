package org.chat.server.protocol.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author XiaoRed
 * @date 2023/12/1 19:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalkBoxDto {
    private String talkId; //对话框ID
    private Integer talkType; //对话框类型；0好友、1群组
    private String talkName; //聊天名：好友名/群组名
    private String avatar; //头像：好友头像/群组头像
    private String desc; //消息简述：最新那条聊天记录
    private Date talkDate; //消息时间：展示最新那条聊天记录的时间
    private List<ChatRecordDto> chatRecordList; //聊天记录

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId;
    }

    public Integer getTalkType() {
        return talkType;
    }

    public void setTalkType(Integer talkType) {
        this.talkType = talkType;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String talkHead) {
        this.avatar = talkHead;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String talkSketch) {
        this.desc = talkSketch;
    }

    public Date getTalkDate() {
        return talkDate;
    }

    public void setTalkDate(Date talkDate) {
        this.talkDate = talkDate;
    }

    public List<ChatRecordDto> getChatRecordList() {
        return chatRecordList;
    }

    public void setChatRecordList(List<ChatRecordDto> chatRecordList) {
        this.chatRecordList = chatRecordList;
    }
}
