package ru.taheoport.geocatalog_desktop.gui;

import javax.swing.*;
import java.awt.*;

public class MainWin extends JFrame {
    private JMenuBar mbr;
    private JMenu mTasks, mHelp;
    private JMenuItem miReverseTack,
            miForwardTask,
            miResection,
            miAbout,
            miHelp;

    /**
     * Constructor
     * @throws HeadlessException
     */
    public MainWin() throws HeadlessException {
        super("Geocalculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int widthMainWin = 300;
        int heightMainWin = 600;
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - widthMainWin) / 2,
                (screenSize.height - heightMainWin) / 2,
                widthMainWin,
                heightMainWin);
        setResizable(true);

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);


        createPanel();

        setVisible(true);
    }

    /**
     * Creates Content Pane
     */
    private void createPanel() {

        //region Menu

        mTasks = new JMenu("Задачи");
        miForwardTask = new JMenuItem("Прямая геодезическая задача");
        miReverseTack = new JMenuItem("Обратная геодезическая задача");
        miResection = new JMenuItem("Обратная геодезическая засечка");

        mTasks.add(miForwardTask);
        mTasks.add(miReverseTack);
        mTasks.add(miResection);

        mHelp = new JMenu("Помощь");
        miAbout = new JMenuItem("О программе");
        miHelp = new JMenuItem("Справка");

        mHelp.add(miAbout);
        mHelp.add(miHelp);

        mbr = new JMenuBar();
        mbr.add(mTasks);
        mbr.add(mHelp);

        setJMenuBar(mbr);

        //endregion


    }
}
