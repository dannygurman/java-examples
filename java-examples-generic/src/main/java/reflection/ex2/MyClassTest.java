package reflection.ex2;

import java.lang.reflect.Field;

public class MyClassTest {

    static MyClass mc = new MyClass();

    public static void main(String[] args) {
        Class clazz = mc.getClass();
        Field [] fields = clazz.getDeclaredFields();
        for (Field field: fields){
            Class type = field.getType();
            System.out.println(type.getCanonicalName());
        }
        int x = fields.length;


    }
}
