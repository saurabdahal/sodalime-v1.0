import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class SodalimeFileTree {

	private JTree tree;
	DefaultMutableTreeNode fileRootDir;

	// JTabbedPane tabbedPane;
	public SodalimeFileTree() {

	}

	public JTree showTree(DefaultMutableTreeNode parentNode, File selectedFile,
			final JTabbedPane tabbedPane) {
		// this.tabbedPane=tabbedPane;
		String filePath = selectedFile.getPath();
		DefaultMutableTreeNode currDir = new DefaultMutableTreeNode(filePath);
		if (parentNode != null) {
			parentNode.add(currDir);
		}
		Vector<String> tmpfilesList, fileList;
		tmpfilesList = new Vector<String>();
		fileList = new Vector<String>();
		String temp[] = selectedFile.list();
		File file;
		for (int i = 0; i < temp.length; i++) {
			tmpfilesList.addElement(temp[i]);
		}
		Collections.sort(tmpfilesList, String.CASE_INSENSITIVE_ORDER);

		for (int i = 0; i < tmpfilesList.size(); i++) {
			String fileString = (String) tmpfilesList.elementAt(i);
			String newPath;
			if (filePath.equals(selectedFile)) {
				newPath = filePath;
			} else {
				newPath = fileString;
			}
			if ((file = new File(newPath)).isDirectory()) {
				showTree(currDir, file, null);
			} else {
				fileList.addElement(fileString);
			}
		}
		for (int fnum = 0; fnum < fileList.size(); fnum++) {
			currDir.add(new DefaultMutableTreeNode(fileList.elementAt(fnum)));
		}
		tree = new JTree(currDir);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					System.out.println(e.getClickCount());
					TreePath c = tree.getSelectionPath(); // [E:\Sodalime, src,
															// BasicLayout.java]
					String a = "";
					for (int i = 0; i < c.getPathCount(); i++) {
						a += c.getPathComponent(i) + File.separator;
					}
					if (new File(a).isDirectory() == false) {
						System.out.println(a);
						FileAction fa = new FileAction();
						fa.FileOpenTab(new Tab(tabbedPane), a);
					} else
						System.out.println(a + " is a directory"); 
					
					e.consume();
				}
			}
		});
		return tree;
	}

	// DefaultMutableTreeNode node =
	// (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

}
