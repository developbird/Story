package bean;

/**
 * Created by Administrator on 2016/8/24.
 */
public class GetUser {

        private String id;
        private String username;
        private String usersex;
        private String useremail;
        private String nickname;
        private String birthday;
        private String portrait;
        private String signature;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsersex() {
            return usersex;
        }

        public void setUsersex(String usersex) {
            this.usersex = usersex;
        }

        public Object getUseremail() {
            return useremail;
        }

        public void setUseremail(String useremail) {
            this.useremail = useremail;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

    @Override
    public String toString() {
        return "GetUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", usersex='" + usersex + '\'' +
                ", useremail='" + useremail + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", portrait='" + portrait + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
