package javaVersions.v15.records;

    /**
     * Without Records - Prior to records, we would create an immutable data transfer object (DTO) as below
     *
     * Notice that there's a lot of code here to create an immutable object that really just holds the state.
     *
     * All of our fields are explicitly defined using final,
     * we have a single all-arguments constructor, and we have an accessor method for every field.
     *
     * In some cases, we might even declare the class itself as final to prevent any sub-classing
     *
     * In many cases, we would also go a step further and override the toString method to   provide meaningful logging output.
     * We would probably also want to override the equals and hashCode methods to avoid unexpected
     * consequences when comparing two instances of these objects.
     *

     */
    public class ImmutablePersonWithoutRecord  {

        private final String name;
        private final int age;

        public ImmutablePersonWithoutRecord(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

}
