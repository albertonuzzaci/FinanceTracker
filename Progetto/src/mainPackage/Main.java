package mainPackage;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

import menuPackage.finalMenu;
import utilityPackage.backupThread;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        setUIFont(new FontUIResource(new Font("Comic Sans MS", 0, 15)));

        JFrame f = new JFrame();

        mainPanel mP = new mainPanel();
        f.add(mP);

        finalMenu fMenu = new finalMenu(mP.getTableModel(), mP.getTable(), mP.getTotalBalance());
        f.setJMenuBar(fMenu);

        backupThread buThread = new backupThread(mP.getTableModel());
        buThread.start();

        f.setPreferredSize(new Dimension(900,800));

        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    /**
     * Funzione che setta un font come font di sistema
     * @param f font di sistema
     */
    public static void setUIFont(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

}