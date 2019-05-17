package concurrency.completableFuture.common;

public class User {

    public User(Double creditRating) {
        this.creditRating = creditRating;
    }

    private Double creditRating;

    public Double getCreditRating() {
        return creditRating;
    }
}
