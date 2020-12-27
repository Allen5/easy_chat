package com.allen.easyChat.server.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 手机号
     */
    @Column(name = "`mobile`")
    private String mobile;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 用户头像
     */
    @Column(name = "`avatar`")
    private String avatar;

    /**
     * 0未知 1男性 2女性
     */
    @Column(name = "`sex`")
    private Byte sex;

    /**
     * 0正常 1异常
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 用户注册时间
     */
    @Column(name = "`created`")
    private Date created;

    /**
     * 用户更新时间
     */
    @Column(name = "`updated`")
    private Date updated;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String MOBILE = "mobile";

    public static final String DB_MOBILE = "mobile";

    public static final String PASSWORD = "password";

    public static final String DB_PASSWORD = "password";

    public static final String AVATAR = "avatar";

    public static final String DB_AVATAR = "avatar";

    public static final String SEX = "sex";

    public static final String DB_SEX = "sex";

    public static final String STATUS = "status";

    public static final String DB_STATUS = "status";

    public static final String CREATED = "created";

    public static final String DB_CREATED = "created";

    public static final String UPDATED = "updated";

    public static final String DB_UPDATED = "updated";
}