package stringBufferVsStringBuilder;

import org.junit.Test;

public class StringBufferVsStringBulderTest {

//StringBuilder is faster then stringbuffer cause it is not synchronized
    @Test
    public  void testStringBufferVsBuilder() {
        int N = 77777777;
        long t;

        {
            StringBuffer sb = new StringBuffer();
            t = System.currentTimeMillis();
            for (int i = N; i --> 0 ;) {
                sb.append("");
            }

            System.out.println(System.currentTimeMillis() - t);
           //Result -  2247
        }

        {
            StringBuilder sb = new StringBuilder();
            t = System.currentTimeMillis();
            for (int i = N; i > 0 ; i--) {
                sb.append("");
            }
            System.out.println(System.currentTimeMillis() - t);
            //Result -  94
        }
    }

}
