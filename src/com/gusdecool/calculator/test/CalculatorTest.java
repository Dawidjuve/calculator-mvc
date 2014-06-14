package com.gusdecool.calculator.test;

import com.gusdecool.calculator.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

	private Calculator model;

	@Before
	public void setUp() throws Exception {
		model = new Calculator();
	}

	@After
	public void tearDown() throws Exception {
		model.reset();
	}

	@Test
	public void testAddData() throws Exception {
		// Test input number
		String expected = "789";
		model.addData(expected);
		Calculator.Data data = model.getSequence().get(model.getSequence().size() - 1);
		assertEquals("Data value wrong", expected, data.getValue());
		assertEquals("Data type wrong", Calculator.Data.TYPE_INPUT, data.getType());
	}

	@Test
	public void testReset() throws Exception {
		model.addData("123");
		model.addData("456");
		model.reset();

		assertEquals(0, model.getSequence().size());
	}

	@Test
	public void testFindDataType() throws Exception {
		assertEquals(Calculator.Data.TYPE_INPUT, model.findDataType("123"));
	}

	@Test
	public void testCalculateResult() throws Exception {
		model.addData("1");
		model.addData("+");
		model.addData("3");
		assertEquals("4", model.calculateResult());

		model.addData("-");
		model.addData("3");
		assertEquals("1", model.calculateResult());

		model.addData("*");
		model.addData("4");
		assertEquals("4", model.calculateResult());

		model.addData("/");
		model.addData("2");
		assertEquals("2", model.calculateResult());
	}

	@Test
	public void testGetLatestInput() throws Exception {
		model.addData("2");
		assertEquals("2", model.getLastInput());
	}

	@Test
	public void testResetLatestInput() throws Exception {
		model.addData("2");
		model.addData("+");
		model.addData("3");
		model.resetLastInput();
		assertEquals("0", model.getLastInput());
		assertEquals(3, model.getSequence().size());
	}

	@Test
	public void testEditLatestInput() throws Exception {
		model.addData("2");
		model.addData("+");
		model.addData("3");
		model.editLatestInput("5");
		assertEquals("5", model.getLastInput());
		assertEquals(3, model.getSequence().size());
	}
}