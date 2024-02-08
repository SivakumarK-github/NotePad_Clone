import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Clone extends JFrame implements ActionListener, WindowListener {
    JTextArea jta = new JTextArea();
    File fnameContainer;

    public Clone() {
        Font font = new Font("Arial", Font.PLAIN, 15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");

        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jta.setFont(font);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        con.add(sbrText);

        createMenuItem(jmfile, "New");
        createMenuItem(jmfile, "Open");
        createMenuItem(jmfile, "Save");
        jmfile.addSeparator();
        createMenuItem(jmfile, "Exit");

        createMenuItem(jmedit, "Cut");
        createMenuItem(jmedit, "Copy");
        createMenuItem(jmedit, "Paste");

        createMenuItem(jmhelp, "About");

        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmhelp);

        setJMenuBar(jmb);

        setIconImage(Toolkit.getDefaultToolkit().getImage("Notepad"));
        addWindowListener(this);
        setSize(500, 500);
        setTitle("Untitled.txt-Notepad");
        setVisible(true);
    }

    public void createMenuItem(JMenu jm, String txt) {
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        jm.add(jmi);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        // MENUBAR-new, open, save
        if (e.getActionCommand().equals("New")) {
            this.setTitle("Untitled.txt-Notepad");
            jta.setText("");
            fnameContainer = null;
        } else if (e.getActionCommand().equals("Open")) {
            int ret = jfc.showDialog(null, "Open");
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    File fyl = jfc.getSelectedFile();
                    OpenFile(fyl.getAbsolutePath());
                    this.setTitle(fyl.getName() + "-Notepad");
                    fnameContainer = fyl;
                } catch (IOException ex) {
                }
            } else if (e.getActionCommand().equals("Save")) {
                if (fnameContainer != null) {
                    jfc.setCurrentDirectory(fnameContainer);
                    jfc.setSelectedFile(fnameContainer);
                } else {
                    jfc.setSelectedFile(new File("Untitled.txt"));
                }
                int rett = jfc.showSaveDialog(this);
                if (rett == JFileChooser.APPROVE_OPTION) {
                    try {
                        File fyl = jfc.getSelectedFile();
                        SaveFile(fyl.getAbsolutePath());
                        this.setTitle(getTitle(fyl.getName() + " - Notepad"));
                        fnameContainer = fyl;
                    } catch (Exception ex) {
                    }
                }
            }
            // EDIT------copy,cut,paste
            else if (e.getActionCommand().equals("Exit")) {
                Exiting();
            } else if (e.getActionCommand().equals("Copy")) {
                jta.copy();
            } else if (e.getActionCommand().equals("Paste")) {
                jta.paste();
            } else if (e.getActionCommand().equals("About")) {
                JOptionPane.showMessageDialog(this, "Created by: Sivakumar K", "Notepad",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getActionCommand().equals("Cut")) {
                jta.cut();
            }
        }
    }

    private String getTitle(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    public void OpenFile(String fname) throws IOException {
        BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
        String l;
        jta.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while ((l = d.readLine()) != null) {
            jta.setText(jta.getText() + l + "\r\n");
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        d.close();
    }

    public void SaveFile(String fname) throws IOException {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream a = new DataOutputStream(new FileOutputStream(fname));
        a.writeBytes(jta.getText());
        a.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void windowClosed(WindowEvent ex) {
    }

    public void windowActivated(WindowEvent ex) {
    }

    public void windowDeiconified(WindowEvent ex) {
    }

    public void windowIconified(WindowEvent ex) {
    }

    public void windowOpened(WindowEvent ex) {
    }

    public void windowClosing(WindowEvent ex) {
        Exiting();
    }

    public void windowDeactivated(WindowEvent ex) {
    }

    public void Exiting() {
        System.exit(0);
    }
}