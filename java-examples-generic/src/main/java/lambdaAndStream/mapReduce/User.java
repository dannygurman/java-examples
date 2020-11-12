package lambdaAndStream.mapReduce;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//You need to Enable Annotation Processing on IntelliJ IDEA
// > Settings > Build, Execution, Deployment > Compiler > Annotation Processors
//need Lombok Plugin
public class User {
    String name;
    int age;
}
