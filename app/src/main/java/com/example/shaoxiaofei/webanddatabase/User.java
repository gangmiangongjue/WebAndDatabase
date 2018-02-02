package com.example.shaoxiaofei.webanddatabase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by shaoxiaofei on 28/12/2017.
 */

@Entity
public class User
{
    @Id
    private Long id;
    private int userSex;
    private String userLastX;
    @Property(nameInDb = "sex")
    private String userNickname;
    private String userIcon;
    private String userMobile;
    private int userId;
    @Transient
    private String userDetailAddr;
    private String userLastExperience;
    private String userLevelName;
    private long userBirthday;
    private String userProvince;
    @Generated(hash = 1794637085)
    public User(Long id, int userSex, String userLastX, String userNickname,
            String userIcon, String userMobile, int userId,
            String userLastExperience, String userLevelName, long userBirthday,
            String userProvince) {
        this.id = id;
        this.userSex = userSex;
        this.userLastX = userLastX;
        this.userNickname = userNickname;
        this.userIcon = userIcon;
        this.userMobile = userMobile;
        this.userId = userId;
        this.userLastExperience = userLastExperience;
        this.userLevelName = userLevelName;
        this.userBirthday = userBirthday;
        this.userProvince = userProvince;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUserSex() {
        return this.userSex;
    }
    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }
    public String getUserLastX() {
        return this.userLastX;
    }
    public void setUserLastX(String userLastX) {
        this.userLastX = userLastX;
    }
    public String getUserNickname() {
        return this.userNickname;
    }
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    public String getUserIcon() {
        return this.userIcon;
    }
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
    public String getUserMobile() {
        return this.userMobile;
    }
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserLastExperience() {
        return this.userLastExperience;
    }
    public void setUserLastExperience(String userLastExperience) {
        this.userLastExperience = userLastExperience;
    }
    public String getUserLevelName() {
        return this.userLevelName;
    }
    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }
    public long getUserBirthday() {
        return this.userBirthday;
    }
    public void setUserBirthday(long userBirthday) {
        this.userBirthday = userBirthday;
    }
    public String getUserProvince() {
        return this.userProvince;
    }
    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }
}

