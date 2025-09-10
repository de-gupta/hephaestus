package de.gupta.commons.measurement.system.unit.implementation;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;
import de.gupta.commons.measurement.system.unit.Unit;
import de.gupta.commons.utility.map.MapCleaner;
import de.gupta.commons.utility.math.FunctionUtility;

import java.util.*;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CompositeUnit extends AbstractUnit implements Unit
{
	private static final String EMPTY_UNIT_SYMBOL = "";
	private static final String MULTIPLICATION_SYMBOL_SEPARATOR = "·";
	private static final String DIVISION_SYMBOL_SEPARATOR = "/";

	private final Map<BasicUnit, Integer> components;

	static CompositeUnit from(Unit unit)
	{
		return switch (unit)
		{
			case BasicUnit basic -> new CompositeUnit(Map.of(basic, 1));
			case CompositeUnit comp -> comp;
			default -> throw new IllegalArgumentException("Unknown unit type: " + unit);
		};
	}

	static CompositeUnit combine(CompositeUnit left, CompositeUnit right)
	{
		return withComponents(Stream.concat(left.components.entrySet().stream(), right.components.entrySet().stream())
									.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum)));
	}

	static CompositeUnit withComponents(Map<BasicUnit, Integer> components)
	{
		return new CompositeUnit(components);
	}

	@Override
	public DoubleUnaryOperator convertToBaseUnitOfTheDimension()
	{
		return FunctionUtility.multiplyWithExponents(components.entrySet().stream().map(entry -> Map.entry(
				entry.getKey().convertToBaseUnitOfTheDimension(), entry.getValue())).collect(
				Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
	}

	@Override
	public MeasurementDimension dimension()
	{
		return components.entrySet().stream().map(entry -> entry.getKey().dimension().power(entry.getValue()))
						 .reduce(MeasurementDimensionRegistry.DIMENSIONLESS.dimension(),
								 MeasurementDimension::multiply);
	}

	@Override
	public Set<String> caseInsensitiveAliases()
	{
		return computeAliases(BasicUnit::caseInsensitiveAliases);
	}

	@Override
	public Set<String> caseSensitiveAliases()
	{
		return computeAliases(BasicUnit::caseSensitiveAliases);
	}

	@Override
	public String symbol()
	{
		if (components.isEmpty() || components.values().stream().allMatch(exp -> exp == 0))
		{
			return EMPTY_UNIT_SYMBOL;
		}

		final Function<Integer, Predicate<Map.Entry<BasicUnit, Integer>>> filterBySign =
				sign -> entry -> Integer.signum(entry.getValue()) == sign;

		final Function<Integer, String> formatBySign =
				sign -> components.entrySet().stream().filter(filterBySign.apply(sign))
								  .map(entry -> formatUnit(entry.getKey(), Math.abs(entry.getValue())))
								  .collect(Collectors.joining(MULTIPLICATION_SYMBOL_SEPARATOR));

		final String numerator = formatBySign.apply(1);
		final String denominator = formatBySign.apply(-1);

		if (numerator.isEmpty()) return denominator.isEmpty() ? EMPTY_UNIT_SYMBOL : formatDenominator(denominator);
		return denominator.isEmpty() ? numerator : numerator + DIVISION_SYMBOL_SEPARATOR + denominator;
	}

	private String formatUnit(BasicUnit unit, int exponent)
	{
		return exponent == 1 ? unit.symbol() : exponent == 0 ? EMPTY_UNIT_SYMBOL :
				StringFormattingUtility.formatWithSuperscript(unit.symbol(), exponent, Integer.toString(exponent));
	}

	private String formatDenominator(String denominator)
	{
		SequencedCollection<Map.Entry<BasicUnit, Integer>> negativeEntries =
				components.entrySet().stream().filter(entry -> entry.getValue() < 0).toList();

		return negativeEntries.size() == 1 ?
				formatUnit(negativeEntries.getFirst().getKey(), negativeEntries.getFirst().getValue()) :
				"1 " + DIVISION_SYMBOL_SEPARATOR + denominator;
	}

	private Set<String> computeAliases(Function<BasicUnit, Set<String>> aliasExtractor)
	{
		List<Set<String>> numeratorAliases = components.entrySet().stream().filter(entry -> entry.getValue() > 0)
													   .map(entry -> aliasExtractor.apply(entry.getKey())).toList();

		List<Set<String>> denominatorAliases = components.entrySet().stream().filter(entry -> entry.getValue() < 0)
														 .map(entry -> aliasExtractor.apply(entry.getKey())).toList();

		Set<String> numeratorCombinations = cartesianProduct(numeratorAliases, MULTIPLICATION_SYMBOL_SEPARATOR);
		Set<String> denominatorCombinations = cartesianProduct(denominatorAliases, MULTIPLICATION_SYMBOL_SEPARATOR);

		if (denominatorCombinations.isEmpty()) return numeratorCombinations;
		if (numeratorCombinations.isEmpty())
			return denominatorCombinations.stream().map(alias -> "1" + DIVISION_SYMBOL_SEPARATOR + alias)
										  .collect(Collectors.toSet());

		return numeratorCombinations.stream().flatMap(
											num -> denominatorCombinations.stream().map(den -> num + DIVISION_SYMBOL_SEPARATOR + den))
									.collect(Collectors.toSet());
	}

	private Set<String> cartesianProduct(Collection<Set<String>> aliasGroups, String separator)
	{
		return aliasGroups.stream().reduce((set1, set2) -> set1.stream().flatMap(
								  alias1 -> set2.stream().map(alias2 -> alias1 + separator + alias2)).collect(Collectors.toSet()))
						  .orElse(Set.of());
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(components);
	}

	@Override
	public boolean equals(final Object o)
	{
		return this == o || o instanceof final CompositeUnit that && MapCleaner.removeKeyIfValueEquals(components, 0)
																			   .equals(MapCleaner.removeKeyIfValueEquals(
																					   that.components, 0));
	}

	@Override
	public String toString()
	{
		return "CompositeUnit{" + "components=" + components + '}';
	}

	private CompositeUnit(Map<BasicUnit, Integer> components)
	{
		this.components = Collections.unmodifiableMap(MapCleaner.removeKeyIfValueEquals(components, 0));
	}

	@Override
	public Unit inverse()
	{
		return this.power(-1);
	}

	@Override
	public Unit power(int exponent)
	{
		return withComponents(components.entrySet().stream().collect(
				Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() * exponent)));
	}
}