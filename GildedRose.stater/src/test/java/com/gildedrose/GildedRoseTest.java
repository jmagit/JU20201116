package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.gildedrose.core.SpaceCamelCase;

@DisplayNameGeneration(SpaceCamelCase.class)
class GildedRoseTest {

	@Test
	void minQualityInvalidTest() {
		Item product = new Item("Elixir of the Mongoose", 1, -1);
		assertThrows(Exception.class, () -> new GildedRose(new Item[] { product }));
	}

	@Test
	void maxQualityInvalidTest() {
		Item product = new Item("+5 Dexterity Vest", 1, 80);
		assertThrows(Exception.class, () -> new GildedRose(new Item[] { product }));
	}

	@Test
	void sulfurasQualityInvalidTest() {
		Item product = new Item("Sulfuras, Hand of Ragnaros", 1, 66);
		assertThrows(Exception.class, () -> new GildedRose(new Item[] { product }));
	}

	@Test
	@Disabled
	void privateRefInvalidTest() {
		Item product = new Item("Elixir of the Mongoose", 1, 10);
		GildedRose app = new GildedRose(new Item[] { product });
		product.sellIn = 0;
		assertEquals(1, app.items[0].sellIn);
	}

	@ParameterizedTest
	@CsvSource({ "1, 80, 1, 80", "-1, 80, -1, 80", })
	void productSulfurasValidTest(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Sulfuras, Hand of Ragnaros";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(80, product.quality, "quality"));
	}

	@ParameterizedTest
	@CsvSource({ "-1, 1, -2, 0", "-10, 3, -11, 1", "0, 0, -1, 0", " 7, 6, 6, 5", })
	void otherProductTest(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Normal Product";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest
	@CsvSource({ "-2, 49, -3, 50", "-1, 48, -2, 50", "2, 50, 1, 50", "2, 0, 1, 1", })
	void productAgedBrieTest(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Aged Brie";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@ParameterizedTest
	@CsvSource({ "11, 0, 10, 1", "7, 1, 6, 3", "7, 49, 6, 50", "5, 3, 4, 6", "0, 3, -1, 0", "-1, 3, -2, 0", })
	void productBackstagePassesTest(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Backstage passes to a TAFKAL80ETC concert";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

	@Disabled
	@ParameterizedTest
	@CsvSource({ "11, 10, 10, 8", "7, 1, 6, -1", "-5, 10, -6, 6", "0, 3, -1, 0", })
	void productConjuredTest(int sellIn, int quality, int sellInResult, int qualityResult) {
		String name = "Conjured Mana Cake";
		Item product = new Item(name, sellIn, quality);
		GildedRose app = new GildedRose(new Item[] { product });
		app.updateQuality();
		assertAll(name, () -> assertEquals(name, product.name, "name"),
				() -> assertEquals(sellInResult, product.sellIn, "sellIn"),
				() -> assertEquals(qualityResult, product.quality, "quality"));
	}

}
