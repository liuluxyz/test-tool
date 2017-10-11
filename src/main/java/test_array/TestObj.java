package test_array;

/**
 * liulu5
 * 2015-11-2
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-11-2 下午5:42:08 
 */
public class TestObj {

	private String a;
	private int b;
	
	public TestObj(String a, int b){
		this.a = a;
		this.b = b;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof TestObj))
			return false;
		
		TestObj other = (TestObj)obj;
		if(this.getA() == null)
			return (other.getA() == null) && this.getB() == other.getB();
		else
			return (this.getA().equals(other.getA())) && this.getB() == other.getB();
	}
}

