package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public sealed interface MeasurementUnit permits MeasurementUnitImpl, MeasurementUnitRegistry
{
	MeasurementUnit multiply(MeasurementUnit other);

	MeasurementUnit divide(MeasurementUnit other);

	MeasurementUnit power(int power);

	MeasurementDimension dimension();

	double conversionToBaseUnitFactor();

	String symbol();

	Set<String> caseSensitiveAliases();

	Set<String> caseInsensitiveAliases();

	default Set<String> representations()
	{
		return Stream.of(caseInsensitiveAliases(), caseSensitiveAliases(), Set.of(symbol()))
					 .flatMap(Set::stream)
					 .collect(Collectors.toSet());
	}
}