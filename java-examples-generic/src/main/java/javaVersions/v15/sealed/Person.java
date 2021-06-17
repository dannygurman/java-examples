package javaVersions.v15.sealed;

/**
 * Till sealed classes, Java did not provided fine-grained control over the inheritance.
 * Access modifiers such as public, protected, private, as well as the default package-private,
 * provide very coarse-grained control.
 *
 * The goal of sealed classes is to allow individual classes to declare which types may be used as sub-types.
 * This also applies to interfaces and determining which types can implement them.
 *
 * Sealed classes involve two new keywords — sealed and permits.
 * permits- specified which classes can extend it.
 *
 * any class that extends a sealed class must itself be declared sealed, non-sealed, or final.
 * This ensures the class hierarchy remains finite and known by the compiler.
 */
public abstract sealed class Person permits Employee, Student, Manager {


}
