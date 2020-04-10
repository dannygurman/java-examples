package springexampes.injectcollection.collections.ex3;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CollectionsBean3 {

    //Map With Setter Injection

    private Map<Integer, String> nameMap;

    @Autowired
    public void setNameMap(Map<Integer, String> nameMap) {
        this.nameMap = nameMap;
    }

    public void printNameMap() {
        System.out.println(nameMap);
    }

}
