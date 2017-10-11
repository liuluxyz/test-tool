package test_enum;

import java.util.Arrays;

public class Test_enum {
	
	public enum ExecuteType {
		PROV,
		LATN;
	}
	
	/**
	 * @param args
	 * 下午4:28:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecuteType[] types = new ExecuteType[]{
				ExecuteType.LATN,
				ExecuteType.PROV,
				ExecuteType.LATN,
				ExecuteType.PROV
		};
		System.out.println(Arrays.toString(types));
		Arrays.sort(types);
		System.out.println(Arrays.toString(types));
		
		for(ExecuteType type : types){
			System.out.println(type.name() + "- " + type.ordinal());
		}
//		System.out.println(ExecuteType.PROV);
//		System.out.println(ExecuteType.PROV.name());
//		System.out.println(ExecuteType.PROV.toString());
//		
//		if("PROV".equals(ExecuteType.PROV.toString())){
//			System.out.println("equals");
//		}
	}

}
