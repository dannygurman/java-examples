package compareSort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog implements Comparator<Dog>, Comparable<Dog> {
    private String name;
    private int age;


    // Overriding the compareTo method
    public int compareTo(Dog d){
        return (this.name).compareTo(d.name);
    }

    // Overriding the compare method to sort the age
    public int compare(Dog d, Dog d1){
        return d.age - d1.age;
    }

  }