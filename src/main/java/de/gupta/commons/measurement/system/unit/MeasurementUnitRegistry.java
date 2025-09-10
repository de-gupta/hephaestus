package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;

public enum MeasurementUnitRegistry implements MeasurementUnit
{
	DIMENSIONLESS("", 1.0, MeasurementDimensionRegistry.DIMENSIONLESS),

	// Length units
	METER("m", 1.0, MeasurementDimensionRegistry.LENGTH),

	// Mass units
	KILOGRAM("kg", 1.0, MeasurementDimensionRegistry.MASS),
	GRAM("g", 0.001, MeasurementDimensionRegistry.MASS),
	POUND("lb", 0.45359237, MeasurementDimensionRegistry.MASS),

	// Time units
	SECOND("s", 1.0, MeasurementDimensionRegistry.TIME),
	MINUTE("min", 60.0, MeasurementDimensionRegistry.TIME),
	HOUR("hr", 3600.0, MeasurementDimensionRegistry.TIME),

	// Temperature units
	KELVIN("K", 1.0, MeasurementDimensionRegistry.TEMPERATURE),

	// Volume units
	CUBIC_METER("m³", 1.0, MeasurementDimensionRegistry.VOLUME),
	LITER("L", 0.001, MeasurementDimensionRegistry.VOLUME),

	// Energy units
	JOULE("J", 1.0, MeasurementDimensionRegistry.ENERGY),
	CALORIE("cal", 4.184, MeasurementDimensionRegistry.ENERGY);

	private final String symbol;
	private final double conversionToBaseUnit;
	private final MeasurementDimension dimension;

	MeasurementUnitRegistry(String symbol, double conversionToBaseUnit, MeasurementDimension dimension)
	{
		this.symbol = symbol;
		this.conversionToBaseUnit = conversionToBaseUnit;
		this.dimension = dimension;
	}

	@Override
	public String symbol()
	{
		return symbol;
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