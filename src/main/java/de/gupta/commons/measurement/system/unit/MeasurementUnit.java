package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

public sealed interface MeasurementUnit permits MeasurementUnitImpl, MeasurementUnitRegistry
{
	MeasurementUnit multiply(MeasurementUnit other);

	MeasurementUnit divide(MeasurementUnit other);

	MeasurementUnit power(int power);

	MeasurementDimension dimension();

	double conversionToBaseUnitFactor();

	String symbol();
}