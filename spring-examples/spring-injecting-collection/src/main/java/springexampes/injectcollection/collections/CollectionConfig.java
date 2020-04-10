package springexampes.injectcollection.collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springexampes.injectcollection.collections.ex1.CollectionsBean1;
import springexampes.injectcollection.collections.ex2.CollectionsBean2;
import springexampes.injectcollection.collections.ex3.CollectionsBean3;

import java.util.*;

@Configuration
public class CollectionConfig {

    @Bean
    public CollectionsBean1 getCollectionsBean1() {
        return new CollectionsBean1();
    }

    @Bean
    public List<String> nameList() {
        return Arrays.asList("John", "Adam", "Harry");
    }

    @Bean
    public CollectionsBean2 getCollectionsBean2() {
        return new CollectionsBean2(new HashSet<>(Arrays.asList("John2", "Adam2", "Harry2")));
    }

    @Bean
    public CollectionsBean3 getCollectionsBean3() {
        return new CollectionsBean3();
    }


    @Bean
    public Map<Integer, String> nameMap(){
        Map<Integer, String>  nameMap = new HashMap<>();
        nameMap.put(1, "John3");
        nameMap.put(2, "Adam3");
        nameMap.put(3, "Harry3");
        return nameMap;
    }


}
