package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

import java.util.Set;

public sealed interface MeasurementUnit permits MeasurementUnitImpl, MeasurementUnitRegistry
{
	MeasurementUnit multiply(MeasurementUnit other);

	MeasurementUnit divide(MeasurementUnit other);

	MeasurementUnit power(int power);

	MeasurementDimension dimension();

	double conversionToBaseUnitFactor();

	String symbol();

	default Set<String> representations()
	{
		return Set.of(symbol());
	}
}