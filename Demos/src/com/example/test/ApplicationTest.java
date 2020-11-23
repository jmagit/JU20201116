package com.example.test;

import static org.junit.Assume.assumeFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.Application;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
class ApplicationTest {
	Application application;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		application = new Application();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Nested
	@Tag("Integracion")
	class Integracion {
		@Test
		@Order(4)
		@Timeout(value = 3, unit = TimeUnit.MILLISECONDS)
		void test_uno_de_dos_Divide() {

			double actual = application.divide(4, 2);

			assertAll("Verifica", () -> assertEquals(2, application.divide(4, 2)),
					() -> assertEquals(1.5, application.divide(3, 2))
			// , () -> assertEquals(Double.POSITIVE_INFINITY, application.divide(2, 0))
			// , () -> assertThrows(Exception.class, () -> application.divide(2, 0))
			);
		}
		@ParameterizedTest(name = "{index} => ''{0}'' –> {1}")
		@ValueSource(ints = {1,2,3,4,5,6})		
		@Tag("actual")
		@Order(3)
		void test_parametros_value(int valor) {
			double actual = application.divide(valor, valor);
			assertEquals(1, actual);
		}
		@Order(2)
		@ParameterizedTest(name = "{index} => ''{0} / {1}'' –> {2}")
		@CsvSource({"4,2,2","2,2,1","3,1,3","0,7,0"})
		@Smoke
		void test_parametros_2_valores(int valor1, int valor2, double esperado) {
			assumeFalse(valor1 == valor2);
			assertEquals(esperado, application.divide(valor1, valor2));
		}
	}

	@Nested
	class Unitarias {
		@Test
		@DisplayName(value = "Casos invalidos de la division")
		@Smoke
		//@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
//	@Disabled
		@Order(1)
		void testDivide2() {
//			Application application = new Application();
			assertThrows(ArithmeticException.class, () -> application.divide(2, 0));
//		// double actual = 2; // application.divide(4, 2);
//		assertAll("Verifica", 
//			() -> assertNotNull(application),
//			// () -> assertDoesNotThrow(NullPointerException.class, () -> application.divide(4, 2)),
//			() -> assertEquals(3, actual, "Error en la división ...")
//		);
		}
	}
}
