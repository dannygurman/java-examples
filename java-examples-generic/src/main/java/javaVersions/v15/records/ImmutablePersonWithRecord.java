package javaVersions.v15.records;

/*
Using the new record class, we can define the same immutable data object in a much more compact way
The class definition has a new syntax that is specific for records.
This header is where we provide the details about the fields inside the record.

Using this header, the compiler can infer the internal fields.
This means we don't need to define specific member variables and accessors,
 as they're provided by default. We also don't have to provide a constructor.

Additionally, the compiler provides sensible implementations for the toString, equals, and hashCode methods.
 */
public record ImmutablePersonWithRecord (String name, int age) {

}
