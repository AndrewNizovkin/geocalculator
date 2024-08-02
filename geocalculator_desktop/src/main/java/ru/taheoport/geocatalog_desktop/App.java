package ru.taheoport.geocatalog_desktop;

import ru.taheoport.geocatalog_desktop.gui.MainWin;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWin();
            }
        });
    }
}
