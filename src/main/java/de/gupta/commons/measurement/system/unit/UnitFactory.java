package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.utility.map.enumMap.EnumMapConstruction;

import java.util.Map;

public final class UnitFactory
{
	static Unit dimensionless()
	{
		return UnitImpl.identity();
	}

	static Unit scale(final Unit unit, final double factor)
	{
		// TODO
		return null;
	}

	static Unit of(final Map<UnitConstituent, Integer> exponents)
	{
		return UnitImpl.of(EnumMapConstruction.from(exponents, UnitConstituent.class));
	}

}