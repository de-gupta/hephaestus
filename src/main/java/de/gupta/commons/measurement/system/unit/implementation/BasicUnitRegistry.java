package de.gupta.commons.measurement.system.unit.implementation;

import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;
import de.gupta.commons.utility.math.FunctionUtility;

import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public enum BasicUnitRegistry
{
	DIMENSIONLESS(new BasicUnit("", Set.of(""), DoubleUnaryOperator.identity(),
			MeasurementDimensionRegistry.DIMENSIONLESS.dimension())),

	METER(new BasicUnit("meter", Set.of("meter", "metre", "metres", "meters"), Set.of("m"),
			DoubleUnaryOperator.identity(), MeasurementDimensionRegistry.LENGTH.dimension())),

	LITER(new BasicUnit("liter", Set.of("l", "liter", "litre", "liters", "litres"),
			FunctionUtility.fromMultiplication(0.001),
			MeasurementDimensionRegistry.VOLUME.dimension())),

	GRAM(new BasicUnit("gram", Set.of("gram", "grams"), Set.of("g"), FunctionUtility.fromMultiplication(1e-3),
			MeasurementDimensionRegistry.MASS.dimension())),
	POUND(new BasicUnit("lb", Set.of("pound", "pounds"), FunctionUtility.fromMultiplication(0.45359237),
			MeasurementDimensionRegistry.MASS.dimension())),
	OUNCE(new BasicUnit("oz", Set.of("ounce", "ounces"), FunctionUtility.fromMultiplication(28.3495231e-3),
			MeasurementDimensionRegistry.MASS.dimension())),

	SECOND(new BasicUnit("second", Set.of("sec", "second", "seconds"), Set.of("s"), DoubleUnaryOperator.identity(),
			MeasurementDimensionRegistry.TIME.dimension())),
	MINUTE(new BasicUnit("minute", Set.of("min", "minute", "minutes"), FunctionUtility.fromMultiplication(60),
			MeasurementDimensionRegistry.TIME.dimension())),
	HOUR(new BasicUnit("hour", Set.of("hr", "hour", "hours"), FunctionUtility.fromMultiplication(3600),
			MeasurementDimensionRegistry.TIME.dimension())),
	DAY(new BasicUnit("day", Set.of("day", "days"), FunctionUtility.fromMultiplication(86400),
			MeasurementDimensionRegistry.TIME.dimension())),
	WEEK(new BasicUnit("week", Set.of("week", "weeks"), FunctionUtility.fromMultiplication(604800),
			MeasurementDimensionRegistry.TIME.dimension())),
	YEAR(new BasicUnit("year", Set.of("year", "years"), Set.of("y"), FunctionUtility.fromMultiplication(31556952),
			MeasurementDimensionRegistry.TIME.dimension())),

	CELSIUS(new BasicUnit("celsius", Set.of(), Set.of("°C", "C"),
			FunctionUtility.fromAdditionAndMultiplication(273.15, 1.0),
			MeasurementDimensionRegistry.TEMPERATURE.dimension())),
	FAHRENHEIT(new BasicUnit("fahrenheit", Set.of(), Set.of("°F", "F"),
			FunctionUtility.fromAdditionAndMultiplication(-32.0, 5.0 / 9.0)
						   .andThen(FunctionUtility.fromAdditionAndMultiplication(273.15, 1.0)),
			MeasurementDimensionRegistry.TEMPERATURE.dimension())),
	KELVIN(new BasicUnit("kelvin", Set.of(), Set.of("K"), DoubleUnaryOperator.identity(),
			MeasurementDimensionRegistry.TEMPERATURE.dimension())),

	JOULE(new BasicUnit("joule", Set.of("joule", "joules"), Set.of("J"), DoubleUnaryOperator.identity(),
			MeasurementDimensionRegistry.ENERGY.dimension())),
	CALORIE(new BasicUnit("cal", Set.of("cal", "calorie", "calories"), FunctionUtility.fromMultiplication(4.184),
			MeasurementDimensionRegistry.ENERGY.dimension()));

	private final BasicUnit unit;

	public BasicUnit basicUnit()
	{
		return unit;
	}

	BasicUnitRegistry(BasicUnit unit)
	{
		this.unit = unit;
	}
}