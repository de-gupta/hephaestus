package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;

import java.util.Set;

public enum MeasurementUnitRegistry implements MeasurementUnit
{
	DIMENSIONLESS,

	METER,
	KILOMETER,
	CENTIMETER,
	MILLIMETER,
	INCH,
	FOOT,
	MILE,

	KILOGRAM,
	GRAM,
	POUND,
	OUNCE,

	SECOND,
	MINUTE,
	HOUR,
	DAY,

	KELVIN,
	CELSIUS,

	CUBIC_METER,
	LITER,
	MILLILITER,
	GALLON,

	JOULE,
	CALORIE,
	KILOCALORIE,

	METER_PER_SECOND,
	KILOMETER_PER_HOUR,
	MILE_PER_HOUR,

	NEWTON,
	POUND_FORCE,

	WATT,
	KILOWATT,
	HORSEPOWER,

	PASCAL,
	BAR,
	PSI;

	public MeasurementUnit unit()
	{
		return switch (this)
		{
			case DIMENSIONLESS -> BasicMeasurementUnit.createUnit("", Set.of(), Set.of(""), 1.0, MeasurementDimensionRegistry.DIMENSIONLESS);

			case METER -> BasicMeasurementUnit.createUnit("m", Set.of("meter", "metre", "meters", "metres"), Set.of(), 1.0, MeasurementDimensionRegistry.LENGTH);
			case KILOMETER -> BasicMeasurementUnit.createUnit("km", Set.of("kilometer", "kilometre", "kilometers", "kilometres"), Set.of(), 1000.0, MeasurementDimensionRegistry.LENGTH);
			case CENTIMETER -> BasicMeasurementUnit.createUnit("cm", Set.of("centimeter", "centimetre", "centimeters", "centimetres"), Set.of(), 0.01, MeasurementDimensionRegistry.LENGTH);
			case MILLIMETER -> BasicMeasurementUnit.createUnit("mm", Set.of("millimeter", "millimetre", "millimeters", "millimetres"), Set.of(), 0.001, MeasurementDimensionRegistry.LENGTH);
			case INCH -> BasicMeasurementUnit.createUnit("in", Set.of("inch", "inches"), Set.of("\""), 0.0254, MeasurementDimensionRegistry.LENGTH);
			case FOOT -> BasicMeasurementUnit.createUnit("ft", Set.of("foot", "feet"), Set.of("'"), 0.3048, MeasurementDimensionRegistry.LENGTH);
			case MILE -> BasicMeasurementUnit.createUnit("mi", Set.of("mile", "miles"), Set.of(), 1609.344, MeasurementDimensionRegistry.LENGTH);

			case KILOGRAM -> BasicMeasurementUnit.createUnit("kg", Set.of("kilogram", "kilograms"), Set.of(), 1.0, MeasurementDimensionRegistry.MASS);
			case GRAM -> BasicMeasurementUnit.createUnit("g", Set.of("gram", "grams"), Set.of(), 0.001, MeasurementDimensionRegistry.MASS);
			case POUND -> BasicMeasurementUnit.createUnit("lb", Set.of("pound", "pounds"), Set.of("lbs"), 0.45359237, MeasurementDimensionRegistry.MASS);
			case OUNCE -> BasicMeasurementUnit.createUnit("oz", Set.of("ounce", "ounces"), Set.of(), 0.0283495231, MeasurementDimensionRegistry.MASS);

			case SECOND -> BasicMeasurementUnit.createUnit("s", Set.of("second", "seconds", "sec"), Set.of(), 1.0, MeasurementDimensionRegistry.TIME);
			case MINUTE -> BasicMeasurementUnit.createUnit("min", Set.of("minute", "minutes"), Set.of(), 60.0, MeasurementDimensionRegistry.TIME);
			case HOUR -> BasicMeasurementUnit.createUnit("hr", Set.of("hour", "hours"), Set.of("h"), 3600.0, MeasurementDimensionRegistry.TIME);
			case DAY -> BasicMeasurementUnit.createUnit("d", Set.of("day", "days"), Set.of(), 86400.0, MeasurementDimensionRegistry.TIME);

			case KELVIN -> BasicMeasurementUnit.createUnit("K", Set.of("kelvin"), Set.of(), 1.0, MeasurementDimensionRegistry.TEMPERATURE);
			case CELSIUS -> BasicMeasurementUnit.createUnit("°C", Set.of("celsius", "centigrade"), Set.of("C"), 1.0, MeasurementDimensionRegistry.TEMPERATURE);

			case CUBIC_METER -> BasicMeasurementUnit.createUnit("m³", Set.of("cubic meter", "cubic metre", "cubic meters", "cubic metres"), Set.of("m^3"), 1.0, MeasurementDimensionRegistry.VOLUME);
			case LITER -> BasicMeasurementUnit.createUnit("L", Set.of("liter", "litre", "liters", "litres"), Set.of("l"), 0.001, MeasurementDimensionRegistry.VOLUME);
			case MILLILITER -> BasicMeasurementUnit.createUnit("mL", Set.of("milliliter", "millilitre", "milliliters", "millilitres"), Set.of("ml"), 0.000001, MeasurementDimensionRegistry.VOLUME);
			case GALLON -> BasicMeasurementUnit.createUnit("gal", Set.of("gallon", "gallons"), Set.of(), 0.00378541, MeasurementDimensionRegistry.VOLUME);

			case JOULE -> BasicMeasurementUnit.createUnit("J", Set.of("joule", "joules"), Set.of(), 1.0, MeasurementDimensionRegistry.ENERGY);
			case CALORIE -> BasicMeasurementUnit.createUnit("cal", Set.of("calorie", "calories"), Set.of(), 4.184, MeasurementDimensionRegistry.ENERGY);
			case KILOCALORIE -> BasicMeasurementUnit.createUnit("kcal", Set.of("kilocalorie", "kilocalories"), Set.of("Cal"), 4184.0, MeasurementDimensionRegistry.ENERGY);

			case METER_PER_SECOND -> BasicMeasurementUnit.createUnit("m/s", Set.of("meter per second", "metres per second", "mps"), Set.of(), 1.0, MeasurementDimensionRegistry.LENGTH.divide(MeasurementDimensionRegistry.TIME));
			case KILOMETER_PER_HOUR -> BasicMeasurementUnit.createUnit("km/h", Set.of("kilometer per hour", "kilometres per hour", "kph"), Set.of("kmh"), 0.277778, MeasurementDimensionRegistry.LENGTH.divide(MeasurementDimensionRegistry.TIME));
			case MILE_PER_HOUR -> BasicMeasurementUnit.createUnit("mph", Set.of("mile per hour", "miles per hour"), Set.of(), 0.44704, MeasurementDimensionRegistry.LENGTH.divide(MeasurementDimensionRegistry.TIME));

			case NEWTON -> BasicMeasurementUnit.createUnit("N", Set.of("newton", "newtons"), Set.of(), 1.0, MeasurementDimensionRegistry.MASS.multiply(MeasurementDimensionRegistry.LENGTH).divide(MeasurementDimensionRegistry.TIME.power(2)));
			case POUND_FORCE -> BasicMeasurementUnit.createUnit("lbf", Set.of("pound force", "pounds force"), Set.of(), 4.44822, MeasurementDimensionRegistry.MASS.multiply(MeasurementDimensionRegistry.LENGTH).divide(MeasurementDimensionRegistry.TIME.power(2)));

			case WATT -> BasicMeasurementUnit.createUnit("W", Set.of("watt", "watts"), Set.of(), 1.0, MeasurementDimensionRegistry.ENERGY.divide(MeasurementDimensionRegistry.TIME));
			case KILOWATT -> BasicMeasurementUnit.createUnit("kW", Set.of("kilowatt", "kilowatts"), Set.of(), 1000.0, MeasurementDimensionRegistry.ENERGY.divide(MeasurementDimensionRegistry.TIME));
			case HORSEPOWER -> BasicMeasurementUnit.createUnit("hp", Set.of("horsepower"), Set.of(), 745.7, MeasurementDimensionRegistry.ENERGY.divide(MeasurementDimensionRegistry.TIME));

			case PASCAL -> BasicMeasurementUnit.createUnit("Pa", Set.of("pascal", "pascals"), Set.of(), 1.0, MeasurementDimensionRegistry.MASS.divide(MeasurementDimensionRegistry.LENGTH.multiply(MeasurementDimensionRegistry.TIME.power(2))));
			case BAR -> BasicMeasurementUnit.createUnit("bar", Set.of(), Set.of(), 100000.0, MeasurementDimensionRegistry.MASS.divide(MeasurementDimensionRegistry.LENGTH.multiply(MeasurementDimensionRegistry.TIME.power(2))));
			case PSI -> BasicMeasurementUnit.createUnit("psi", Set.of("pounds per square inch"), Set.of(), 6894.76, MeasurementDimensionRegistry.MASS.divide(MeasurementDimensionRegistry.LENGTH.multiply(MeasurementDimensionRegistry.TIME.power(2))));
		};
	}

	@Override
	public String symbol()
	{
		return this.unit().symbol();
	}

	@Override
	public Set<String> caseSensitiveAliases()
	{
		return this.unit().caseSensitiveAliases();
	}

	@Override
	public Set<String> caseInsensitiveAliases()
	{
		return this.unit().caseInsensitiveAliases();
	}

	@Override
	public double conversionToBaseUnitFactor()
	{
		return this.unit().conversionToBaseUnitFactor();
	}

	@Override
	public MeasurementDimension dimension()
	{
		return this.unit().dimension();
	}

	@Override
	public MeasurementUnit multiply(MeasurementUnit other)
	{
		return this.unit().multiply(other);
	}

	@Override
	public MeasurementUnit divide(MeasurementUnit other)
	{
		return this.unit().divide(other);
	}

	@Override
	public MeasurementUnit power(int power)
	{
		return this.unit().power(power);
	}
}