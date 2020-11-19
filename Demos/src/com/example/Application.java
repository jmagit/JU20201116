package com.example;

public class Application {

	public static void main(String[] args) {
		Application app = new Application();
		
		System.out.println(app.divide(4, 2));

	}
	
	public double divide(double a, double b) {
		return a / b;
	}

}
