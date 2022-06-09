package lt.codeacadmy;

import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class Users {
    private ObjectId id;
    private String username;
    private String password;
    private BigDecimal balance;

    public Users() {
    }

    public Users(ObjectId id, String username, String password, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
