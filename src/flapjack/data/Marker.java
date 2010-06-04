// Copyright 2007-2010 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package flapjack.data;

public class Marker extends XMLRoot implements Comparable<Marker>
{
	private String name;

	// The marker's poistion on the chromosome
	private float position;
	// And its "actual" position, if different (used by all-chromsome markers)
	private float realPosition = -999;

	private int dbKey;

	// (Might) hold summary information on the frequency of each allele state
	// for this marker
	private float[] frequencies;

	public Marker()
	{
	}

	public Marker(String name, float position)
	{
		this.name = new String(name);
		this.position = position;

		realPosition = position;
	}

	public Marker(String name, float position, float realPosition)
	{
		this.name = new String(name);
		this.position = position;
		this.realPosition = realPosition;
	}

	public Marker(boolean dummy, float position)
	{
		this.position = position;

		realPosition = -1000;
	}

	void validate()
		throws NullPointerException
	{
		if (name == null)
			throw new NullPointerException();

		// This copes with existing (pre 01/06/2010) projects that don't have
		// the realPosition variable set. DummyMarkers have a realPosition set
		// to -1000 which we want to leave as is. All others should be reset
		if (realPosition < 0 && realPosition != -1000)
			realPosition = position;
	}


	// Methods required for XML serialization

	public String getName()
		{ return name; }

	public void setName(String name)
		{ this.name = name; }

	public float getPosition()
		{ return position; }

	public void setPosition(float position)
		{ this.position = position; }

	public float getRealPosition()
		{ return realPosition; }

	public void setRealPosition(float realPosition)
		{ this.realPosition = realPosition; }

	public int getDbKey()
		{ return dbKey; }

	public void setDbKey(int dbKey)
		{ this.dbKey = dbKey; }

	public float[] getFrequencies()
		{ return frequencies; }

	public void setFrequencies(float[] frequencies)
		{ this.frequencies = frequencies; }


	// Other methods

	public String toString()
		{ return name; }

	public int compareTo(Marker marker)
	{
		if (marker.position > position)
			return -1;
		else if (marker.position == position)
			return 0;
		else
			return 1;
	}

	/**
	 * Returns true if allele frequency statistics have been calculated and are
	 * available for this marker.
	 */
	public boolean frequenciesAvailable()
	{
		return (frequencies != null);
	}

	public boolean dummyMarker()
		{ return realPosition == -1000; }
}