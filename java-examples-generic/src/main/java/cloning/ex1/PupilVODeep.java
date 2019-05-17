package cloning.ex1;


	public class PupilVODeep implements Cloneable { 
		// Contained object
		private SubjectVO subj;
		private String name;

		public PupilVODeep (String name, String sub) { 
			this.name = name;
			this.subj = new SubjectVO(sub);
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




		public Object clone() { // deep copy
			PupilVODeep pupil = new PupilVODeep(name, subj.getName());
			return pupil; 
			}
		
			//Read more: http://mrbool.com/what-is-deep-copy-and-shallow-copy-in-java/28569#ixzz35j9cfFLE

	}

	

