package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;

import java.util.Set;

public enum MeasurementUnitRegistry implements MeasurementUnit
{
	DIMENSIONLESS("", Set.of(), Set.of(""), 1.0, MeasurementDimensionRegistry.DIMENSIONLESS),

	METER("m", Set.of("meter", "metre", "meters", "metres"), Set.of(), 1.0, MeasurementDimensionRegistry.LENGTH),
	KILOMETER("km", Set.of("kilometer", "kilometre", "kilometers", "kilometres"), Set.of(), 1000.0, MeasurementDimensionRegistry.LENGTH),
	CENTIMETER("cm", Set.of("centimeter", "centimetre", "centimeters", "centimetres"), Set.of(), 0.01, MeasurementDimensionRegistry.LENGTH),
	MILLIMETER("mm", Set.of("millimeter", "millimetre", "millimeters", "millimetres"), Set.of(), 0.001, MeasurementDimensionRegistry.LENGTH),
	INCH("in", Set.of("inch", "inches"), Set.of("\""), 0.0254, MeasurementDimensionRegistry.LENGTH),
	FOOT("ft", Set.of("foot", "feet"), Set.of("'"), 0.3048, MeasurementDimensionRegistry.LENGTH),
	MILE("mi", Set.of("mile", "miles"), Set.of(), 1609.344, MeasurementDimensionRegistry.LENGTH),

	KILOGRAM("kg", Set.of("kilogram", "kilograms"), Set.of(), 1.0, MeasurementDimensionRegistry.MASS),
	GRAM("g", Set.of("gram", "grams"), Set.of(), 0.001, MeasurementDimensionRegistry.MASS),
	POUND("lb", Set.of("pound", "pounds"), Set.of("lbs"), 0.45359237, MeasurementDimensionRegistry.MASS),
	OUNCE("oz", Set.of("ounce", "ounces"), Set.of(), 0.0283495231, MeasurementDimensionRegistry.MASS),

	SECOND("s", Set.of("second", "seconds", "sec"), Set.of(), 1.0, MeasurementDimensionRegistry.TIME),
	MINUTE("min", Set.of("minute", "minutes"), Set.of(), 60.0, MeasurementDimensionRegistry.TIME),
	HOUR("hr", Set.of("hour", "hours"), Set.of("h"), 3600.0, MeasurementDimensionRegistry.TIME),
	DAY("d", Set.of("day", "days"), Set.of(), 86400.0, MeasurementDimensionRegistry.TIME),

	KELVIN("K", Set.of("kelvin"), Set.of(), 1.0, MeasurementDimensionRegistry.TEMPERATURE),
	CELSIUS("°C", Set.of("celsius", "centigrade"), Set.of("C"), 1.0, MeasurementDimensionRegistry.TEMPERATURE),

	CUBIC_METER("m³", Set.of("cubic meter", "cubic metre", "cubic meters", "cubic metres"), Set.of("m^3"), 1.0, MeasurementDimensionRegistry.VOLUME),
	LITER("L", Set.of("liter", "litre", "liters", "litres"), Set.of("l"), 0.001, MeasurementDimensionRegistry.VOLUME),
	MILLILITER("mL", Set.of("milliliter", "millilitre", "milliliters", "millilitres"), Set.of("ml"), 0.000001, MeasurementDimensionRegistry.VOLUME),
	GALLON("gal", Set.of("gallon", "gallons"), Set.of(), 0.00378541, MeasurementDimensionRegistry.VOLUME),

	JOULE("J", Set.of("joule", "joules"), Set.of(), 1.0, MeasurementDimensionRegistry.ENERGY),
	CALORIE("cal", Set.of("calorie", "calories"), Set.of(), 4.184, MeasurementDimensionRegistry.ENERGY),
	KILOCALORIE("kcal", Set.of("kilocalorie", "kilocalories"), Set.of("Cal"), 4184.0, MeasurementDimensionRegistry.ENERGY),

