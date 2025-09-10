package de.gupta.commons.measurement.system.unit.implementation;

import de.gupta.commons.measurement.system.unit.Unit;
import de.gupta.commons.measurement.system.unit.UnitRegistry;

import java.util.Map;

public final class UnitFactory
{
	public static Unit from(Unit unit)
	{
		return unit;
	}

	public static Unit withComponents(Map<BasicUnit, Integer> components)
	{
		return CompositeUnit.withComponents(components);
	}

	public static Unit fromRegistryValue(final UnitRegistry unitRegistryValue)
	{
		return switch (unitRegistryValue)
		{
			case GRAM -> BasicUnitRegistry.GRAM.basicUnit();
			case METER -> BasicUnitRegistry.METER.basicUnit();
			case CALORIE -> BasicUnitRegistry.CALORIE.basicUnit();
			case YEAR -> BasicUnitRegistry.YEAR.basicUnit();
		};
	}

	private UnitFactory()
	{
	}
}