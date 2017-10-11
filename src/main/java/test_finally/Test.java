package test_finally;

public class Test {

	public static int test1() {
		int i = 1;

		if (i == 1)
			return 0;
		System.out.println("the previous statement of try block");
		i = i / 0;

		try {
			System.out.println("try block");
			return i;
		} finally {
			System.out.println("finally block");
		}
	}

	public static int test2() {
		int i = 1;

		try {
			System.out.println("try block");
			System.exit(0);
			return i;
		} finally {
			System.out.println("finally block");
		}
	}

	public static int test3() {
		try {
			return 0;
		} finally {
			return 1;
		}
	}

	public static int test() {
		int i = 1;
		try {
			return i;
		} finally {
			i = 9;
			i += 9;
			i++;
			++i;
			System.out.println(i);
		}
	}

	public static String test6() {
		try {
			System.out.println("try block");
			return test5();
		} finally {
			System.out.println("finally block");
		}
	}

	public static String test5() {
		System.out.println("return statement");
		return "after return";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("return value of test(): " + test());
	}

}
