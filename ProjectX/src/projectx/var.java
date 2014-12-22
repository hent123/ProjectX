package projectx;

public class var {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getSystemProperty();
	}
	public static void getSystemProperty() {
		String path = System.getProperty("java.library.path");
		System.out.println(path);
		}
}
