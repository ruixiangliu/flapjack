// Copyright 2007-2010 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package flapjack.gui.dialog;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import flapjack.gui.*;

import scri.commons.gui.*;

class NBImportOptionsPanel extends javax.swing.JPanel
{

	/** Creates new form NBImportOptionsPanel */
	public NBImportOptionsPanel(MouseAdapter dcl, boolean secondaryOptions)
	{
		initComponents();

		setBackground((Color)UIManager.get("fjDialogBG"));
		panel1.setBackground((Color)UIManager.get("fjDialogBG"));
		panel2.setBackground((Color)UIManager.get("fjDialogBG"));

		panel1.setBorder(BorderFactory.createTitledBorder(
			RB.getString("gui.dialog.NBImportOptionsPanel.panel1.title")));
		RB.setText(label1, "gui.dialog.NBImportOptionsPanel.label1");

		RB.setText(rFromFile, "gui.dialog.NBImportOptionsPanel.rFromFile");
		rFromFile.setSelected(Prefs.guiImportMethod == 0);
		rFromFile.addMouseListener(dcl);

		RB.setText(rFromDB, "gui.dialog.NBImportOptionsPanel.rFromDB");
		rFromDB.setSelected(Prefs.guiImportMethod == 1);
//		rFromDB.addMouseListener(dcl);

		RB.setText(rFromSample, "gui.dialog.NBImportOptionsPanel.rFromSample");
		rFromSample.setSelected(Prefs.guiImportMethod == 2);
		rFromSample.addMouseListener(dcl);


		panel2.setBorder(BorderFactory.createTitledBorder(
			RB.getString("gui.dialog.NBImportOptionsPanel.panel2.title")));
		RB.setText(label2, "gui.dialog.NBImportOptionsPanel.label2");

		RB.setText(rTraitFile, "gui.dialog.NBImportOptionsPanel.rTraitFile");
		rTraitFile.setSelected(Prefs.guiImportMethod == 20);
		rTraitFile.addMouseListener(dcl);

		RB.setText(rQTLFile, "gui.dialog.NBImportOptionsPanel.rQTLFile");
		rQTLFile.setSelected(Prefs.guiImportMethod == 21);
		rQTLFile.addMouseListener(dcl);


		if (secondaryOptions == false)
		{
			if (Prefs.guiImportMethod >= 20)
				rFromFile.setSelected(true);

			rTraitFile.setEnabled(false);
			rQTLFile.setEnabled(false);
		}
	}

	void isOK()
	{
		if (rFromFile.isSelected())
			Prefs.guiImportMethod = 0;
		else if (rFromDB.isSelected())
			Prefs.guiImportMethod = 1;
		else if (rFromSample.isSelected())
			Prefs.guiImportMethod = 2;

		else if (rTraitFile.isSelected())
			Prefs.guiImportMethod = 20;
		else if (rQTLFile.isSelected())
			Prefs.guiImportMethod = 21;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panel1 = new javax.swing.JPanel();
        rFromFile = new javax.swing.JRadioButton();
        rFromDB = new javax.swing.JRadioButton();
        rFromSample = new javax.swing.JRadioButton();
        label1 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        rTraitFile = new javax.swing.JRadioButton();
        rQTLFile = new javax.swing.JRadioButton();

        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Primary data import options:"));

        buttonGroup.add(rFromFile);
        rFromFile.setText("Providing the map and genotype data from files located on disk");

        buttonGroup.add(rFromDB);
        rFromDB.setText("Connecting to a Germinate database and importing directly");
        rFromDB.setEnabled(false);

        buttonGroup.add(rFromSample);
        rFromSample.setText("Using the built-in example dataset bundled with Flapjack");

        label1.setText("Import into Flapjack by:");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rFromFile)
                            .addComponent(rFromDB)
                            .addComponent(rFromSample)))
                    .addComponent(label1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rFromFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rFromDB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rFromSample)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Secondary data import options:"));

        label2.setText("Additional information can also be provided for existing data sets:");

        buttonGroup.add(rTraitFile);
        rTraitFile.setText("Import trait information from a file located on disk");

        buttonGroup.add(rQTLFile);
        rQTLFile.setText("Import QTL information from a file located on disk");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rTraitFile)
                            .addComponent(rQTLFile)))
                    .addComponent(label2))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rTraitFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rQTLFile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JRadioButton rFromDB;
    private javax.swing.JRadioButton rFromFile;
    private javax.swing.JRadioButton rFromSample;
    private javax.swing.JRadioButton rQTLFile;
    private javax.swing.JRadioButton rTraitFile;
    // End of variables declaration//GEN-END:variables

}