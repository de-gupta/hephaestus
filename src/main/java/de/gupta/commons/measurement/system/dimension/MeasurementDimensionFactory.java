package de.gupta.commons.measurement.system.dimension;

import de.gupta.commons.utility.map.enumMap.EnumMapConstruction;

import java.util.Map;

final class MeasurementDimensionFactory
{
	static MeasurementDimension of(final Map<MeasurementDimensionConstituent, Integer> exponents)
	{
		return MeasurementDimensionImpl.of(EnumMapConstruction.from(exponents, MeasurementDimensionConstituent.class));
	}

	static MeasurementDimension dimensionless()
	{
		return MeasurementDimensionImpl.IDENTITY;
	}

	private MeasurementDimensionFactory()
	{
	}
}