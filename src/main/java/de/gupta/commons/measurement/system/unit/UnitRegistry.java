package de.gupta.commons.measurement.system.unit;

import de.gupta.commons.measurement.system.unit.implementation.UnitFactory;

public enum UnitRegistry
{
	GRAM,

	METER,

	CALORIE,

	YEAR;

	public final Unit unit()
	{
		return UnitFactory.fromRegistryValue(this);
	}

	public final Unit divide(UnitRegistry unitRegistry)
	{
		return this.unit().divide(unitRegistry.unit());
	}
}