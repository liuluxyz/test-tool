package test_json;

import java.util.Map;

/**
 * liulu5
 * 2015-7-8
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-7-8 下午3:45:25 
 */
public class Student extends People{

	private String name;
	private int age;
	private String address = "add-add";
	private String[] alias;
	
	private Result res;
	
	private Map<String, String> config;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getAlias() {
		return alias;
	}
	public void setAlias(String[] alias) {
		this.alias = alias;
	}
	
	public Result getRes() {
		return res;
	}
	public void setRes(Result res) {
		this.res = res;
	}
	
	public Map<String, String> getConfig() {
		return config;
	}
	public void setConfig(Map<String, String> config) {
		this.config = config;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

