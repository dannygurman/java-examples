package javaVersions.v15.records;

/**
 * While records eliminate a lot of boilerplate code, they do allow us to override some of the default behaviors.
 * For example, we could define a canonical constructor that does some validation:
 */
public record ImmutablePersonWithRecordV2(String name, int age)  {
    public ImmutablePersonWithRecordV2 {
        if(age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

}
