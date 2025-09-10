package de.gupta.commons.measurement.system.dimension;

public sealed interface MeasurementDimension permits MeasurementDimensionImpl
{
	MeasurementDimension multiply(MeasurementDimension other);

	MeasurementDimension divide(MeasurementDimension other);

	MeasurementDimension power(int power);
}