package de.gupta.commons.measurement.system.dimension;

import de.gupta.commons.utility.math.algebra.algebraicGroup.Group;

public sealed interface MeasurementDimension permits MeasurementDimensionImpl, MeasurementDimensionRegistry
{
	MeasurementDimension multiply(MeasurementDimension other);

	MeasurementDimension divide(MeasurementDimension other);

	MeasurementDimension power(int power);
}