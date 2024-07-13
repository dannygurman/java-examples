package cloning.ex1;

import lombok.Data;

@Data
public class PupilVODeep implements Cloneable {
	// Contained object
	private SubjectVO subj;
	private String name;

	public PupilVODeep (String name, String sub) {
		this.name = name;
		this.subj = new SubjectVO(sub);
	}


	public Object clone() { // deep copy
		PupilVODeep pupil =
				new PupilVODeep(name, subj.getName());
		return pupil;
	}

	//Read more: http://mrbool.com/what-is-deep-copy-and-shallow-copy-in-java/28569#ixzz35j9cfFLE

}

	

