package test_equals;

import java.awt.Point;

public class TempBean {
	
	String name;
	int age;
	String type;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	@Override
//	public String toString() {
//		return this.getName() + "-" + this.getAge() + "-" + this.getType();
//	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TempBean))
			return false;
		TempBean bean = (TempBean) obj;
		
		System.out.println(this.getName().equals(bean.getName()));
		System.out.println(this.getAge() == bean.getAge());
		System.out.println(this.getType().equals(bean.getType()));
		
		return this.getName().equals(bean.getName()) && 
				this.getAge() == bean.getAge() && 
				this.getType().equals(bean.getType());
	}
	
	
}
