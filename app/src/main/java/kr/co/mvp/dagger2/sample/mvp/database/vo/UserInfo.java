package kr.co.mvp.dagger2.sample.mvp.database.vo;

import android.support.annotation.NonNull;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by my on 2016-12-20.
 */

@DatabaseTable
public class UserInfo {
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserclass() {
        return userclass;
    }

    public void setUserclass(String userclass) {
        this.userclass = userclass;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }


    @DatabaseField(unique = true,id = true)
    private String userid;

    @DatabaseField
    private String username;
    @DatabaseField
    private String userclass;
    @DatabaseField
    private String userage;


}
