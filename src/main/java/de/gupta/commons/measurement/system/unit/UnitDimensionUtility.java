package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.DimensionHomomorphicMapExtension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;
import de.gupta.commons.measurement.system.unit.implementation.BasicUnitRegistry;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class UnitDimensionUtility
{
	public static Set<Unit> unitsForDimension(MeasurementDimensionRegistry dimension)
	{
		return unitsForDimension(dimension.dimension());
	}

	public static Set<Unit> unitsForDimension(MeasurementDimension dimension)
	{
		return null;
//		return DimensionHomomorphicMapExtension.homomorphicExtension(partialMapping(), dimension);
	}

	private static Map<Unit, MeasurementDimension> partialMapping()
	{
		return Arrays.stream(BasicUnitRegistry.values())
					 .collect(Collectors.toMap(BasicUnitRegistry::basicUnit,
							 basicUnitRegistryValue -> basicUnitRegistryValue.basicUnit().dimension()));
	}

	private UnitDimensionUtility()
	{
	}
}