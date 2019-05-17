package passingParameter.ex3;

public class TestPassReference {

	public static void main(String[] args) {
		Counter counter = new Counter();
		CounterRepo counterRepo1 =new CounterRepo("1",counter);
		CounterRepo counterRepo2 =new CounterRepo("2",counter);
		
		counterRepo1.increment();
		counterRepo1.printVal();
		
		counterRepo2.printVal();
		counterRepo2.increment();
		counterRepo2.printVal();
		
		counterRepo1.printVal();
		counterRepo1.increment();
		counterRepo1.printVal();
		
		counterRepo2.printVal();
		
	}
}
