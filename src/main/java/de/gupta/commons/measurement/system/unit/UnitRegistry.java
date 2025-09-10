package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

import java.util.Map;

public enum UnitRegistry implements Unit
{
	DIMENSIONLESS,

	KILOGRAM,
	METER,
	SECOND,
	KELVIN,

	METER_PER_SECOND;

	@Override
	public Unit multiply(final Unit other)
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
	public Unit divide(final Unit other)
	{
		return this.unit().divide(other);
	}

	@Override
	public Unit power(final int power)
	{
		return this.unit().power(power);
	}

	private Unit unit()
	{
		return switch (this)
		{
			case DIMENSIONLESS -> UnitFactory.dimensionless();

			case KILOGRAM -> UnitFactory.of(Map.of(UnitConstituent.KILOGRAM, 1));
			case METER -> UnitFactory.of(Map.of(UnitConstituent.METER, 1));
			case SECOND -> UnitFactory.of(Map.of(UnitConstituent.SECOND, 1));
			case KELVIN -> UnitFactory.of(Map.of(UnitConstituent.KELVIN, 1));

			case METER_PER_SECOND -> UnitFactory.of(Map.of(UnitConstituent.METER, 1, UnitConstituent.SECOND, -1));
		};
	}
}