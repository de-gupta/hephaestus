package de.gupta.commons.measurement.system.dimension;

import java.util.Map;

public enum MeasurementDimensionRegistry implements MeasurementDimension
{
	DIMENSIONLESS,
	MASS,
	LENGTH,
	TIME,
	TEMPERATURE,
	VOLUME,
	ENERGY,

	MASS_DENSITY,
	ENERGY_DENSITY;

	@Deprecated
	public final MeasurementDimension dimension()
	{
		return switch (this)
		{
			case DIMENSIONLESS -> MeasurementDimensionFactory.dimensionless();

			case MASS -> MeasurementDimensionFactory.of(Map.of(MeasurementDimensionConstituent.MASS, 1));
			case LENGTH -> MeasurementDimensionFactory.of(Map.of(MeasurementDimensionConstituent.LENGTH, 1));
			case TIME -> MeasurementDimensionFactory.of(Map.of(MeasurementDimensionConstituent.TIME, 1));
			case TEMPERATURE -> MeasurementDimensionFactory.of(Map.of(MeasurementDimensionConstituent.TEMPERATURE, 1));

			case VOLUME -> LENGTH.dimension().power(3);
			case ENERGY -> MASS.dimension().multiply(LENGTH.dimension().power(2)).divide(TIME.dimension().power(2));

			case MASS_DENSITY -> MASS.dimension().divide(VOLUME.dimension());
			case ENERGY_DENSITY ->
					MASS.dimension().multiply(LENGTH.dimension().power(-1)).divide(TIME.dimension().power(2));
		};
	}

	@Override
	public MeasurementDimension multiply(final MeasurementDimension other)
	{
		return this.dimension().multiply(other);
	}

	@Override
	public MeasurementDimension divide(final MeasurementDimension other)
	{
		return this.dimension().divide(other);
	}

	@Override
	public MeasurementDimension power(final int power)
	{
		return this.dimension().power(power);
	}
}