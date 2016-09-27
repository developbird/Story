package bean;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class GetStory {

    /**
     * id : 39
     * story_time : 1440668612
     * story_info : ceshiceshi
     * pics : null
     * uid : 29
     * lng : null
     * lat : null
     * city : null
     * readcount : 46
     * comment : 0
     * user : {"id":"29","username":"zz","usersex":null,"useremail":null,"nickname":"zz","birthday":null,"portrait":null,"signature":null}
     */

    private String id;
    private String story_time;
    private String story_info;
    private String pics;
    private String uid;
    private Object lng;
    private Object lat;
    private String city;
    private String readcount;
    private String comment;
    private GetUser getUser;
    /**
     * id : 29
     * username : zz
     * usersex : null
     * useremail : null
     * nickname : zz
     * birthday : null
     * portrait : null
     * signature : null
     */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStory_time() {
        return story_time;
    }

    public void setStory_time(String story_time) {
        this.story_time = story_time;
    }

    public String getStory_info() {
        return story_info;
    }

    public void setStory_info(String story_info) {
        this.story_info = story_info;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReadcount() {
        return readcount;
    }

    public void setReadcount(String readcount) {
        this.readcount = readcount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGetUser(GetUser getUser) {
        this.getUser = getUser;
    }

    public GetUser getGetUser() {
        return getUser;
    }

    @Override
    public String toString() {
        return "GetStory{" +
                "id='" + id + '\'' +
                ", story_time='" + story_time + '\'' +
                ", story_info='" + story_info + '\'' +
                ", pics='" + pics + '\'' +
                ", uid='" + uid + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", city='" + city + '\'' +
                ", readcount='" + readcount + '\'' +
                ", comment='" + comment + '\'' +
                ", getUser=" + getUser +
                '}';
    }
}
