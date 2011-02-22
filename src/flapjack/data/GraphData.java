// Copyright 2007-2011 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package flapjack.data;

import java.util.*;

import scri.commons.*;

public class GraphData extends XMLRoot
{
	// The name of this graph
	private String name;

	// The data for the graph - stored as normalized values
	private float[] data;

	// The maximum and minimum values within the data
	private float min = Float.MAX_VALUE, max = Float.MIN_VALUE;

	private boolean hasThreshold = false;
	private float threshold;

	public GraphData()
	{
	}

	public GraphData(ChromosomeMap map, String name)
	{
		data = new float[map.countLoci()];
		this.name = name;
	}

	// Methods required for XML serialization

	public String getName()
		{ return name; }

	public void setName(String name)
		{ this.name = name; }

	public String getData()
		{ return MatrixXML.arrayToString(data); }

	public void setData(String dataStr)
		{ this.data = MatrixXML.stringToFloatArray(dataStr); }

	public float getMinimum()
		{ return min; }

	public void setMinimum(float min)
		{ this.min = min; }

	public float getMaximum()
		{ return max; }

	public void setMaximum(float max)
		{ this.max = max; }

	public boolean getHasThreshold()
		{ return hasThreshold; }

	public void setHasThreshold(boolean hasThreshold)
		{ this.hasThreshold = hasThreshold; }

	public float getThreshold()
		{ return threshold; }

	public void setThreshold(float threshold)
		{ this.threshold = threshold; }


	// Other methods

	public void setValue(int mrkIndex, float value)
	{
		// Add the value...
		data[mrkIndex] = value;

		// Check if it's the lowest or highest value seen yet
		min = Math.min(value, min);
		max = Math.max(value, max);
	}

	public void normalize()
	{
		// Normalize the threshold
		if (hasThreshold)
		{
			// Quick (extra) check to ensure it isn't the lowest/highest value
			min = Math.min(threshold, min);
			max = Math.max(threshold, max);

			threshold = (threshold - min) / (max - min);

			if (Float.isNaN(threshold))
				threshold = 0;
		}

		// Normalize every value in the graph...
		for (int d = 0; d < data.length; d++)
		{
			data[d] = (data[d] - min) / (max - min);

			if (Float.isNaN(data[d]))
				data[d] = 0;
		}
	}

	public float getRealValueAt(int mIndex)
	{
		// Get the value for it from the graph data
		float value = data[mIndex];

		// "Unnormalize" it back to its original value
		value = (value * (max-min)) + min;

		return value;
	}

	public float[] data()
		{ return data; }

	public void determineThreshold(Float value)
	{
		if (value > Float.MIN_VALUE)
		{
			hasThreshold = true;
			threshold = value;
		}
	}
}