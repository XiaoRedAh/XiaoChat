create database `xiao_chat`;

DROP TABLE user;
CREATE TABLE user
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    userId       varchar(9) COMMENT '用户ID',
    userNickName varchar(32) COMMENT '用户昵称',
    userHead     varchar(16) COMMENT '用户头像',
    userPassword varchar(64) COMMENT '用户密码',
    createTime   datetime COMMENT '创建时间',
    updateTime   datetime COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE `groups`;
CREATE TABLE `groups`
(
    id         bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    groupId    varchar(9) COMMENT '群组ID',
    groupName  varchar(16) COMMENT '群组名称',
    groupHead  varchar(16) COMMENT '群组头像',
    createTime datetime COMMENT '创建时间',
    updateTime datetime COMMENT '更新时间',
    PRIMARY KEY (id),
    CONSTRAINT idx_groupId UNIQUE (groupId)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE talk_box;
CREATE TABLE talk_box
(
    id         bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    userId     varchar(9) COMMENT '用户ID',
    talkId     varchar(9) COMMENT '对话框ID(好友ID、群组ID)',
    talkType   int(4) COMMENT '对话框类型；0好友、1群组',
    createTime datetime COMMENT '创建时间',
    updateTime datetime COMMENT '更新时间',
    PRIMARY KEY (id),
    CONSTRAINT idx_talkId_userId UNIQUE (userId, talkId)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE user;
CREATE TABLE user
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    userId       varchar(9) COMMENT '用户ID',
    userNickName varchar(32) COMMENT '用户昵称',
    userHead     varchar(16) COMMENT '用户头像',
    userPassword varchar(64) COMMENT '用户密码',
    createTime   datetime COMMENT '创建时间',
    updateTime   datetime COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE user_friend;
CREATE TABLE user_friend
(
    id           bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    userId       bigint COMMENT '用户ID',
    userFriendId bigint COMMENT '好友用户ID',
    createTime   datetime COMMENT '创建时间',
    updateTime   datetime COMMENT '更新时间',
    PRIMARY KEY (id),
    CONSTRAINT uuid_idx UNIQUE (userId, userFriendId)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE user_group;
CREATE TABLE user_group
(
    id         bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    userId     varchar(9) COMMENT '用户ID',
    groupId    varchar(9) COMMENT '群组ID',
    createTime datetime COMMENT '创建时间',
    updateTime datetime COMMENT '更新时间',
    PRIMARY KEY (id),
    CONSTRAINT idx_uuid UNIQUE (userId, groupId)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
