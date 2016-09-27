package bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class GetMyStory {

    /**
     * id : 104
     * story_time : 1440729655
     * story_info : 倾听你们的故事
     * pics : ["20150828/55dfca3787732.png","20150828/55dfca3787f3f.png","20150828/55dfca37885a1.png"]
     * uid : 1
     * lng : 18.00
     * lat : 19.00
     * city : 北京
     * readcount : 0
     * comment : 0
     */

    private String id;
    private String story_time;
    private String story_info;
    private String uid;
    private String lng;
    private String lat;
    private String city;
    private String readcount;
    private String comment;
    private List<String> pics;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
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

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