	// Composite units - velocity
	METER_PER_SECOND("m/s", Set.of("meter per second", "metres per second", "mps"), Set.of(), 1.0, MeasurementDimensionRegistry.LENGTH.divide(MeasurementDimensionRegistry.TIME)),
	KILOMETER_PER_HOUR("km/h", Set.of("kilometer per hour", "kilometres per hour", "kph"), Set.of("kmh"), 0.277778, MeasurementDimensionRegistry.LENGTH.divide(MeasurementDimensionRegistry.TIME)),
	MILE_PER_HOUR("mph", Set.of("mile per hour", "miles per hour"), Set.of(), 0.44704, MeasurementDimensionRegistry.LENGTH.divide(MeasurementDimensionRegistry.TIME)),

	// Force units
	NEWTON("N", Set.of("newton", "newtons"), Set.of(), 1.0, MeasurementDimensionRegistry.MASS.multiply(MeasurementDimensionRegistry.LENGTH).divide(MeasurementDimensionRegistry.TIME.power(2))),
	POUND_FORCE("lbf", Set.of("pound force", "pounds force"), Set.of(), 4.44822, MeasurementDimensionRegistry.MASS.multiply(MeasurementDimensionRegistry.LENGTH).divide(MeasurementDimensionRegistry.TIME.power(2))),

	// Power units
	WATT("W", Set.of("watt", "watts"), Set.of(), 1.0, MeasurementDimensionRegistry.ENERGY.divide(MeasurementDimensionRegistry.TIME)),
	KILOWATT("kW", Set.of("kilowatt", "kilowatts"), Set.of(), 1000.0, MeasurementDimensionRegistry.ENERGY.divide(MeasurementDimensionRegistry.TIME)),
	HORSEPOWER("hp", Set.of("horsepower"), Set.of(), 745.7, MeasurementDimensionRegistry.ENERGY.divide(MeasurementDimensionRegistry.TIME)),

	// Pressure units
	PASCAL("Pa", Set.of("pascal", "pascals"), Set.of(), 1.0, MeasurementDimensionRegistry.MASS.divide(MeasurementDimensionRegistry.LENGTH.multiply(MeasurementDimensionRegistry.TIME.power(2)))),
	BAR("bar", Set.of(), Set.of(), 100000.0, MeasurementDimensionRegistry.MASS.divide(MeasurementDimensionRegistry.LENGTH.multiply(MeasurementDimensionRegistry.TIME.power(2)))),
	PSI("psi", Set.of("pounds per square inch"), Set.of(), 6894.76, MeasurementDimensionRegistry.MASS.divide(MeasurementDimensionRegistry.LENGTH.multiply(MeasurementDimensionRegistry.TIME.power(2))));

	private final String symbol;
	private final Set<String> caseInsensitiveAliases;
	private final Set<String> caseSensitiveAliases;
	private final double conversionToBaseUnit;
	private final MeasurementDimension dimension;

	MeasurementUnitRegistry(final String symbol, final Set<String> caseInsensitiveAliases, final Set<String> caseSensitiveAliases, double conversionToBaseUnit, final MeasurementDimension dimension)
	{
		this.symbol = symbol;
		this.caseInsensitiveAliases = caseInsensitiveAliases;
		this.caseSensitiveAliases = caseSensitiveAliases;
		this.conversionToBaseUnit = conversionToBaseUnit;
		this.dimension = dimension;
	}

	@Override
	public String symbol()
	{
		return symbol;
	}

	@Override
	public Set<String> caseSensitiveAliases()
	{
		return caseSensitiveAliases;
	}

	@Override
	public Set<String> caseInsensitiveAliases()
	{
		return caseInsensitiveAliases;
	}

	@Override
	public double conversionToBaseUnitFactor()
	{
		return conversionToBaseUnit;
	}

	@Override
	public MeasurementDimension dimension()
	{
		return dimension;
	}

	@Override
	public MeasurementUnit multiply(MeasurementUnit other)
	{
		// For composite units, delegate to implementation
		return MeasurementUnitFactory.multiply(this, other);
	}

	@Override
	public MeasurementUnit divide(MeasurementUnit other)
	{
		// For composite units, delegate to implementation
		return MeasurementUnitFactory.divide(this, other);
	}

	@Override
	public MeasurementUnit power(int power)
	{
		// For composite units, delegate to implementation
		return MeasurementUnitFactory.power(this, power);
	}
}