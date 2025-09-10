package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

public sealed interface Unit permits UnitImpl, UnitRegistry
{
	Unit multiply(Unit other);

	Unit divide(Unit other);

	Unit power(int power);

	MeasurementDimension dimension();

	double conversionToBaseUnitFactor();

	String symbol();
}