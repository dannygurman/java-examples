package concurrency.threadSafety;

public class ImmutableValue{

	private int value = 0;

	public ImmutableValue(int value){
		this.value = value;
	}

	public int getValue(){
		return this.value;
	}


	public ImmutableValue add(int valueToAdd){
		return new ImmutableValue(this.value + valueToAdd);
	}

}