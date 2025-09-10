package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

import java.util.Map;

public enum MeasurementUnitRegistry implements MeasurementUnit
{
	DIMENSIONLESS,

	KILOGRAM,
	METER,
	SECOND,
	KELVIN;

	@Override
	public MeasurementUnit multiply(final MeasurementUnit other)
	{
		return this.unit().multiply(other);
	}

	@Override
	public String symbol()
	{
		return this.unit().symbol();
	}

	@Override
	public double conversionToBaseUnitFactor()
	{
		return this.unit().conversionToBaseUnitFactor();
	}

	@Override
	public MeasurementDimension dimension()
	{
		return this.unit().dimension();
	}

	@Override
	public MeasurementUnit divide(final MeasurementUnit other)
	{
		return this.unit().divide(other);
	}

	@Override
	public MeasurementUnit power(final int power)
	{
		return this.unit().power(power);
	}

	private MeasurementUnit unit()
	{
		return switch (this)
		{
			case DIMENSIONLESS -> MeasurementUnitFactory.dimensionless();

			case KILOGRAM -> MeasurementUnitFactory.of(Map.of(MeasurementUnitConstituent.KILOGRAM, 1));
			case METER -> MeasurementUnitFactory.of(Map.of(MeasurementUnitConstituent.METER, 1));
			case SECOND -> MeasurementUnitFactory.of(Map.of(MeasurementUnitConstituent.SECOND, 1));
			case KELVIN -> MeasurementUnitFactory.of(Map.of(MeasurementUnitConstituent.KELVIN, 1));
		};
	}
}