package de.gupta.commons.measurement.system.dimension;

import ai.eylo.commons.utility.math.algebra.algebraicGroup.Group;
import ai.eylo.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.HomomorphicMapExtension;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class DimensionHomomorphicMapExtension
{
	public static <T extends Group<T>> Set<T> homomorphicExtension(final Map<T, MeasurementDimension> partialMapping,
																   final MeasurementDimension element)
	{
		Map<T, EnumMap<MeasurementDimensionConstituent, Integer>> convertedPartialMapping =
				partialMapping.entrySet().stream()
							  .collect(Collectors.toMap(Map.Entry::getKey,
									  entry -> exponents(entry.getValue())));

		return switch (element)
		{
			case MeasurementDimensionImpl that -> HomomorphicMapExtension.preimagesFromPartialMapping(
					convertedPartialMapping, that.exponents());
		};
	}

	private static EnumMap<MeasurementDimensionConstituent, Integer> exponents(final MeasurementDimension dimension)
	{
		return switch (dimension)
		{
			case MeasurementDimensionImpl that -> that.exponents();
		};
	}

	private DimensionHomomorphicMapExtension()
	{
	}
}