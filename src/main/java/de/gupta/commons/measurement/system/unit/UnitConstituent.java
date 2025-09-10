package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;

enum UnitConstituent
{
	KILOGRAM,
	METER,
	SECOND,
	KELVIN;

	public String symbol()
	{
		return switch (this)
		{
			case KILOGRAM -> "kg";
			case METER -> "m";
			case SECOND -> "s";
			case KELVIN -> "K";
		};
	}

	public MeasurementDimension dimension()
	{

		return switch (this)
		{
			case KILOGRAM -> MeasurementDimensionRegistry.MASS;
			case METER -> MeasurementDimensionRegistry.LENGTH;
			case SECOND -> MeasurementDimensionRegistry.TIME;
			case KELVIN -> MeasurementDimensionRegistry.TEMPERATURE;
		};
	}

	public double factor()
	{
		return 1.0;
	}
}