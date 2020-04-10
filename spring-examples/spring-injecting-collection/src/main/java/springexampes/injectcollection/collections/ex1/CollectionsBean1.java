package springexampes.injectcollection.collections.ex1;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CollectionsBean1 {

    //List With @Autowired

    @Autowired
    private List<String> nameList;

    public void printNameList() {
        System.out.println(nameList);
    }

}
