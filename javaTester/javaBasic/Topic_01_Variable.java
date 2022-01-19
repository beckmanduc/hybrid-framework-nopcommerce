package javaBasic;

public class Topic_01_Variable {
	long studentNumber;
	static boolean statusValue;
	static final String BROWSER_NAME = "Chrome";
	String studentName = "duc";
	
	public static void main(String[] args) {
		Topic_01_Variable dt = new Topic_01_Variable();
		System.out.println(dt.BROWSER_NAME);
//		long a;
//		System.out.println(a);

	}
	
	// Getter: getCurrentUrl()/getTitle()/getText()/getSize()...
	public String getSetudentName() {
		return this.studentName;
		
	}
	
	//Setter: click/senkey/clear/select
	public void setStudentName(String stdName) {
		this.studentName = stdName;
		
	}


}
