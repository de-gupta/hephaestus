package de.gupta.commons.measurement.system.unit.implementation;

import de.gupta.commons.measurement.system.unit.Unit;

final class UnitArithmeticUtility
{
	static Unit identity()
	{
		return BasicUnitRegistry.DIMENSIONLESS.basicUnit();
	}

	static Unit inverse(Unit unit)
	{
		return CompositeUnit.from(unit).inverse(unit);
	}

	static Unit multiply(Unit unit1, Unit unit2)
	{
		return CompositeUnit.combine(CompositeUnit.from(unit1), CompositeUnit.from(unit2));
	}

	static Unit divide(Unit unit1, Unit unit2)
	{
		return unit1.multiply(CompositeUnit.from(unit2).power(-1));
	}

	static Unit power(Unit unit, int exponent)
	{
		return CompositeUnit.from(unit).power(exponent);
	}

	private UnitArithmeticUtility()
	{
	}
}