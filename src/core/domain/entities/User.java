package core.domain.entities;

public class User extends Base {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int isAdmin;
    private String deletedAt;


    public User() {
    }

    public User(int id, String firstName, String lastName) {
        super(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public User(int id, String firstName, String lastName, String userName, String password, int isAdmin) {
        super(id);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setPassword(password);
        setAdmin(isAdmin);
        setDeletedAt(null);
    }

    public User(String firstName, String lastName, String userName, String password, int isAdmin) {
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setPassword(password);
        setAdmin(isAdmin);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isAdmin() {
        return isAdmin;
    }

    public void setAdmin(int admin) {
        isAdmin = admin;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}
