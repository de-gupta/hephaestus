package de.gupta.commons.measurement.system.quantity;

import de.gupta.commons.measurement.system.unit.MeasurementUnit;
import de.gupta.commons.measurement.system.unit.MeasurementUnitRegistry;
import de.gupta.commons.utility.math.MathUtility;

public record QuantityRepresentation(Number value, String unit)
{
	public static QuantityRepresentation of(Number value, String unit)
	{
		return new QuantityRepresentation(value, unit);
	}

	public static QuantityRepresentation of(Number value, MeasurementUnit unit)
	{
		return of(value, unit.symbol());
	}

	public static QuantityRepresentation of(Number value, MeasurementUnitRegistry unit)
	{
		return of(value, unit.symbol());
	}

	public QuantityRepresentation roundToGivenDecimalPlaces(int roundingDecimalPlaces)
	{
		return QuantityRepresentation.of(
				MathUtility.round(value.doubleValue(), roundingDecimalPlaces),
				unit);
	}

	public QuantityRepresentation scale(Number factor)
	{
		return QuantityRepresentation.of(value.doubleValue() * factor.doubleValue(), unit);
	}
}