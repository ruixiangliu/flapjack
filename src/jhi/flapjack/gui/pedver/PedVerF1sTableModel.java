// Copyright 2009-2016 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.flapjack.gui.pedver;

import java.util.*;

import jhi.flapjack.data.*;
import jhi.flapjack.data.results.*;
import jhi.flapjack.gui.*;
import jhi.flapjack.gui.table.*;

class PedVerF1sTableModel extends LineDataTableModel
{
	private static final int selectedIndex = 12;
	private static final int commentIndex = 13;
	private static final int sortIndex = 14;

	PedVerF1sTableModel(DataSet dataSet, GTViewSet viewSet)
	{
		this.dataSet = dataSet;

		setLines(new ArrayList<>(viewSet.getLines()));
		initModel();
	}

	void initModel()
	{
		columnNames = new String[] { "Line", "Marker count", "% Missing",
			"Het count", "% Het", "% Deviation from Expected", "Count P1 Contained",
			"% P1 Contained", "Count P2 Contained", "% P2 Contained",
			"Count Allele Match to Expected", "% Allele Match to Expected",
			"Selected", "Comments", "Don't Sort/Filter" };
	}

	@Override
	public int getRowCount()
	{
		return lines.size();
	}

	@Override
	public Object getObjectAt(int row, int col)
	{
		LineInfo line = lines.get(row);
		PedVerF1sResult stats = line.getResults().getPedVerF1sResult();

		// Name, Selected and Sort can work without results
		if (col == 0)
			return line;
		else if (col == selectedIndex)
			return line.getSelected();
		else if (col == sortIndex)
			return line.getResults().isSortToTop();

		if (stats == null)
			return null;

		switch (col)
		{
			case 1: return stats.getMarkerCount();
			case 2: return stats.getPercentMissing();
			case 3: return stats.getHeterozygousCount();
			case 4: return stats.getPercentHeterozygous();
			case 5: return stats.getPercentDeviationFromExpected();
			case 6: return stats.getCountP1Contained();
			case 7: return stats.getPercentP1Contained();
			case 8: return stats.getCountP2Contained();
			case 9: return stats.getPercentP2Contained();
			case 10: return stats.getCountAlleleMatchExpected();
			case 11: return stats.getPercentAlleleMatchExpected();

			case commentIndex:
				String comment = line.getResults().getComments();
				return comment == null ? "" : comment;

			default: return null;
		}
	}

	@Override
	public Class getObjectColumnClass(int col)
	{
		if (col == 0)
			return LineInfo.class;
		else if (col == selectedIndex || col == sortIndex)
			return Boolean.class;
		else if (col == commentIndex)
			return String.class;
		else
			return Double.class;
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return (col == selectedIndex || col == commentIndex || col == sortIndex);
	}

	@Override
	public void setValueAt(Object value, int row, int col)
	{
		LineInfo line = (LineInfo) getObjectAt(row, 0);

		if (col == selectedIndex)
			line.setSelected((boolean)value);
		else if (col == commentIndex)
			line.getResults().setComments((String)value);
		else if (col == sortIndex)
			line.getResults().setSortToTop((boolean)value);

		fireTableRowsUpdated(row, row);

		Actions.projectModified();
	}
}