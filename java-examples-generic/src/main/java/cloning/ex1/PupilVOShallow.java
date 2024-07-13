package cloning.ex1;

import lombok.Data;

@Data
public class PupilVOShallow implements Cloneable {
	// Contained object
	private SubjectVO subj;
	private String name;

	public PupilVOShallow (String name, String sub) {
		this.name = name;
		this.subj = new SubjectVO(sub);
	}

	// shallow copy
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}

//	Read more: http://mrbool.com/what-is-deep-copy-and-shallow-copy-in-java/28569#ixzz35j8EQYwn

