package de.gupta.commons.measurement.system.dimension;

import de.gupta.commons.utility.map.MapCleaner;
import de.gupta.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.FreeAbelianGroup;
import de.gupta.commons.utility.math.algebra.algebraicGroup.freeAbelianGroup.FreeAbelianGroupFactory;

import java.util.EnumMap;

record MeasurementDimensionImpl(EnumMap<MeasurementDimensionConstituent, Integer> exponents)
		implements MeasurementDimension
{
	static final MeasurementDimension IDENTITY =
			MeasurementDimensionImpl.of(new EnumMap<>(MeasurementDimensionConstituent.class));
	private static final FreeAbelianGroup<MeasurementDimensionConstituent> freeAbelianGroup =
			FreeAbelianGroupFactory.create(MeasurementDimensionConstituent.class);

	@Override
	public MeasurementDimension multiply(final MeasurementDimension other)
	{
		return other instanceof MeasurementDimensionImpl(EnumMap<MeasurementDimensionConstituent, Integer> exponents1) ?
				MeasurementDimensionImpl.of(freeAbelianGroup.add(this.exponents, exponents1)) :
				other.multiply(this);
	}

	static MeasurementDimension of(final EnumMap<MeasurementDimensionConstituent, Integer> exponents)
	{
		return new MeasurementDimensionImpl(MapCleaner.removeKeyIfValueEquals(exponents, 0));
	}

	@Override
	public MeasurementDimension divide(final MeasurementDimension other)
	{
		return other instanceof MeasurementDimensionImpl(EnumMap<MeasurementDimensionConstituent, Integer> exponents1) ?
				MeasurementDimensionImpl.of(freeAbelianGroup.subtract(this.exponents, exponents1)) :
				other.divide(this);
	}

	@Override
	public MeasurementDimension power(final int power)
	{
		return MeasurementDimensionImpl.of(freeAbelianGroup.scale(exponents, power));
	}

	@Override
	public boolean equals(final Object o)
	{
		return this == o || o instanceof MeasurementDimensionImpl(
				EnumMap<MeasurementDimensionConstituent, Integer> exponents1
		) && exponents.equals(exponents1);
	}

	@Override
	public String toString()
	{
		return "MeasurementDimension{" +
				"exponents=" + exponents +
				'}';
	}
}