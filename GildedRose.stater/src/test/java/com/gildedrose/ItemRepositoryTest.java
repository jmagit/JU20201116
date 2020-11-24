package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.dbunit.DefaultOperationListener;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {
	private ItemRepository dao;
	private IDatabaseTester databaseTester;
	private IDatabaseConnection connection;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		// Obtener instancia del DAO que testeamos
		dao = ItemRepositoryImp.getInstance();

		databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/DBUNIT", "root",
				"root");
		// Inicializar la BD
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSet = builder.build(this.getClass().getResourceAsStream("/db-init.xml"));
		databaseTester.setDataSet(dataSet);
		databaseTester.setOperationListener(new DefaultOperationListener() {
			@Override
			public void connectionRetrieved(IDatabaseConnection connection) {
				connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
						new MySqlDataTypeFactory());
				super.connectionRetrieved(connection);
			}
		});
		databaseTester.onSetup();
		connection = databaseTester.getConnection();
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.close();
		databaseTester.onTearDown();
	}

	@Test
	void getAllTest() throws Exception {
		List<Item> actual = dao.getAll();
		Item product = actual.get(2);
		assertEquals(10, actual.size());
		assertAll("Aged Brie", 
				() -> assertEquals("Aged Brie", product.name, "name"),
				() -> assertEquals(2, product.sellIn, "sellIn"), 
				() -> assertEquals(0, product.quality, "quality"));
	}
	@Test
	void getOneTest() throws Exception {
		Optional<Item> actual = dao.getOne(3);
		assertTrue(actual.isPresent());
		Item product = actual.get();
		assertAll("Aged Brie", 
				() -> assertEquals("Aged Brie", product.name, "name"),
				() -> assertEquals(2, product.sellIn, "sellIn"), 
				() -> assertEquals(0, product.quality, "quality"));
	}
	@Test
	void deleteByIdTest() throws Exception {
		dao.deleteById(3);
		List<Item> actual = dao.getAll();
		assertEquals(9, actual.size());
	}

}
