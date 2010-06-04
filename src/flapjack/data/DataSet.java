// Copyright 2007-2010 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package flapjack.data;

import java.util.*;

public class DataSet extends XMLRoot
{
	private String name;

	// Data information
	private ArrayList<ChromosomeMap> chromosomes = new ArrayList<ChromosomeMap>();
	private ArrayList<Line> lines = new ArrayList<Line>();

	// Dummy line data (can be used whenever we need a 'placeholder' line
	private Line dummyLine;

	// Trait information
	private ArrayList<Trait> traits = new ArrayList<Trait>();

	// View information
	private StateTable stateTable = new StateTable(0);
	private ArrayList<GTViewSet> viewSets = new ArrayList<GTViewSet>();

	// DB-link/association data
	private DBAssociation dbAssociation = new DBAssociation();

	public DataSet()
	{
	}

	void validate()
		throws NullPointerException
	{
		if (name == null)
			throw new NullPointerException();

		for (ChromosomeMap map: chromosomes)
			map.validate();
		for (Line line: lines)
			line.validate();
		for (Trait trait: traits)
			trait.validate();

		stateTable.validate();
		for (GTViewSet viewSet: viewSets)
			viewSet.validate();
	}


	// Methods required for XML serialization

	public String getName()
		{ return name; }

	public void setName(String name)
		{ this.name = name; }

	public ArrayList<ChromosomeMap> getChromosomeMaps()
		{ return chromosomes; }

	public void setChromosomeMaps(ArrayList<ChromosomeMap> chromosomes)
		{ this.chromosomes = chromosomes; }

	public ArrayList<Line> getLines()
		{ return lines; }

	public void setLines(ArrayList<Line> lines)
		{ this.lines = lines; }

	public Line getDummyLine()
		{ return dummyLine; }

	public void setDummyLine(Line dummyLine)
		{ this.dummyLine = dummyLine; }

	public ArrayList<Trait> getTraits()
		{ return traits; }

	public void setTraits(ArrayList<Trait> traits)
		{ this.traits = traits; }

	public StateTable getStateTable()
		{ return stateTable; }

	public void setStateTable(StateTable stateTable)
		{ this.stateTable = stateTable; }

	public ArrayList<GTViewSet> getViewSets()
		{ return viewSets; }

	public void setViewSets(ArrayList<GTViewSet> viewSets)
		{ this.viewSets = viewSets; }

	public DBAssociation getDbAssociation()
		{ return dbAssociation; }

	public void setDbAssociation(DBAssociation dbAssociation)
		{ this.dbAssociation = dbAssociation; }


	// Other methods

	public Line createLine(String name, boolean useByteStorage)
	{
		Line line = new Line(name, lines.size());

		// This ensures each line has a set of (empty) loci data for each map
		for (ChromosomeMap map: chromosomes)
			line.initializeMap(map, useByteStorage);

		lines.add(line);

		return line;
	}

	public ChromosomeMap getMapByIndex(int index)
		throws ArrayIndexOutOfBoundsException
	{
		return chromosomes.get(index);
	}

	public Line getLineByIndex(int index)
		throws ArrayIndexOutOfBoundsException
	{
		return lines.get(index);
	}

	/**
	 * Returns the chromosome map (and its index within the dataset) with the
	 * given name, or creates a new map with this name if it could not be found.
	 */
	public ChromosomeMap.Wrapper getMapByName(String mapName, boolean create)
	{
		for (int i = 0; i < chromosomes.size(); i++)
			if (chromosomes.get(i).getName().equals(mapName))
				return new ChromosomeMap.Wrapper(chromosomes.get(i), i);

		ChromosomeMap map = new ChromosomeMap(mapName);
		chromosomes.add(map);

//		System.out.println("Added map " + map + " to dataset");

		return new ChromosomeMap.Wrapper(map, chromosomes.size()-1);
	}

	public int countChromosomeMaps()
	{
		return chromosomes.size();
	}

	public int countLines()
	{
		return lines.size();
	}

	/**
	 * Returns the total number of markers across all maps.
	 */
	public int countMarkers()
	{
		int count = 0;
		for (ChromosomeMap map: chromosomes)
			count += map.countLoci();

		return count;
	}

	/**
	 * Sorts the markers within each chromosome map so that they are held in
	 * ascending position order.
	 */
	public void sortChromosomeMaps()
	{
		for (ChromosomeMap map: chromosomes)
			map.sort();
	}

	public void createSuperChromosome()
	{

		// Create a new "super" chromosome to hold every marker
		ChromosomeMap allMap = new ChromosomeMap("AllChromosomes");

		float mapOffset = 0;

		// Traverse each existing chromsome...
		for (int i = 0; i < chromosomes.size(); i++)
		{
			ChromosomeMap map = chromosomes.get(i);

			// Adding clones of its markers to the super chromosome
			for (Marker marker: map)
			{
				// Offset each marker's position
				float position = mapOffset + marker.getPosition();
				float realPosition = marker.getPosition();

				Marker m = new Marker(marker.getName(), position, realPosition);
				allMap.addMarker(m);
			}

			mapOffset += map.getLength();

			// Add three dummy markers
			if (i < chromosomes.size()-1)
				for (int d = 0; d < 5; d++)
					allMap.addMarker(new Marker("DUMMY", mapOffset));
		}

		allMap.sort();

		int allMapIndex = chromosomes.size();

		// Now force the lines to duplicate their info too
		for (Line line: lines)
		{
			System.out.println("processing " + line.getName());
			line.initializeMap(allMap, true);
			int loci = 0;

			for (int i = 0; i < chromosomes.size(); i++)
			{
				ChromosomeMap map = chromosomes.get(i);

				for (int j = 0; j < map.countLoci(); j++, loci++)
					line.setLoci(allMapIndex, loci, line.getState(i, j));

				if (i < chromosomes.size()-1)
					for (int d = 0; d < 5; d++, loci++)
						line.setLoci(allMapIndex, loci, 0);
			}
		}

		chromosomes.add(allMap);
	}
}