package flapjack.gui.dialog;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import flapjack.gui.*;

import scri.commons.gui.*;

public class NBDataImportPanel extends javax.swing.JPanel implements ActionListener
{
	public NBDataImportPanel()
	{
		initComponents();

		setBackground((Color)UIManager.get("fjDialogBG"));
		filePanel.setBackground((Color)UIManager.get("fjDialogBG"));
		optionPanel.setBackground((Color)UIManager.get("fjDialogBG"));

		mapButton.addActionListener(this);
		genoButton.addActionListener(this);
		checkUseHetSep.addActionListener(this);

		mapText.setText(Prefs.guiCurrentMap);
		mapText.setCaretPosition(0);
		genoText.setText(Prefs.guiCurrentGeno);
		genoText.setCaretPosition(0);

		missingText.setText(Prefs.ioMissingData);
		heteroText.setText(Prefs.ioHeteroSeparator);
		checkHetero.setSelected(Prefs.ioHeteroCollapse);
		checkUseHetSep.setSelected(Prefs.ioUseHetSep);

		// Apply localized text
		filePanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.NBDataImportPanel.filePanel")));
		RB.setText(mapLabel, "gui.dialog.NBDataImportPanel.mapLabel");
		mapButton.setText(RB.getString("gui.text.browse"));
		RB.setText(genoLabel, "gui.dialog.NBDataImportPanel.genoLabel");
		genoButton.setText(RB.getString("gui.text.browse"));
		optionPanel.setBorder(BorderFactory.createTitledBorder(RB.getString("gui.dialog.NBDataImportPanel.optionPanel")));
		RB.setText(checkUseHetSep, "gui.dialog.NBDataImportPanel.checkUseHetSep");
		RB.setText(missingLabel, "gui.dialog.NBDataImportPanel.missingLabel");
		RB.setText(heteroLabel, "gui.dialog.NBDataImportPanel.heteroLabel");
		RB.setText(checkHetero, "gui.dialog.NBDataImportPanel.checkHetero");
		
		setLabelStates();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == mapButton)
			browse(mapText);

		else if (e.getSource() == genoButton)
			browse(genoText);
	
		else if (e.getSource() == checkUseHetSep)
			setLabelStates();
	}

	private void browse(JTextField textfield)
	{
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle(RB.getString("gui.dialog.NBDataImportPanel.fcTitle"));
		fc.setCurrentDirectory(new File(Prefs.guiCurrentDir));

		if (textfield.getText().length() > 0)
			fc.setCurrentDirectory(new File(textfield.getText()));

//		Filters.setFilters(fc, -1, FAS, PHY_S, PHY_I, ALN, MSF, NEX, NEX_B);

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();
			Prefs.guiCurrentDir = fc.getCurrentDirectory().toString();

			textfield.setText(file.toString());
		}
	}

	boolean isOK()
	{
		if (mapText.getText().length() == 0 || genoText.getText().length() == 0)
		{
			TaskDialog.warning(
				RB.getString("gui.dialog.NBDataImportPanel.warn1"),
				RB.getString("gui.text.ok"));
			return false;
		}

		Prefs.ioMissingData = missingText.getText();
		Prefs.ioHeteroSeparator = heteroText.getText();
		Prefs.ioHeteroCollapse = checkHetero.isSelected();
		Prefs.ioUseHetSep = checkUseHetSep.isSelected();

		return true;
	}

	File getMapFile()
	{
		Prefs.guiCurrentMap = mapText.getText();
		return new File(mapText.getText());
	}

	File getGenotypeFile()
	{
		Prefs.guiCurrentGeno = genoText.getText();
		return new File(genoText.getText());
	}
	
	private void setLabelStates()
	{
		heteroLabel.setEnabled(checkUseHetSep.isSelected());
		heteroText.setEnabled(checkUseHetSep.isSelected());
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filePanel = new javax.swing.JPanel();
        mapLabel = new javax.swing.JLabel();
        mapText = new javax.swing.JTextField();
        mapButton = new javax.swing.JButton();
        genoLabel = new javax.swing.JLabel();
        genoText = new javax.swing.JTextField();
        genoButton = new javax.swing.JButton();
        optionPanel = new javax.swing.JPanel();
        heteroLabel = new javax.swing.JLabel();
        heteroText = new javax.swing.JTextField();
        checkHetero = new javax.swing.JCheckBox();
        missingLabel = new javax.swing.JLabel();
        missingText = new javax.swing.JTextField();
        checkUseHetSep = new javax.swing.JCheckBox();

        filePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data files to import:"));

        mapLabel.setLabelFor(mapText);
        mapLabel.setText("Map file:");

        mapButton.setText("Browse...");

        genoLabel.setLabelFor(genoText);
        genoLabel.setText("Genotype file:");

        genoButton.setText("Browse...");

        org.jdesktop.layout.GroupLayout filePanelLayout = new org.jdesktop.layout.GroupLayout(filePanel);
        filePanel.setLayout(filePanelLayout);
        filePanelLayout.setHorizontalGroup(
            filePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(filePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(filePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(genoLabel)
                    .add(mapLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(filePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(filePanelLayout.createSequentialGroup()
                        .add(mapText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(mapButton))
                    .add(filePanelLayout.createSequentialGroup()
                        .add(genoText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(genoButton)))
                .addContainerGap())
        );
        filePanelLayout.setVerticalGroup(
            filePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(filePanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(filePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(mapLabel)
                    .add(mapButton)
                    .add(mapText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(filePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(genoLabel)
                    .add(genoButton)
                    .add(genoText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        optionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Additional options:"));

        heteroLabel.setLabelFor(heteroText);
        heteroLabel.setText("Heterozygous separator string:");

        heteroText.setColumns(4);

        checkHetero.setText("Don't distinguish between heterozyous alleles (treats A/T the same as T/A)");

        missingLabel.setLabelFor(missingText);
        missingLabel.setText("Missing data string:");

        missingText.setColumns(4);

        checkUseHetSep.setText("Expect heteozygotes to be separated by a string (A/T rather than AT)");

        org.jdesktop.layout.GroupLayout optionPanelLayout = new org.jdesktop.layout.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(checkUseHetSep)
                    .add(checkHetero)
                    .add(optionPanelLayout.createSequentialGroup()
                        .add(optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(heteroLabel)
                            .add(missingLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(missingText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(heteroText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(checkHetero)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(checkUseHetSep)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(heteroLabel)
                    .add(heteroText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(optionPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(missingLabel)
                    .add(missingText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, optionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, filePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(filePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(optionPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkHetero;
    private javax.swing.JCheckBox checkUseHetSep;
    private javax.swing.JPanel filePanel;
    private javax.swing.JButton genoButton;
    private javax.swing.JLabel genoLabel;
    private javax.swing.JTextField genoText;
    private javax.swing.JLabel heteroLabel;
    private javax.swing.JTextField heteroText;
    private javax.swing.JButton mapButton;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JTextField mapText;
    private javax.swing.JLabel missingLabel;
    private javax.swing.JTextField missingText;
    private javax.swing.JPanel optionPanel;
    // End of variables declaration//GEN-END:variables

}