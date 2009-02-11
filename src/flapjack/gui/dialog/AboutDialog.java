package flapjack.gui.dialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import flapjack.gui.*;

import scri.commons.gui.*;

public class AboutDialog extends JDialog implements ActionListener
{
	private JButton bClose;

	private NBAboutPanel nbPanel;

	public AboutDialog()
	{
		super(
			Flapjack.winMain,
			RB.getString("gui.dialog.AboutDialog.title"),
			true
		);

		nbPanel = new NBAboutPanel();

		add(nbPanel);
		add(createButtons(), BorderLayout.SOUTH);

		getRootPane().setDefaultButton(bClose);
		SwingUtils.addCloseHandler(this, bClose);

		pack();
		setLocationRelativeTo(Flapjack.winMain);
		setResizable(false);
		setVisible(true);
	}

	private JPanel createButtons()
	{
		bClose = SwingUtils.getButton(RB.getString("gui.text.close"));
		bClose.addActionListener(this);

		JPanel p1 = FlapjackUtils.getButtonPanel();
		p1.add(bClose);

		return p1;
	}

	public void actionPerformed(ActionEvent e)
	{
		setVisible(false);
	}
}