package de.gupta.commons.measurement.system.quantity;

import de.gupta.commons.measurement.system.unit.Unit;
import de.gupta.commons.measurement.system.unit.UnitRegistry;
import de.gupta.commons.utility.math.MathUtility;

public record QuantityRepresentation(Number value, String unit)
{
	public static QuantityRepresentation of(Number value, String unit)
	{
		return new QuantityRepresentation(value, unit);
	}

	public static QuantityRepresentation of(Number value, Unit unit)
	{
		return of(value, unit.symbol());
	}

	public static QuantityRepresentation of(Number value, UnitRegistry unit)
	{
		return of(value, unit.unit());
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