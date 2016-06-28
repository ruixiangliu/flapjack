// Copyright 2009-2016 Information & Computational Sciences, JHI. All rights
// reserved. Use is subject to the accompanying licence terms.

package jhi.flapjack.gui.table;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.table.*;
import jhi.flapjack.analysis.SortLinesByLineDataModel;

import jhi.flapjack.data.*;
import jhi.flapjack.gui.*;

import scri.commons.gui.*;

public class LineDataTable extends JTable
{
	private LineDataTableModel model;
	private TableRowSorter<LineDataTableModel> sorter;

	private boolean colorCells = true;

	private GTViewSet viewSet;

	// The list of SortableColumn objects used the last time a sort was run
	private SortableColumn[] lastSort;

	public LineDataTable()
	{
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		getTableHeader().setReorderingAllowed(false);

		setDefaultRenderer(String.class, new ColoredCellRenderer());
		setDefaultRenderer(Float.class, new ColoredCellRenderer());
		setDefaultRenderer(Double.class, new ColoredCellRenderer());

		UIScaler.setCellHeight(this);
	}

	public boolean colorCells()
		{ return colorCells; }

	public void setColorCells(boolean colorCells)
		{ this.colorCells = colorCells; }

	// Ensures all column headers have tooltips
	@Override
	public JTableHeader createDefaultTableHeader()
	{
		return new JTableHeader(columnModel)
		{
			@Override
			public String getToolTipText(MouseEvent e)
			{
				Point p = e.getPoint();
				int index = columnModel.getColumnIndexAtX(p.x);
				if (index >= 0 && index < columnModel.getColumnCount())
				{
					int realIndex = columnModel.getColumn(index).getModelIndex();
					return getModel().getColumnName(realIndex);
				}
				else
					return null;
			}
		};
	}

	@Override
	public void setModel(TableModel tm)
	{
		super.setModel(tm);

		// Set a default width per column
		for (int i = 0; i < getColumnCount(); i++)
		{
			TableColumn column = getColumnModel().getColumn(i);
			column.setPreferredWidth(UIScaler.scale(120));
		}

		// Safety net for Matisse code calling setModel with a DefaultTableModel
		if (tm instanceof LineDataTableModel)
		{
			model = (LineDataTableModel) tm;

			// Let the user sort by column
			sorter = new TableRowSorter<>(model);
			setRowSorter(sorter);

			// Catch sort events
			sorter.addRowSorterListener((RowSorterEvent e) ->
			{
				// We only want to deal with events of type sorted...not sort order changed
				if (e.getType() == RowSorterEvent.Type.SORTED)
				{
					ArrayList<LineInfo> orderedLines = new ArrayList<>();
					for (int i = 0; i < getRowCount(); i++)
						orderedLines.add((LineInfo)model.getValueAt(convertRowIndexToModel(i), 0));

					if (viewSet != null)
						viewSet.setLines(orderedLines);
				}
			});
		}
	}

	public void setViewSet(GTViewSet viewSet)
	{
		this.viewSet = viewSet;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		// WATCH OUT FOR THIS FUNKY CODE. Because we need the main view to reflect the sorting of the table we end up
		// applying the sort to the list which the TableModel wraps. This then breaks the sorting of the table as its
		// array of view to model indexes points at the locations of elements in the pre-sorted list. To work our way
		// around this (but still have the niceties of JTable sorting) we simply make the table look straight at the
		// model instread by calling convertRowIndexToView with the row the table is looking for.
		row = convertRowIndexToView(row);

		return super.getValueAt(row, column);
	}

	public void exportData()
	{
		String name = "table-data.txt";
		File saveAs = new File(Prefs.guiCurrentDir, name);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			RB.getString("other.Filters.txt"), "txt");

		// Ask the user for a filename to save the current view as
		String filename = FlapjackUtils.getSaveFilename("Save table data as", saveAs, filter);

		// Quit if the user cancelled the file selection
		if (filename == null)
			return;

		LineDataTableExporter exporter = new LineDataTableExporter(this, new File(filename));
		ProgressDialog dialog = new ProgressDialog(exporter,
			RB.format("gui.dialog.ExportDataDialog.exportTitle"),
			RB.format("gui.dialog.ExportDataDialog.exportLabel"), Flapjack.winMain);

		if (dialog.failed("gui.error"))
			return;

		TaskDialog.info(
			RB.format("gui.dialog.ExportDataDialog.exportSuccess", filename),
			RB.getString("gui.text.close"));
	}

	public void multiColumnSort()
	{
		SortDialog dialog = new SortDialog(model.getSortableColumns(), lastSort);
		if (dialog.isOK() == false)
			return;

		// Get the list of columns to use for the sort
		SortableColumn[] data = dialog.getSortInfo();
		// And also remember it for next time in case the user runs another sort
		lastSort = dialog.getSortInfo();

		SortLinesByLineDataModel s = new SortLinesByLineDataModel(viewSet, sorter, data);
		Flapjack.winMain.mAnalysis.runSort(s);

		model.fireTableDataChanged();
	}

	// Deals with the fact that our fake double header for the JTable means
	// that String data can be found in numerical columns.
	private class ColoredCellRenderer extends DefaultTableCellRenderer
	{
		protected final NumberFormat nf = NumberFormat.getInstance();

		private Color bgCol1 = UIManager.getColor("Table.selectionBackground");
		private Color bgCol2 = UIManager.getColor("Table.background");

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
		{
			super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

			// Align numerical values to the right
			if (value instanceof Number)
			{
				setText(nf.format((Number)value));
				setHorizontalAlignment(JLabel.RIGHT);
			}

			int iRow = table.getRowSorter().convertRowIndexToModel(row);
			Color bg = model.getDisplayColor(iRow, column);

			if (colorCells && bg != null)
				setBackground(isSelected ? bg.darker() : bg);
			else
				setBackground(isSelected ? bgCol1 : bgCol2);

			return this;
		}
	}
}