// Copyright 2009-2020 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.flapjack.data.results;

import java.util.*;

import jhi.flapjack.data.*;

public class IFBSummary extends XMLRoot
{
	private GTViewSet viewSet;
	private ArrayList<LineInfo> lines;

	// TODO: These should be references in CASTOR
	private int familySize;

	// Don't save these!
//	private Double percentDataAvg, percentHetAvg, similarityToP1Avg,
//		similarityToP2Avg, percentAlleleMatchExpectedAvg;


	public IFBSummary()
	{
	}

	public IFBSummary(GTViewSet viewSet, IFBBatchList batchList)
	{
		this.viewSet = viewSet;
		this.lines = viewSet.getLines();

		familySize = lines.size();
	}

	// XML serialization methods

	public GTViewSet getViewSet()
		{ return viewSet; }

	public void setViewSet(GTViewSet viewSet)
		{ this.viewSet = viewSet; }

	public ArrayList<LineInfo> getLines()
		{ return lines; }

	public void setLines(ArrayList<LineInfo> lines)
		{ this.lines = lines; }

	public int getFamilySize()
		{ return familySize; }

	public void setFamilySize(int familySize)
		{ this.familySize = familySize; }


	public String name()
	{
		return viewSet.getDataSet().getName() + " - " + viewSet.getName();
	}


	public float proportionSelected()
	{
		long selectedCount = lines.stream()
			.filter(LineInfo::getSelected).count();

		return selectedCount / (float)familySize;
	}

	public double minWeightedMBVSelected()
	{
		return lines.stream()
			.filter(LineInfo::getSelected)
			.filter(li -> li.getLineResults().getIFBResult().isMbvValid())
			.mapToDouble(li -> li.getLineResults().getIFBResult().getWmbvTotal())
			.min()
			.orElse(Double.NaN);
	}

	public double maxWeightedMBVSelected()
	{
		return lines.stream()
			.filter(LineInfo::getSelected)
			.filter(li -> li.getLineResults().getIFBResult().isMbvValid())
			.mapToDouble(li -> li.getLineResults().getIFBResult().getWmbvTotal())
			.max()
			.orElse(Double.NaN);
	}

	public double avgWeightedMBVSelected()
	{
		return lines.stream()
			.filter(LineInfo::getSelected)
			.filter(li -> li.getLineResults().getIFBResult().isMbvValid())
			.mapToDouble(li -> li.getLineResults().getIFBResult().getWmbvTotal())
			.average()
			.orElse(Double.NaN);
	}

	// Same as the above, but this time on Non Missing (NM) data
	public double minWeightedMBVSelectedNM()
	{
		return lines.stream()
			.filter(LineInfo::getSelected)
			.mapToDouble(li -> li.getLineResults().getIFBResult().getWmbvTotal2())
			.min()
			.orElse(Double.NaN);
	}

	public double maxWeightedMBVSelectedNM()
	{
		return lines.stream()
			.filter(LineInfo::getSelected)
			.mapToDouble(li -> li.getLineResults().getIFBResult().getWmbvTotal2())
			.max()
			.orElse(Double.NaN);
	}

	public double avgWeightedMBVSelectedNM()
	{
		return lines.stream()
			.filter(LineInfo::getSelected)
			.mapToDouble(li -> li.getLineResults().getIFBResult().getWmbvTotal2())
			.average()
			.orElse(Double.NaN);
	}
}