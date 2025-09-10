package de.gupta.commons.measurement.system.unit.implementation;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.unit.Unit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public final class BasicUnit extends AbstractUnit implements Unit
{
	private final String symbol;
	private final Set<String> caseInsensitiveAliases;
	private final Set<String> caseSensitiveAliases;
	private final DoubleUnaryOperator convertToBaseUnitOfTheDimension;
	private final MeasurementDimension dimension;

	@Override
	public DoubleUnaryOperator convertToBaseUnitOfTheDimension()
	{
		return convertToBaseUnitOfTheDimension;
	}

	@Override
	public MeasurementDimension dimension()
	{
		return dimension;
	}

	@Override
	public String symbol()
	{
		return symbol;
	}

	@Override
	public Set<String> caseInsensitiveAliases()
	{
		return caseInsensitiveAliases;
	}

	@Override
	public Set<String> caseSensitiveAliases()
	{
		return caseSensitiveAliases;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(symbol, convertToBaseUnitOfTheDimension, dimension);
	}

	@Override
	public boolean equals(Object o)
	{
		return this == o || o instanceof BasicUnit that &&
				dimension.equals(that.dimension) &&
				symbol.equals(that.symbol) &&
				Double.compare(that.convertToBaseUnitOfTheDimension.applyAsDouble(1.0),
						convertToBaseUnitOfTheDimension.applyAsDouble(1.0)) == 0;
	}

	@Override
	public String toString()
	{
		return symbol;
	}

	BasicUnit(final String symbol, final Set<String> caseInsensitiveAliases,
			  final DoubleUnaryOperator convertToBaseUnitOfTheDimension,
			  final MeasurementDimension dimension)
	{
		this(symbol, caseInsensitiveAliases, Collections.emptySet(), convertToBaseUnitOfTheDimension, dimension);
	}

	BasicUnit(final String symbol, Set<String> caseInsensitiveAliases, final Set<String> caseSensitiveAliases,
			  final DoubleUnaryOperator convertToBaseUnitOfTheDimension,
			  final MeasurementDimension dimension)
	{
		this.symbol = symbol;
		this.caseInsensitiveAliases = normalizeAliasesToLowerCase(caseInsensitiveAliases, symbol);
		this.caseSensitiveAliases = caseSensitiveAliases;
		this.convertToBaseUnitOfTheDimension = convertToBaseUnitOfTheDimension;
		this.dimension = dimension;
	}

	private static Set<String> normalizeAliasesToLowerCase(final Set<String> aliases, final String symbol)
	{
		Set<String> normalizedAliases = new HashSet<>();
		aliases.forEach(alias -> normalizedAliases.add(alias.toLowerCase()));
		normalizedAliases.add(symbol);
		normalizedAliases.add(symbol.toLowerCase());
		return Collections.unmodifiableSet(normalizedAliases);
	}
}