package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.dimension.MeasurementDimension;

import java.util.Objects;
import java.util.Set;

record BasicMeasurementUnit(String symbol, Set<String> caseInsensitiveAliases, Set<String> caseSensitiveAliases,
							double conversionToBaseUnitFactor, MeasurementDimension dimension)
		implements MeasurementUnit
{
	static BasicMeasurementUnit createUnit(final String symbol, final Set<String> caseInsensitiveAliases,
										   final Set<String> caseSensitiveAliases, final double conversionToBaseUnitFactor,
										   final MeasurementDimension dimension)
	{
		return new BasicMeasurementUnit(symbol, caseInsensitiveAliases, caseSensitiveAliases,
				conversionToBaseUnitFactor, dimension);
	}

	@Override
	public MeasurementUnit multiply(MeasurementUnit other)
	{
		return MeasurementUnitFactory.multiply(this, other);
	}

	@Override
	public MeasurementUnit divide(MeasurementUnit other)
	{
		return MeasurementUnitFactory.divide(this, other);
	}

	@Override
	public MeasurementUnit power(int power)
	{
		return MeasurementUnitFactory.power(this, power);
	}


	@Override
	public int hashCode()
	{
		return Objects.hash(symbol, conversionToBaseUnitFactor, dimension);
	}

	@Override
	public boolean equals(Object o)
	{
		return this == o || o instanceof BasicMeasurementUnit that &&
				dimension.equals(that.dimension) &&
				symbol.equals(that.symbol) &&
				Double.compare(that.conversionToBaseUnitFactor, conversionToBaseUnitFactor) == 0;
	}

	@Override
	public String toString()
	{
		return symbol;
	}
}