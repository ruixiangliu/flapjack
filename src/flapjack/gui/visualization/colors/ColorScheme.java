package flapjack.gui.visualization.colors;

import java.awt.*;
import java.awt.image.*;

import flapjack.data.*;

/**
 * Abstract base class for all color schemes.
 */
public abstract class ColorScheme
{
	public static final int RANDOM = 0;
	public static final int NUCLEOTIDE = 1;

	protected GTView view;
	protected StateTable stateTable;

	ColorScheme(GTView view)
	{
		this.view = view;
		stateTable = view.getStateTable();
	}

	public abstract BufferedImage getImage(int line, int marker);

	public abstract Color getColor(int line, int marker);
}