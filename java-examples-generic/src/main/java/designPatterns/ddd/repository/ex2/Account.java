package designPatterns.ddd.repository.ex2;

/**
 * Created by DannyG on 05/05/2016.
 */
public class Account {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public boolean hasUseName(String desiredUserName) {
        return this.userName.equals(desiredUserName);
    }

    public boolean ageBetween(int minAge, int maxAge) {
        return age >= minAge && age <= maxAge;
    }
}
