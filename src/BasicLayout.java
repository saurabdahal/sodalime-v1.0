import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JToggleButton;

public class BasicLayout {

	JFrame frame;
	JSplitPane splitPane;
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	JPanel mainPanel, menubarPanel, iconPanel;
	JLabel newlabel, savelabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					BasicLayout window = new BasicLayout();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BasicLayout() {
		frame = new JFrame("Sodalime V1.0");
		frame.getContentPane().setBackground(Color.WHITE);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(StaticVariables.initialXcoordinates,
				StaticVariables.initialYcoordinates, StaticVariables.minWidth,
				StaticVariables.minHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.NORTH);
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		mainPanel.setBounds(0, 0, StaticVariables.minWidth,
				StaticVariables.menubarHeight);

		menubarPanel = new JPanel();
		menubarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 5));
		menubarPanel.setBounds(0, 0, StaticVariables.minWidth, 30);
		menubarPanel.setBackground(new Color(238, 238, 238));
		mainPanel.add(menubarPanel, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(240,
				248, 255), null));
		menuBar.setBorderPainted(false);
		menuBar.setOpaque(false);
		menubarPanel.add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenu FInewMenu = new JMenu("New");
		mnFile.add(FInewMenu);
		JMenuItem FInew = new JMenuItem("New file");
		FInewMenu.add(FInew);
		JMenu FInewProject = new JMenu("New Project");
		FInewMenu.add(FInewProject);
		JMenuItem FInewJavaProject = new JMenuItem("Java");
		FInewProject.add(FInewJavaProject);
		JMenuItem FInewPythonProject = new JMenuItem("Python");
		FInewProject.add(FInewPythonProject);
		JMenuItem FInewHtmlProject = new JMenuItem("Html");
		FInewProject.add(FInewHtmlProject);

		FInew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		FInew.setMnemonic(KeyEvent.VK_N);

		JMenuItem FIopen = new JMenuItem("Open FIle");
		mnFile.add(FIopen);
		JMenuItem FIopenFolder = new JMenuItem("Open Folder");
		mnFile.add(FIopenFolder);
		FIopen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		FIopen.setMnemonic(KeyEvent.VK_O);

		JMenuItem FIsave = new JMenuItem("Save");
		mnFile.add(FIsave);
		FIsave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		FIsave.setMnemonic(KeyEvent.VK_S);

		JMenuItem FIsaveAs = new JMenuItem("Save As ...");
		mnFile.add(FIsaveAs);
		FIsaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.SHIFT_MASK));

		mnFile.addSeparator();

		JMenuItem quit = new JMenuItem("quit");
		mnFile.add(quit);
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
				ActionEvent.CTRL_MASK));
		quit.setMnemonic(KeyEvent.VK_Q);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem EdCut = new JMenuItem("Cut");
		mnEdit.add(EdCut);
		EdCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));

		JMenuItem EdCopy = new JMenuItem("Copy");
		mnEdit.add(EdCopy);
		EdCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));

		JMenuItem EdPaste = new JMenuItem("Paste");
		mnEdit.add(EdPaste);
		EdPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));

		iconPanel = new JPanel();
		iconPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null,
				null, new Color(255, 235, 205), null));
		mainPanel.add(iconPanel, BorderLayout.SOUTH);
		iconPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		iconPanel.setBounds(0, 30, StaticVariables.minWidth, 40);
		iconPanel.setBackground(new Color(238, 238, 238));

		newlabel = new JLabel();
		newlabel.setToolTipText("create new file");
		newlabel.setIcon(new ImageIcon(BasicLayout.class
				.getResource("/icons/newFile.png")));
		newlabel.setPreferredSize(new Dimension(newlabel.getIcon()
				.getIconWidth() + 6, newlabel.getIcon().getIconHeight() + 6));
		iconPanel.add(newlabel);

		savelabel = new JLabel();
		savelabel.setToolTipText("save file");
		savelabel.setIcon(new ImageIcon(BasicLayout.class
				.getResource("/icons/saveFile.png")));
		savelabel.setPreferredSize(new Dimension(savelabel.getIcon()
				.getIconWidth()+6, savelabel.getIcon().getIconHeight()+6));
		System.out.println(savelabel.getIcon()
				.getIconWidth()+" "+savelabel.getIcon().getIconHeight());
		savelabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		iconPanel.add(savelabel);
		
		JToggleButton toggleMacro = new JToggleButton("macro");
		iconPanel.add(toggleMacro);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		splitPane.setResizeWeight(0.125);
		final JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(0, 1, 0, 0));
		final JScrollPane scp = new JScrollPane();
		splitPane.setLeftComponent(scp);
		splitPane.setRightComponent(tabbedPane);
		tabbedPane.setOpaque(false);
		tabbedPane.setTabLayoutPolicy(1);

		// FileAction starts from here
		// --------------------------------------------
		FInew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tab ta = new Tab(tabbedPane);
				StaticVariables.tablist.add(ta.addTab(true));
				System.out.println("finew " + StaticVariables.tablist.size());
			}
		});
		newlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				newlabel.setBackground(new Color(0.123f, 0.123f, 0.123f, .5f));
				Border border = BorderFactory.createMatteBorder(1, 1, 1, 1,
						new Color(0.123f, 0.123f, 0.123f, .4f));
				Border paddingBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
				newlabel.setBorder(BorderFactory.createCompoundBorder(border,
						paddingBorder));
			}

			public void mouseExited(MouseEvent e) {
				newlabel.setOpaque(false);
				newlabel.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
			}

			public void mouseClicked(MouseEvent e) {
				Tab ta = new Tab(tabbedPane);
				StaticVariables.tablist.add(ta.addTab(true));
			}
		});
		savelabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				savelabel.setBackground(new Color(0.123f, 0.123f, 0.123f, .5f));
				Border border = BorderFactory.createMatteBorder(1, 1, 1, 1,
						new Color(0.123f, 0.123f, 0.123f, .4f));
				Border paddingBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);
				savelabel.setBorder(BorderFactory.createCompoundBorder(border,
						paddingBorder));
			}

			public void mouseExited(MouseEvent e) {
				savelabel.setOpaque(false);
				savelabel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
			}

			public void mouseClicked(MouseEvent e) {
				Tab temp = StaticVariables.tablist.get(tabbedPane
						.getSelectedIndex());
				FileAction fa = new FileAction();
				fa.FileSaveaction(temp);
			}
		});

		FIopen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tab ta = new Tab(tabbedPane);
				FileAction fa = new FileAction();
				fa.FileOpenaction(ta);
			}
		});
		FIopenFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileAction fa = new FileAction();
				fa.FileOpenFolderaction(scp, pane, tabbedPane);
			}
		});
		FIsave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tab temp = StaticVariables.tablist.get(tabbedPane
						.getSelectedIndex());
				FileAction fa = new FileAction();
				fa.FileSaveaction(temp);
			}
		});
		FIsaveAs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Tab temp = StaticVariables.tablist.get(tabbedPane
						.getSelectedIndex());
				FileAction fa = new FileAction();
				fa.FileSaveAsaction(temp);
			}
		});
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FileAction fa = new FileAction();
				fa.FileQuitaction();
			}
		});
		// Edit Action starts from here
		// -----------------------------------------------------------
		EdCut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EditAction ea = new EditAction();
				Tab temp = StaticVariables.tablist.get(tabbedPane
						.getSelectedIndex());
				ea.EditCutAction(temp);
			}
		});
		EdCopy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EditAction ea = new EditAction();
				Tab temp = StaticVariables.tablist.get(tabbedPane
						.getSelectedIndex());
				ea.EditCopyAction(temp);
			}
		});
		EdPaste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				EditAction ea = new EditAction();
				Tab temp = StaticVariables.tablist.get(tabbedPane
						.getSelectedIndex());
				ea.EditPasteAction(temp);
			}
		});
		// frame.getContentPane().add(openedFilesTab, BorderLayout.EAST);
		// openedFilesTab.add("tabs", openedFilesTabPanel);
		JPanel bottomPanel = new JPanel();
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		JLabel bottomLabel = new JLabel("Copyright(Saurab Dahal)");
		bottomPanel.add(bottomLabel);
	}

}
