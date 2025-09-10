package de.gupta.commons.measurement.system.dimension;

import de.gupta.commons.utility.map.MapCleaner;
import de.gupta.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.FreeAbelianGroup;
import de.gupta.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.FreeAbelianGroupFactory;

import java.util.EnumMap;
import java.util.Objects;

final class MeasurementDimensionImpl implements MeasurementDimension
{
	static final MeasurementDimension IDENTITY =
			MeasurementDimensionImpl.of(new EnumMap<>(MeasurementDimensionConstituent.class));
	private static final FreeAbelianGroup<MeasurementDimensionConstituent> freeAbelianGroup =
			FreeAbelianGroupFactory.create(MeasurementDimensionConstituent.class);
	private final EnumMap<MeasurementDimensionConstituent, Integer> exponents;

	@Override
	public MeasurementDimension multiply(final MeasurementDimension other)
	{
		return other instanceof MeasurementDimensionImpl that ?
				MeasurementDimensionImpl.of(freeAbelianGroup.add(this.exponents, that.exponents)) :
				other.multiply(this);
	}

	static MeasurementDimension of(final EnumMap<MeasurementDimensionConstituent, Integer> exponents)
	{
		return new MeasurementDimensionImpl(MapCleaner.removeKeyIfValueEquals(exponents, 0));
	}

	@Override
	public MeasurementDimension divide(final MeasurementDimension other)
	{
		return other instanceof MeasurementDimensionImpl that ?
				MeasurementDimensionImpl.of(freeAbelianGroup.subtract(this.exponents, that.exponents)) :
				other.divide(this);
	}

	@Override
	public MeasurementDimension power(final int power)
	{
		return MeasurementDimensionImpl.of(freeAbelianGroup.scale(exponents, power));
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(exponents);
	}

	@Override
	public boolean equals(final Object o)
	{
		return this == o || o instanceof MeasurementDimensionImpl that && exponents.equals(that.exponents);
	}

	@Override
	public String toString()
	{
		return "MeasurementDimension{" +
				"exponents=" + exponents +
				'}';
	}

	EnumMap<MeasurementDimensionConstituent, Integer> exponents()
	{
		return exponents;
	}

	private MeasurementDimensionImpl(final EnumMap<MeasurementDimensionConstituent, Integer> exponents)
	{
		this.exponents = exponents;
	}
}