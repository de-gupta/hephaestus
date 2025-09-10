package de.gupta.commons.measurement.system.unit;

import java.util.EnumMap;

public final class MeasurementUnitFactory
{
	public static MeasurementUnit multiply(MeasurementUnit left, MeasurementUnit right)
	{
		// Convert BasicMeasurementUnit to registry operations for consistency
		if (left instanceof BasicMeasurementUnit)
		{
			return multiply(convertBasicToRegistry(left), right);
		}
		if (right instanceof BasicMeasurementUnit)
		{
			return multiply(left, convertBasicToRegistry(right));
		}

		if (left instanceof MeasurementUnitRegistry leftRegistry && right instanceof MeasurementUnitRegistry rightRegistry)
		{
			return createFromRegistryPair(leftRegistry, 1, rightRegistry, 1);
		}
		else if (left instanceof MeasurementUnitImpl leftImpl && right instanceof MeasurementUnitRegistry rightRegistry)
		{
			return createFromImplAndRegistry(leftImpl, rightRegistry, 1);
		}
		else if (left instanceof MeasurementUnitRegistry leftRegistry && right instanceof MeasurementUnitImpl rightImpl)
		{
			return createFromImplAndRegistry(rightImpl, leftRegistry, 1);
		}
		else if (left instanceof MeasurementUnitImpl leftImpl && right instanceof MeasurementUnitImpl rightImpl)
		{
			return createFromImplPair(leftImpl, rightImpl, 1);
		}
		throw new IllegalArgumentException("Unknown unit types");
	}

	public static MeasurementUnit divide(MeasurementUnit left, MeasurementUnit right)
	{
		// Convert BasicMeasurementUnit to registry operations for consistency
		if (left instanceof BasicMeasurementUnit)
		{
			return divide(convertBasicToRegistry(left), right);
		}
		if (right instanceof BasicMeasurementUnit)
		{
			return divide(left, convertBasicToRegistry(right));
		}

		if (left instanceof MeasurementUnitRegistry leftRegistry && right instanceof MeasurementUnitRegistry rightRegistry)
		{
			return createFromRegistryPair(leftRegistry, 1, rightRegistry, -1);
		}
		else if (left instanceof MeasurementUnitImpl leftImpl && right instanceof MeasurementUnitRegistry rightRegistry)
		{
			return createFromImplAndRegistry(leftImpl, rightRegistry, -1);
		}
		else if (left instanceof MeasurementUnitRegistry leftRegistry && right instanceof MeasurementUnitImpl rightImpl)
		{
			return createFromRegistryAndImpl(leftRegistry, rightImpl, -1);
		}
		else if (left instanceof MeasurementUnitImpl leftImpl && right instanceof MeasurementUnitImpl rightImpl)
		{
			return createFromImplPair(leftImpl, rightImpl, -1);
		}
		throw new IllegalArgumentException("Unknown unit types");
	}

	public static MeasurementUnit power(MeasurementUnit unit, int exponent)
	{
		// Convert BasicMeasurementUnit to registry operations for consistency
		if (unit instanceof BasicMeasurementUnit)
		{
			return power(convertBasicToRegistry(unit), exponent);
		}

		if (unit instanceof MeasurementUnitRegistry registry)
		{
			return createFromRegistry(registry, exponent);
		}
		else if (unit instanceof MeasurementUnitImpl impl)
		{
			return createFromImpl(impl, exponent);
		}
		throw new IllegalArgumentException("Unknown unit type");
	}

	private static MeasurementUnit createFromRegistryPair(MeasurementUnitRegistry left, int leftExp,
														  MeasurementUnitRegistry right, int rightExp)
	{
		EnumMap<MeasurementUnitRegistry, Integer> components = new EnumMap<>(MeasurementUnitRegistry.class);
		components.put(left, leftExp);
		components.put(right, rightExp);
		return MeasurementUnitImpl.of(components);
	}

	private static MeasurementUnit createFromImplAndRegistry(MeasurementUnitImpl impl, MeasurementUnitRegistry registry, int registryExp)
	{
		EnumMap<MeasurementUnitRegistry, Integer> components = new EnumMap<>(impl.components());
		components.put(registry, components.getOrDefault(registry, 0) + registryExp);
		return MeasurementUnitImpl.of(components);
	}

	private static MeasurementUnit createFromRegistryAndImpl(MeasurementUnitRegistry registry, MeasurementUnitImpl impl, int implExp)
	{
		EnumMap<MeasurementUnitRegistry, Integer> components = new EnumMap<>(MeasurementUnitRegistry.class);
		components.put(registry, 1);
		impl.components().forEach((key, value) -> components.put(key, components.getOrDefault(key, 0) + value * implExp));
		return MeasurementUnitImpl.of(components);
	}

	private static MeasurementUnit createFromImplPair(MeasurementUnitImpl left, MeasurementUnitImpl right, int rightExp)
	{
		EnumMap<MeasurementUnitRegistry, Integer> components = new EnumMap<>(left.components());
		right.components().forEach((key, value) -> components.put(key, components.getOrDefault(key, 0) + value * rightExp));
		return MeasurementUnitImpl.of(components);
	}

	private static MeasurementUnit createFromRegistry(MeasurementUnitRegistry registry, int exponent)
	{
		if (exponent == 1) return registry;
		EnumMap<MeasurementUnitRegistry, Integer> components = new EnumMap<>(MeasurementUnitRegistry.class);
		components.put(registry, exponent);
		return MeasurementUnitImpl.of(components);
	}

	private static MeasurementUnit createFromImpl(MeasurementUnitImpl impl, int exponent)
	{
		EnumMap<MeasurementUnitRegistry, Integer> components = new EnumMap<>(MeasurementUnitRegistry.class);
		impl.components().forEach((key, value) -> components.put(key, value * exponent));
		return MeasurementUnitImpl.of(components);
	}

	private static MeasurementUnitRegistry convertBasicToRegistry(MeasurementUnit basicUnit)
	{
		// Find the registry enum that corresponds to this basic unit
		for (MeasurementUnitRegistry registry : MeasurementUnitRegistry.values())
		{
			if (registry.unit().equals(basicUnit))
			{
				return registry;
			}
		}
		throw new IllegalArgumentException("Cannot find registry enum for basic unit: " + basicUnit);
	}

	private MeasurementUnitFactory() {}
}