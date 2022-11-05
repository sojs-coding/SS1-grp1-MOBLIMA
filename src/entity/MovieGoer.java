package entity;

public class MovieGoer {
    private String name;
    private int mobile;
    private String email;

    public MovieGoer(String username, int mobile, String email) {
        setEmail(email);
        setMobile(mobile);
        setName(username);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    private void setMobile(int mobile) {
        // Set up verification. 8 digits only.
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
