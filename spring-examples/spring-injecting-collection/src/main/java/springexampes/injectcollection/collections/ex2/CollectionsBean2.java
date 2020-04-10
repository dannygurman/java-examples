package springexampes.injectcollection.collections.ex2;

import java.util.Set;

public class CollectionsBean2 {
    //Set With Constructor Injection

    private Set<String> nameSet;

    public CollectionsBean2(Set<String> strings) {
        this.nameSet = strings;
    }

    public void printNameSet() {
        System.out.println(nameSet);
    }

}
