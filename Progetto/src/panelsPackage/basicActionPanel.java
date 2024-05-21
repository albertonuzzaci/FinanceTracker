package panelsPackage;

import actionListenerPackage.basicAction.*;
import utilityPackage.tableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che mette a disposizione le funzioni di base dell'applicazione
 * \n - aggiunta voci
 * \n - modifica voci
 * \n - eliminazione voci
 */
public class basicActionPanel extends JPanel {
    /**
     * Costruttore della classe
     * @param t tabella
     * @param tm table model
     * @param saldoTot label che mostra il saldo delle voci visualizzate
     * @param vmode modalità di visualizzazione (All, Year, Month, Day, Week, Period)
     */
    public basicActionPanel(JTable t, tableModel tm, JLabel saldoTot, JComboBox vmode){
        JButton addButton = new JButton(this.returnFormattedIcon("/Images/plus.png"));
        addButton.addActionListener(new AddActionListener(tm, saldoTot));
        addButton.setToolTipText("Add");
        JButton modifyButton = new JButton(this.returnFormattedIcon("/Images/edit.png"));
        modifyButton.addActionListener(new ModifyActionListener(tm, t, saldoTot, vmode));
        modifyButton.setToolTipText("Edit");
        JButton deleteButton = new JButton(this.returnFormattedIcon("/Images/trashcan.png"));
        deleteButton.addActionListener(new DeleteActionListener(tm, t, saldoTot, vmode));
        deleteButton.setToolTipText("Delete");

        JPanel addPanel = new JPanel();
        JPanel modifyPanel = new JPanel();
        JPanel deletePanel = new JPanel();

        addPanel.add(addButton);
        modifyPanel.add(modifyButton);
        deletePanel.add(deleteButton);

        JPanel totalButtonPanel = new JPanel();
        totalButtonPanel.setLayout(new BorderLayout());

        totalButtonPanel.add(addPanel, BorderLayout.EAST);
        totalButtonPanel.add(modifyPanel, BorderLayout.CENTER);
        totalButtonPanel.add(deletePanel, BorderLayout.WEST);
        this.setLayout(new GridBagLayout());
        this.add(totalButtonPanel);
    }

    /**
     * Funzione per la formattazione delle icone dei pulsanti
     * @param filename percorso dell'icona
     * @return icona formattata
     */
    public ImageIcon returnFormattedIcon(String filename){
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(filename));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }
}
