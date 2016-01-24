import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class Tab {

	JTextArea textArea, linesTestarea;
	JScrollPane scrollPane;
	JTabbedPane tabbedPane;
	JLabel label;
	final JButton button = new JButton("x");;
	boolean newFile;
	String filename, filepath;
	JPanel panel;

	public Tab(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
		newFile = true;
		filename = "untitled.txt";
	}

	public Tab addTab(boolean newBlankTab) {

		filepath = new File(this.filename).getAbsolutePath();
		if (newBlankTab)
			StaticVariables.openedfiles.add(filepath);
		textArea = new JTextArea();

		setLineNumberProperty();

		textArea.setFont(new Font("Serif", Font.PLAIN, StaticVariables.fontSize));
		scrollPane = new JScrollPane();
		tabbedPane.addTab(null, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1,
				addPaneltoTabs(new JPanel()));
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		tabbedPane.setBackgroundAt(tabbedPane.getSelectedIndex(),
				Color.lightGray);

		textArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				linesTestarea.setText(showLineNumber(textArea));
				setTabTitle();
				if (StaticVariables.unsavedfiles.contains(getFilePath()) == false) {
					for (String a : StaticVariables.unsavedfiles)
						System.out.println("removeupdate unsavedfiles " + a);
					System.out.println("removeupdate " + getFilePath());
					StaticVariables.unsavedfiles.add(getFilePath());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				linesTestarea.setText(showLineNumber(textArea));
				if (StaticVariables.unsavedfiles.contains(getFilePath()) == false) {
						setTabTitle();
						StaticVariables.unsavedfiles.add(getFilePath());
				}
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				linesTestarea.setText(showLineNumber(textArea));
				setTabTitle();
				if (StaticVariables.unsavedfiles.contains(getFilePath()) == false) {
					for (String a : StaticVariables.unsavedfiles)
						System.out.println("changeupdate unsavedfiles " + a);
					System.out.println("changeupdate " + getFilePath());
					StaticVariables.unsavedfiles.add(getFilePath());
				}
			}
		});
		scrollPane.setViewportView(textArea);
		scrollPane.setRowHeaderView(linesTestarea);
		return this;
	}

	public JPanel addPaneltoTabs(JPanel panel) {
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.setOpaque(false);

		label = new JLabel(filename);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		panel.add(label);

		setTabButtonProperty();
		panel.add(button);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
						new Color(0.200f, 0.123f, 0.123f, .2f)));
				button.setFont(StaticVariables.btnEnteredFont);
				button.setForeground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				button.setBorder(BorderFactory.createEmptyBorder());
				button.setFont(StaticVariables.btnExitedFont);
				button.setForeground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent e) {
				for (String a : StaticVariables.unsavedfiles) {
					System.out.println("mouseclicked " + a);
				}

				if (StaticVariables.unsavedfiles.contains(getFilePath())) {
					String activeFilename = new File(getFilePath()).getName();
					int opt = JOptionPane.showOptionDialog(null, activeFilename
							+ " is not saved", "Sodalime Save files alert",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							(Object[]) obj(), null);
					if (opt == 0) {

					} else if (opt == 1) {
						StaticVariables.unsavedfiles.remove(getFilePath());
						for(int a=0;a<StaticVariables.openedfiles.size();a++){
							System.out.println(a+":"+StaticVariables.openedfiles.get(a));
						}
						removeTabs();
					} else {
						Tab temp = StaticVariables.tablist.get(tabbedPane
								.getSelectedIndex());
						FileAction fa = new FileAction();
						fa.FileSaveaction(temp);
						System.out.println(StaticVariables.unsavedfiles.size());
						StaticVariables.unsavedfiles.remove(tabbedPane
								.getSelectedIndex());
						System.out.println(StaticVariables.unsavedfiles.size());
						removeTabs();
					}
				} else {
					removeTabs();
				}

			}
		});
		return panel;
	}

	public void showTabList() {
		JPanel tabPanel = new JPanel();
		tabPanel.setSize(200, StaticVariables.tablist.size() * 10);
	}

	public String showLineNumber(JTextArea textArea) {
		int caretPosition = textArea.getDocument().getLength();
		Element root = textArea.getDocument().getDefaultRootElement();
		String text = "1" + System.getProperty("line.separator");
		for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
			text += i + System.getProperty("line.separator");
		}
		return text;
	}

	public void setTabButtonProperty() {
		button.setOpaque(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setPreferredSize(new Dimension(20, 20));
		button.setToolTipText("close this tab");
		button.setFocusable(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setForeground(Color.WHITE);
	}

	public void setLineNumberProperty() {
		Dimension d = new Dimension(20, textArea.getHeight());
		linesTestarea = new JTextArea("1");
		linesTestarea.setPreferredSize(d);
		linesTestarea.setBorder(BorderFactory.createEmptyBorder(7, 0, 0, 1));
		linesTestarea.setEditable(false);
		linesTestarea.setFont(new Font("Serif", Font.ITALIC,
				StaticVariables.fontSize));
		linesTestarea.setForeground(StaticVariables.linesColor);
	}

	public Object obj() {
		String options[] = { "Cancel", "close anyway", "Save and close" };
		return options;
	}

	public void removeTabs() {
		try {
			StaticVariables.openedfiles.remove(getFilePath());
			StaticVariables.tablist.remove(tabbedPane.getSelectedIndex());
			tabbedPane.remove(tabbedPane.getSelectedIndex());
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	public void setTabTitle() {
		this.label.setFont(StaticVariables.tabUnsavedTitleFont);
	}

	public String getFilePath() {
		return StaticVariables.openedfiles.get(tabbedPane.getSelectedIndex());
	}
}
