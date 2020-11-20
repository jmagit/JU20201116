package com.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import com.example.Application;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ApplicationTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_uno_de_dos_Divide() {
		Application application = new Application();
		
		double actual = application.divide(4, 2);
		
		assertAll("Verifica", 
			() -> assertEquals(2, application.divide(4, 2)),
			() -> assertEquals(1.5, application.divide(3, 2))
			// , () -> assertEquals(Double.POSITIVE_INFINITY, application.divide(2, 0))
			// , () -> assertThrows(Exception.class, () -> application.divide(2, 0))
		);
	}
	
	@Test
	@DisplayName(value = "Casos invalidos de la division")
//	@Disabled
	void testDivide2() {
		Application application = new Application();
		assertThrows(ArithmeticException.class, () -> application.divide(2, 0));
//		// double actual = 2; // application.divide(4, 2);
//		assertAll("Verifica", 
//			() -> assertNotNull(application),
//			// () -> assertDoesNotThrow(NullPointerException.class, () -> application.divide(4, 2)),
//			() -> assertEquals(3, actual, "Error en la división ...")
//		);
	}

}
