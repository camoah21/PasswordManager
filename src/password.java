public class password {
    int id;
    int userid;
    String websiteName;
    String websiteUrl;
    String userName;
    String Password;

    public password(int id, String name, String url, String username, String password) {
        this.id = id;
        this.websiteName = name;
        this.websiteUrl = url;
        this.userName = username;
        this.Password = password;
    }

    public int getId() {
        return this.id;
    }

    public int getUserId() {
        return userid;
    }

    public String getWebsiteName() {
        return this.websiteName;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getWebsiteUrl() {
        return this.websiteUrl;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUrl(String url) {
        this.websiteUrl = url;
    }

    public void setWebName(String name) {
        this.websiteName = name;
    }

    public void setPassword(String name) {
        this.Password = name;
    }

    public void setUsername(String name) {
        this.userName = name;
    }

}
