package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.utility.map.enumMap.EnumMapConstruction;

import java.util.Map;

public final class MeasurementUnitFactory
{
	static MeasurementUnit dimensionless()
	{
		return MeasurementUnitImpl.identity();
	}

	static MeasurementUnit of(final Map<MeasurementUnitConstituent, Integer> exponents)
	{
		return MeasurementUnitImpl.of(EnumMapConstruction.from(exponents, MeasurementUnitConstituent.class));
	}

}