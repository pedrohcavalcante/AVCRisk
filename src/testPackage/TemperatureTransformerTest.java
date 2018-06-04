package testPackage;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
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

	@Parameters
	public static Collection<Object[]> testTemperatures() {
		return Arrays.asList(new Object[][] { { 0, 32, true }, { 15, 59, true }, { 50, 122, true }, { -10, 14, true },
				{ 32, 0, false }, { 59, 15, false }, { 122, 50, false }, { 14, -10, false }, });
	}

	@Test
	public void convertTest() throws Exception{
		TemperatureTransformer tmp = new TemperatureTransformer();

		if (type == true) {
			CelsiusTemperature ce = new CelsiusTemperature();
			ce.setValue(input);
			assertEquals(expected, tmp.convert(ce).getValue(), 0.0);
		} else {
			FahrenheitTemperature fa = new FahrenheitTemperature();
			fa.setValue(input);
			assertEquals(expected, tmp.convert(fa).getValue(), 0.0);
		}
	}
}
