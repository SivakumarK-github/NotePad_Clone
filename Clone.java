import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Clone extends JFrame implements ActionListener, WindowListener{
    JTextArea jta = new JTextArea();
    File fnameContainer;

    public Clone(){
        Font int = new Font("Ariel", Font.PLAIN, 15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");

        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbr.setVisible(true);
        jta.setFont(int);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        con.add(sbrText);

        createMenuItem(jmfile,"New");
        createMenuItem(jmfile,"Open");
        createMenuItem(jmfile,"Save");
        jmfile.addSeparator();
        createMenuItem(jmfile,"Exit");

        createMenuItem(jmedit,"Cut");
        createMenuItem(jmedit,"Copy");
        createMenuItem(jmedit,"Paste");

    }
    
}
