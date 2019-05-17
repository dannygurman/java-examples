package designPatterns.ddd.repository.ex1;

/**
 * Created by DannyG on 02/05/2016.
 */
import java.util.Objects;
import java.util.UUID;

/**
 * The first part is to define "Entities". An "Entity" is an object-oriented abstraction of a part of the reality.
 * For example when we create a contact management app a possible Entity would be "Person".
 An entity has an identity: The state of an entity can change over time but it is still the  "same" entity.
 For example the name of a person may change but it is still the same person. Another important point:
 Two persons may have the same name (and other properties) but they are still different persons.
 This means the distinction of instances of entities isn't done based on their properties.
 Instead we need an identifier. I'm typically using a UUID.

 A base class for entities can look like this:
 */
public abstract class Identity {

    //  The id is marked as final and therefore can't be changed after it is assigned in the constructor.
    private final String id;

    public Identity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(id, identity.id);
    }
//As you can see the equals and hashcode methods are only working with the id property.

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + id.substring(0,8) + "...]";
    }
}