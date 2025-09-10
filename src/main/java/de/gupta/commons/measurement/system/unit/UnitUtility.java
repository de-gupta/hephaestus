package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;
import de.gupta.commons.utility.collection.SetUtility;
import de.gupta.commons.utility.math.prefix.Prefix;

import java.util.*;
import java.util.stream.Collectors;

public final class UnitUtility
{
	public static Set<String> unitSymbolsForDimensions(MeasurementDimensionRegistry... dimensions)
	{
		return Set.of(dimensions).stream()
				  .flatMap(dimension -> unitSymbolsForDimension(dimension).stream())
				  .collect(Collectors.toSet());
	}

	public static Set<String> unitSymbolsForDimension(MeasurementDimensionRegistry dimension)
	{
		return UnitDimensionUtility.unitsForDimension(dimension).stream()
								   .map(Unit::symbol)
								   .collect(Collectors.toSet());
	}

	// TODO: cache this method
	public static SortedSet<String> unitRepresentationsWithPrefixesForDimensions(
			MeasurementDimensionRegistry... dimensions)
	{
		Set<String> unitRepresentations = Set.of(dimensions).stream()
											 .flatMap(dimension -> unitRepresentationsForDimension(dimension).stream())
											 .collect(Collectors.toSet());
		Set<String> prefixRepresentation = Arrays.stream(Prefix.values())
												 .map(Prefix::representations)
												 .flatMap(Set::stream)
												 .collect(Collectors.toSet());

		// TODO
//		final var prefixedUnitRepresentations =
//				SetUtility.joinElementsOfSets(List.of(prefixRepresentation, unitRepresentations), "", String::concat);

//		return new TreeSet<>(prefixedUnitRepresentations);
		return null;
	}

	private static Set<String> unitRepresentationsForDimension(final MeasurementDimensionRegistry dimension)
	{
		return UnitDimensionUtility.unitsForDimension(dimension)
								   .stream()
								   .flatMap(unit -> unit.representations().stream())
								   .collect(Collectors.toSet());
	}

	public static boolean doUnitsHaveSameDimensions(Unit unit1, Unit unit2)
	{
		return unit1.dimension().equals(unit2.dimension());
	}
}