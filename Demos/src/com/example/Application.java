package com.example;

public class Application {

	public static void main(String[] args) {
		Application app = new Application();
		 double a = app.divide(4, 2);
		System.out.println(app.divide(4, 2));

	}
	
	public double divide(double a, double b) {
		if(b == 0) {
			throw new ArithmeticException("Divide by 0");
		}
		return a / b;
	}

}
