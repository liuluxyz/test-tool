package test_class;

public class Parse {

	private String name;
	
	public Parse(){
		name = "default";
	}
	
	public Parse(String value){
		this.name = value;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
