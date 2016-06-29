package jhi.flapjack.gui.mabc;

import jhi.flapjack.gui.table.*;

import scri.commons.gui.*;

public class MabcPanelNB extends javax.swing.JPanel
{
	public MabcPanelNB(MabcPanel panel)
	{
		initComponents();

		bFilter.addActionListener(panel);
		bFilter.setIcon(Icons.getIcon("FILTER"));

		bSort.addActionListener(panel);
		bSort.setIcon(Icons.getIcon("SORT"));

		bExport.addActionListener(panel);
		bExport.setIcon(Icons.getIcon("EXPORTTRAITS"));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new LineDataTable();
        coverageLabel = new javax.swing.JLabel();
        bExport = new javax.swing.JButton();
        bSort = new javax.swing.JButton();
        bFilter = new javax.swing.JButton();

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {

            }
        ));
        jScrollPane1.setViewportView(table);

        coverageLabel.setText("RPP Coverage:");

        bExport.setText("Export");

        bSort.setText("Sort...");

        bFilter.setText("Filter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coverageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bExport)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coverageLabel)
                    .addComponent(bExport)
                    .addComponent(bSort)
                    .addComponent(bFilter))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton bExport;
    javax.swing.JButton bFilter;
    javax.swing.JButton bSort;
    javax.swing.JLabel coverageLabel;
    private javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
