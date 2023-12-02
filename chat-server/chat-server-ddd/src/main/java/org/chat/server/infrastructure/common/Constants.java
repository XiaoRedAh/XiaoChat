package org.chat.server.infrastructure.common;

/**
 * @author XiaoRed
 * @date 2023/12/2 7:58
 */
public class Constants {

    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        INDEX_DUP("0003", "主键冲突");

        private final String code;
        private final String info;

        ResponseCode(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    public enum TalkType {
        Friend(0, "好友"),
        Group(1, "群组");

        private final Integer code;
        private final String info;

        TalkType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

    }

    public enum MsgType{

        Myself(0, "自己"),
        Friend(1, "好友");

        private final Integer code;
        private final String info;

        MsgType(Integer code, String info) {
            this.code = code;
            this.info = info;
        }

        public Integer getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

}
