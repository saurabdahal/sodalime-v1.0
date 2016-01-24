 
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class FileAction {

    static String filepath, filename;
    String line = null;
    String NewFileName = "Untitled";
    JFileChooser fc = new JFileChooser();
    JTabbedPane tb = new JTabbedPane();
    int fileChoice;

    public FileAction() {

    }

    public void FileOpenaction(Tab ta) {

        fc.setDialogTitle("Sodalime Open Files");
        fileChoice = fc.showOpenDialog(fc);
        ta.newFile = false;
        if (fileChoice == JFileChooser.APPROVE_OPTION) {
            filepath = fc.getSelectedFile().getAbsolutePath();
            StaticVariables.openedfiles.add(filepath);
            System.out.println("open " + filepath);
            FileOpenTab(ta, filepath);
        }
    }

    public void FileOpenFolderaction(JScrollPane scp, JPanel mpanel,
            JTabbedPane tabbedPane) {
        fc.setDialogTitle("Sodalime open Folder");
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File file = null;
        fileChoice = fc.showOpenDialog(fc);
        if (fileChoice == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
        }

        JPanel panel = new JPanel();
        panel.add(new SodalimeFileTree().showTree(null, file, tabbedPane));
        mpanel.add(panel);
        System.out.println(mpanel.getPreferredSize());
        System.out.println(mpanel.getComponentCount());
        scp.setViewportView(mpanel);
    }

    public void FileOpenTab(Tab ta, String filepath) {

        if (StaticVariables.tablist.contains(ta) == false) {
        	ta.newFile = false;
            File file = new File(filepath);
            StaticVariables.tablist.add(ta.addTab(false));
            StaticVariables.openedfiles.add(filepath);
            ta.textArea.setLineWrap(true);
            ta.textArea.setWrapStyleWord(true);
            ta.textArea.setText(" ");
            filename = file.getName();
            ta.label.setText(filename);
            Path path = Paths.get(filepath);
            try {
                Scanner sc = new Scanner(path);
                String newLine = System.getProperty("line.separator");
                while (sc.hasNextLine()) {
                	ta.textArea.append(sc.nextLine() + newLine);
                }
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ta.tabbedPane.setSelectedIndex(getIndexOfTab(filepath, ta));
        }
    }

    public void FileSaveaction(Tab ta) {

        if (ta.newFile == true) {
            fc.setDialogTitle("Sodalime Save Files");
            fileChoice = fc.showSaveDialog(fc);
            if (fileChoice == JFileChooser.APPROVE_OPTION) {
                ta.newFile = false;
                filepath = fc.getSelectedFile().getAbsolutePath();
                StaticVariables.openedfiles.set(ta.tabbedPane.getSelectedIndex(), filepath);
                File userFile = new File(filepath);
                filename = userFile.getName().contains(".") ? userFile.getName() : userFile.getName() + ".txt";
                File newFile =new File(userFile.getParent()+File.separator+filename);
                if(userFile.renameTo(newFile)) System.out.println("success renaming");
                else System.out.println("failed renaming");
                System.out.println(new File(userFile.getParent()+File.separator+filename));
                ta.label.setText(filename);
                try {
                    if (StaticVariables.unsavedfiles.contains(ta.getFilePath())) {
                        ta.label.setFont(StaticVariables.tabSavedTitleFont);
                        StaticVariables.unsavedfiles.remove(ta.getFilePath());
                    }
                    PrintWriter fw = new PrintWriter(filepath);
                    fw.write(ta.textArea.getText());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        } else {
            try {
                if (StaticVariables.unsavedfiles.contains(ta.getFilePath())) {
                    ta.label.setFont(StaticVariables.tabSavedTitleFont);
                    StaticVariables.unsavedfiles.remove(ta.getFilePath());
                    PrintWriter fw = new PrintWriter(ta.getFilePath());
                    fw.write(ta.textArea.getText());
                    fw.close();
                }else{
                	 ta.label.setFont(StaticVariables.tabSavedTitleFont);
                }
                
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void FileSaveAsaction(Tab ta) {
        fc.setDialogTitle("Sodalime Save As Files");
        fileChoice = fc.showSaveDialog(fc);
        if (fileChoice == JFileChooser.APPROVE_OPTION) {
            filepath = fc.getSelectedFile().getAbsolutePath();
            File userFile = new File(filepath);
            filename = userFile.getName().contains(".") ? userFile.getName() : userFile.getName() + ".txt";
            try {
                if (StaticVariables.unsavedfiles.contains(ta.getFilePath())) {
                    ta.label.setFont(StaticVariables.tabSavedTitleFont);
                    StaticVariables.unsavedfiles.remove(ta.getFilePath());
                }
                PrintWriter fw = new PrintWriter(filepath);
                fw.write(ta.textArea.getText());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void FileQuitaction() {
        System.exit(0);
    }

    public int getIndexOfTab(String filepath, Tab ta) {
        if (StaticVariables.tablist.contains(ta)) {
            return StaticVariables.tablist.indexOf(filepath);
        } else {
            return -1;
        }
    }

}
