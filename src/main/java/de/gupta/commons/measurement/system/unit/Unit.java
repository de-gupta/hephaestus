package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.utility.math.algebra.algebraicGroup.Group;

import java.util.Set;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: make this sealed later
public interface Unit extends Group<Unit>
{
	DoubleUnaryOperator convertToBaseUnitOfTheDimension();

	MeasurementDimension dimension();

	default Set<String> representations()
	{
		return Stream.of(caseInsensitiveAliases(), caseSensitiveAliases(), Set.of(symbol()))
					 .flatMap(Set::stream)
					 .collect(Collectors.toSet());
	}

	Set<String> caseInsensitiveAliases();

	Set<String> caseSensitiveAliases();

	String symbol();

	Unit multiply(Unit other);

	Unit divide(Unit other);

	Unit power(int exponent);

	@Override
	default Unit inverse(final Unit element)
	{
		return inverse();
	}

	Unit inverse();
}