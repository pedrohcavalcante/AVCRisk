package testPackage;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mainPackage.CelsiusTemperature;
import mainPackage.FahrenheitTemperature;
import mainPackage.TemperatureTransformer;

@RunWith(Parameterized.class)
@SuiteClasses({})
public class TemperatureTransformerTest {

	private double input;
	private double expected;
	private boolean type; // TRUE for Celsius, FALSE for Fahrenheit

	public TemperatureTransformerTest(double input, double expected, boolean type) {
		this.input = input;
		this.expected = expected;
		this.type = type;
	}
	
	@Rule
	public ExpectedException ee = ExpectedException.none();

	@Parameters
	public static Collection<Object[]> testTemperatures() {
		return Arrays.asList(new Object[][] { 
			// Testes com input Celsius
				{ 0, 32, true }, { 15, 59, true }, { 50, 122, true }, { -10, 14, true },
			// Testes com input Fahrenheit
				{ 32, 0, false }, { 59, 15, false }, { 122, 50, false }, { 14, -10, false }, 
			// Testes com input inválidos Celsius
				{-273, -459.4, true},
				{-273.1, -459.58, true},
				{-275, -463, true},
			// Testes com input inválidos Fahrenheit
				{-459.4, -273, false},
				{-459, -272.778, false},
				{-900, -517, false}, });
	}

	@Test
	public void convertTest() throws Exception{
		TemperatureTransformer tmp = new TemperatureTransformer();
		if (type == true) {
			
			CelsiusTemperature ce = new CelsiusTemperature();
			if (input < ce.getZERO()) {
				ee.expectMessage("Nao ha temperatura abaixo do zero absoluto");
			}
			ce.setValue(input);
			assertEquals(expected, tmp.convert(ce).getValue(), 0.0);
		} else {
			
			FahrenheitTemperature fa = new FahrenheitTemperature();
			if (input < fa.getZERO()) {
				 ee.expectMessage("Nao ha temperatura abaixo do zero absoluto");
			}
			fa.setValue(input);
			assertEquals(expected, tmp.convert(fa).getValue(), 0.0);
		}
	}
}
