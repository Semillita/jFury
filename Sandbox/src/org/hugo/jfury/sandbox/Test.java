package org.hugo.jfury.sandbox;

public enum Test {
	
	First(1),
	Second(2),
	Third(3),
	Fourth(4);
	
	private int num;
	
	Test(int num) {
		this.num = num;
	}
	
	public static boolean isSame(Test test1, Test test2) {
		return test1.num == test2.num;
	}
	
}
