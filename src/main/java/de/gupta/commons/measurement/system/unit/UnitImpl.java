package de.gupta.commons.measurement.system.unit;

import de.gupta.aletheia.functional.Unfolding;
import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;
import de.gupta.commons.utility.map.MapCleaner;
import de.gupta.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.FreeAbelianGroup;
import de.gupta.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.FreeAbelianGroupFactory;
import de.gupta.commons.utility.string.StringFormatUtility;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.function.Function;
import java.util.stream.Collectors;

record UnitImpl(EnumMap<UnitConstituent, Integer> components) implements Unit
{
	private static final Unit IDENTITY =
			UnitImpl.of(new EnumMap<>(UnitConstituent.class));

	private static final FreeAbelianGroup<UnitConstituent> freeAbelianGroup =
			FreeAbelianGroupFactory.create(UnitConstituent.class);

	static Unit of(final EnumMap<UnitConstituent, Integer> components)
	{
		return new UnitImpl(MapCleaner.removeKeyIfValueEquals(components, 0));
	}

	static Unit identity()
	{
		return IDENTITY;
	}

	@Override
	public Unit multiply(final Unit other)
	{
		return other instanceof UnitImpl(EnumMap<UnitConstituent, Integer> those)
				? UnitImpl.of(freeAbelianGroup.add(components, those))
				: other.multiply(this);
	}

	@Override
	public Unit divide(final Unit other)
	{
		return other instanceof UnitImpl(EnumMap<UnitConstituent, Integer> those)
				? UnitImpl.of(freeAbelianGroup.subtract(components, those))
				: other.divide(this);
	}

	@Override
	public Unit power(final int power)
	{
		return power == 0 ? identity() : UnitImpl.of(freeAbelianGroup.scale(components, power));
	}

	@Override
	public MeasurementDimension dimension()
	{
		return components.entrySet().stream()
						 .map(entry -> entry.getKey().dimension().power(entry.getValue()))
						 .reduce(MeasurementDimensionRegistry.DIMENSIONLESS, MeasurementDimension::multiply);
	}

	@Override
	public double conversionToBaseUnitFactor()
	{
		return components.entrySet().stream()
						 .mapToDouble(entry -> Math.pow(entry.getKey().factor(), entry.getValue()))
						 .reduce(1.0, (a, b) -> a * b);
	}

	@Override
	public String symbol()
	{
		return Unfolding.beckon(components)
						.cleave(AbstractMap::isEmpty, _ -> "", symbolCalculator())
						.summon();
	}

	private Function<EnumMap<UnitConstituent, Integer>, String> symbolCalculator()
	{
		return components -> components.entrySet().stream()
									   .map(entry -> formatUnit(entry.getKey(), entry.getValue()))
									   .collect(Collectors.joining("·"));
	}

	@Override
	public boolean equals(final Object o)
	{
		return this == o || o instanceof UnitImpl(
				EnumMap<UnitConstituent, Integer> components1
		) && components.equals(components1);
	}

	private String formatUnit(final UnitConstituent constituent, final int exponent)
	{
		String symbol = constituent.symbol();
		return exponent == 1 ? symbol : symbol + StringFormatUtility.toSuperscript(exponent);
	}

	@Override
	public String toString()
	{
		return "Unit{components=" + components + "}";
	}
}