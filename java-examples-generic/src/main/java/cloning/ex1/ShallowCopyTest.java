package cloning.ex1;

public class ShallowCopyTest {
	public static void main(String[] args) {
		// Original Object
		PupilVOShallow stud = new PupilVOShallow("Johnathan", "Algebra");
		System.out.println("Original Object: " + stud.getName() + " - " + stud.getSubj().getName()); 

		// Clone Object
		PupilVOShallow clonedStud = (PupilVOShallow) stud.clone();
		System.out.println("Cloned Object: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());

		stud.setName("Daniel");
		stud.getSubj().setName("Physics");

		System.out.println("Original Object after it is updated: " + stud.getName() + " - " + stud.getSubj().getName());

		System.out.println("Cloned Object after updating original object: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());
	}


	//	Read more: http://mrbool.com/what-is-deep-copy-and-shallow-copy-in-java/28569#ixzz35j9zKA4r
}
