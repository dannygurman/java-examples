package finalExamples;

public class FinalVariableExample1 {

        static final int CAPACITY = 4;

        public static void main(String args[])
        {
            // re-assigning final variable
            // will throw compile-time error
          //Error - could not assign final:
            //  CAPACITY = 5;
        }

}
