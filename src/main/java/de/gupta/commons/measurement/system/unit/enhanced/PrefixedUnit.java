package de.gupta.commons.measurement.system.unit.enhanced;

import de.gupta.commons.measurement.system.unit.Unit;
import de.gupta.commons.utility.math.prefix.PrefixWithFactor;

import java.util.Optional;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public interface PrefixedUnit<T extends Unit>
{
	DoubleUnaryOperator effectiveConversionFactor();

	Set<String> caseInsensitiveAliases();

	Set<String> caseSensitiveAliases();

	String symbol();

	PrefixedUnit<Unit> power(int power);

	T unit();

	Optional<PrefixWithFactor> prefix();

	double residualFactor();

	PrefixedUnit<Unit> multiply(final PrefixedUnit<Unit> other);

	PrefixedUnit<Unit> normalizeAfterMultiplyingWithFactor(final double factor);

	PrefixedUnit<Unit> humanReadableAfterMultiplyingWithFactor(final double factor);
}