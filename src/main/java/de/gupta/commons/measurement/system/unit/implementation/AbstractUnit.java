package de.gupta.commons.measurement.system.unit.implementation;

import de.gupta.commons.measurement.system.unit.Unit;

abstract class AbstractUnit implements Unit
{
	@Override
	public Unit identity()
	{
		return UnitArithmeticUtility.identity();
	}

	@Override
	public Unit multiply(final Unit a, final Unit b)
	{
		return UnitArithmeticUtility.multiply(a, b);
	}

	@Override
	public Unit multiply(final Unit other)
	{
		return UnitArithmeticUtility.multiply(this, other);
	}

	@Override
	public Unit divide(final Unit other)
	{
		return UnitArithmeticUtility.divide(this, other);
	}

	@Override
	public Unit power(int exponent)
	{
		return UnitArithmeticUtility.power(this, exponent);
	}

	@Override
	public Unit inverse()
	{
		return UnitArithmeticUtility.inverse(this);
	}
}