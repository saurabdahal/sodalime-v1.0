import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class EditAction {

	Clipboard clipbd = Toolkit.getDefaultToolkit().getSystemClipboard();
	String selection;
	StringSelection clipString;
	JTextArea textArea;

	public void EditCutAction(Tab ta) {
		
		
		selection = ta.textArea.getSelectedText();
		StringSelection clipString = new StringSelection(selection);
		clipbd.setContents(clipString, clipString);
		ta.textArea.replaceRange("", ta.textArea.getSelectionStart(),
				ta.textArea.getSelectionEnd());

		
		 /* Clipboard Cut = Toolkit.getDefaultToolkit().getSystemClipboard();
		 * StringSelection ctdata = new StringSelection(bl.textArea.getText());
		 * bl.textArea.setText(""); Cut.setContents(ctdata, ctdata);
		 */
	}

	public void EditCopyAction(Tab ta) {
		selection = ta.textArea.getSelectedText();
		clipString = new StringSelection(selection);
		clipbd.setContents(clipString, clipString);

		/*
		 * Clipboard copy = Toolkit.getDefaultToolkit().getSystemClipboard();
		 * StringSelection cpdata = new StringSelection(bl.textArea.getText());
		 * copy.setContents(cpdata, cpdata);
		 */
	}

	public void EditPasteAction(Tab ta) {
		Transferable pastedata = Toolkit.getDefaultToolkit()
				.getSystemClipboard().getContents(this);
		String s;
		try {
			s = (String) (pastedata.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception ex) {
			s = ex.toString();
		}
		ta.textArea.insert(s,ta.textArea.getCaretPosition());
	}
}
