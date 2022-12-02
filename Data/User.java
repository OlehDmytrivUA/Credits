package Data;

public class User { //клас користувача з потрібними методами
    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;
    private String CreditCard;

    public User(){};

    public User(String userName, String password, String firstName, String lastName, String creditCard) {
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        CreditCard = creditCard;
    }

    public User(String userName) {
        UserName = userName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }
}
