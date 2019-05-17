package cloning.ex1;


	public class PupilVOShallow implements Cloneable { 
		// Contained object
		private SubjectVO subj;
		private String name;

		public PupilVOShallow (String name, String sub) { 
			this.name = name; this.subj = new SubjectVO(sub); 
		} 

		/** * @return the subj */
		public SubjectVO getSubj() {
			return subj; 
		} 


		/** * @param subj * the subj to set */
		public void setSubj(SubjectVO subj) {
			this.subj = subj; 
		} 

		/** * @return the name */ public String getName() { 		
			return name;
		}

		/** * @param name * the name to set */
		public void setName(String name) {
			this.name = name; 
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

