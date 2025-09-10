package de.gupta.commons.measurement.system.unit.implementation;

import java.util.Map;

public final class CompositeUnitFactory
{
	public static CompositeUnit withComponents(final Map<BasicUnit, Integer> units)
	{
		return CompositeUnit.withComponents(units);
	}

	private CompositeUnitFactory()
	{
	}
}