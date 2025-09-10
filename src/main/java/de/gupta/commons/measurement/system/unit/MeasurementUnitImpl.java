package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;
import de.gupta.commons.measurement.system.dimension.MeasurementDimensionRegistry;
import de.gupta.commons.utility.map.MapCleaner;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

final class MeasurementUnitImpl implements MeasurementUnit
{
	private final EnumMap<MeasurementUnitRegistry, Integer> components;

	static MeasurementUnit of(final EnumMap<MeasurementUnitRegistry, Integer> components)
	{
		EnumMap<MeasurementUnitRegistry, Integer> cleanedComponents = MapCleaner.removeKeyIfValueEquals(components, 0);

		if (cleanedComponents.size() == 1)
		{
			Map.Entry<MeasurementUnitRegistry, Integer> entry = cleanedComponents.entrySet().iterator().next();
			if (entry.getValue() == 1)
			{
				return entry.getKey();
			}
		}

		// If empty or all zero, return dimensionless
		if (cleanedComponents.isEmpty())
		{
			return MeasurementUnitRegistry.DIMENSIONLESS;
		}

		return new MeasurementUnitImpl(cleanedComponents);
	}

	@Override
	public MeasurementUnit multiply(final MeasurementUnit other)
	{
		return MeasurementUnitFactory.multiply(this, other);
	}

	@Override
	public MeasurementUnit divide(final MeasurementUnit other)
	{
		return MeasurementUnitFactory.divide(this, other);
	}

	@Override
	public MeasurementUnit power(final int power)
	{
		return MeasurementUnitFactory.power(this, power);
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
			.mapToDouble(entry -> Math.pow(entry.getKey().conversionToBaseUnitFactor(), entry.getValue()))
			.reduce(1.0, (a, b) -> a * b);
	}

	@Override
	public String symbol()
	{
		if (components.isEmpty())
		{
			return "";
		}

		String numerator = components.entrySet().stream()
				.filter(entry -> entry.getValue() > 0)
				.map(entry -> formatUnit(entry.getKey(), entry.getValue()))
				.collect(Collectors.joining("·"));

		String denominator = components.entrySet().stream()
				.filter(entry -> entry.getValue() < 0)
				.map(entry -> formatUnit(entry.getKey(), Math.abs(entry.getValue())))
				.collect(Collectors.joining("·"));

		if (numerator.isEmpty())
		{
			return denominator.isEmpty() ? "" : "1/" + denominator;
		}

		return denominator.isEmpty() ? numerator : numerator + "/" + denominator;
	}

	private String formatUnit(MeasurementUnitRegistry unit, int exponent)
	{
		return exponent == 1 ? unit.symbol() : unit.symbol() + "^" + exponent;
	}

	EnumMap<MeasurementUnitRegistry, Integer> components()
	{
		return components;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(components);
	}

	@Override
	public boolean equals(final Object o)
	{
		return this == o || o instanceof MeasurementUnitImpl that && components.equals(that.components);
	}

	@Override
	public String toString()
	{
		return "MeasurementUnit{components=" + components + "}";
	}

	private MeasurementUnitImpl(final EnumMap<MeasurementUnitRegistry, Integer> components)
	{
		this.components = components;
	}
}