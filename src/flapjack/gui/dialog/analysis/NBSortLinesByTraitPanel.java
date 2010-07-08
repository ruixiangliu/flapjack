// Copyright 2007-2010 Plant Bioinformatics Group, SCRI. All rights reserved.
// Use is subject to the accompanying licence terms.

package flapjack.gui.dialog.analysis;

import java.awt.*;
import javax.swing.*;

import flapjack.data.*;
import flapjack.gui.*;
import flapjack.gui.visualization.*;

import scri.commons.gui.*;

class NBSortLinesByTraitPanel extends JPanel
{
	public NBSortLinesByTraitPanel(GenotypePanel gPanel)
	{
		initComponents();

		setBackground((Color)UIManager.get("fjDialogBG"));
		panel1.setBackground((Color)UIManager.get("fjDialogBG"));
		panel2.setBackground((Color)UIManager.get("fjDialogBG"));
		panel3.setBackground((Color)UIManager.get("fjDialogBG"));

		panel1.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.analysis.NBSortLinesByTraitPanel.panel1.title")));
		panel2.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.analysis.NBSortLinesByTraitPanel.panel2.title")));
		panel3.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.analysis.NBSortLinesByTraitPanel.panel3.title")));
		RB.setText(rAsc1, "gui.dialog.analysis.NBSortLinesByTraitPanel.ascending");
		RB.setText(rAsc2, "gui.dialog.analysis.NBSortLinesByTraitPanel.ascending");
		RB.setText(rAsc3, "gui.dialog.analysis.NBSortLinesByTraitPanel.ascending");
		RB.setText(rDes1, "gui.dialog.analysis.NBSortLinesByTraitPanel.descending");
		RB.setText(rDes2, "gui.dialog.analysis.NBSortLinesByTraitPanel.descending");
		RB.setText(rDes3, "gui.dialog.analysis.NBSortLinesByTraitPanel.descending");
		RB.setText(checkAssign, "gui.dialog.analysis.NBSortLinesByTraitPanel.checkAssign");


		// Fill the combo boxes with the possible traits
		DataSet dataSet = gPanel.getViewSet().getDataSet();

		combo2.addItem("");
		combo3.addItem("");
		for (Trait trait: dataSet.getTraits())
		{
			combo1.addItem(trait.getName());
			combo2.addItem(trait.getName());
			combo3.addItem(trait.getName());
		}

		checkAssign.setSelected(Prefs.guiAssignTraits);
	}

	boolean isOK()
	{
		Prefs.guiAssignTraits = checkAssign.isSelected();

		return true;
	}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        panel1 = new javax.swing.JPanel();
        combo1 = new javax.swing.JComboBox();
        rAsc1 = new javax.swing.JRadioButton();
        rDes1 = new javax.swing.JRadioButton();
        panel2 = new javax.swing.JPanel();
        combo2 = new javax.swing.JComboBox();
        rAsc2 = new javax.swing.JRadioButton();
        rDes2 = new javax.swing.JRadioButton();
        panel3 = new javax.swing.JPanel();
        combo3 = new javax.swing.JComboBox();
        rAsc3 = new javax.swing.JRadioButton();
        rDes3 = new javax.swing.JRadioButton();
        checkAssign = new javax.swing.JCheckBox();

        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Sort on this trait first:"));

        buttonGroup1.add(rAsc1);
        rAsc1.setSelected(true);
        rAsc1.setText("Ascending");

        buttonGroup1.add(rDes1);
        rDes1.setText("Descending");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo1, 0, 246, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rDes1)
                    .addComponent(rAsc1))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rAsc1)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rDes1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Then on this trait second:"));

        buttonGroup2.add(rAsc2);
        rAsc2.setSelected(true);
        rAsc2.setText("Ascending");

        buttonGroup2.add(rDes2);
        rDes2.setText("Descending");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo2, 0, 246, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rDes2)
                    .addComponent(rAsc2))
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rAsc2)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rDes2)
                .addContainerGap())
        );

        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder("And finally on this trait:"));

        buttonGroup3.add(rAsc3);
        rAsc3.setSelected(true);
        rAsc3.setText("Ascending");

        buttonGroup3.add(rDes3);
        rDes3.setText("Descending");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(combo3, 0, 246, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rDes3)
                    .addComponent(rAsc3))
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rAsc3)
                    .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rDes3)
                .addContainerGap())
        );

        checkAssign.setText("Auto assign these traits to the traits heatmap once the sort is completed");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkAssign)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkAssign)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox checkAssign;
    javax.swing.JComboBox combo1;
    javax.swing.JComboBox combo2;
    javax.swing.JComboBox combo3;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    javax.swing.JRadioButton rAsc1;
    javax.swing.JRadioButton rAsc2;
    javax.swing.JRadioButton rAsc3;
    javax.swing.JRadioButton rDes1;
    javax.swing.JRadioButton rDes2;
    javax.swing.JRadioButton rDes3;
    // End of variables declaration//GEN-END:variables
}