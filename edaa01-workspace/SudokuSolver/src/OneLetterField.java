

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;

public class OneLetterField extends JTextField {

	/**
	 * Creates a text field to display only one character.
	 */
	public OneLetterField() {
		super("");
		((AbstractDocument) this.getDocument()).setDocumentFilter(new OneLetterFilter());
		setHorizontalAlignment(JTextField.CENTER);
	}

	private class OneLetterFilter extends DocumentFilter {	   	   
		OneLetterFilter(){	    
			super();	    
		} 	   
	
		public void insertString(FilterBypass fb, int offset, String  str, AttributeSet attr) throws BadLocationException {	    
			if ((fb.getDocument().getLength() + str.length()) > 1) {
				return;
			}
			if (! str.isEmpty() && ! isInteger(str.charAt(0))) {
				return;
			}
			fb.insertString(offset, str, attr);	         
		}
		
		public void replace(FilterBypass fb, int offset, int length, String  str, AttributeSet attr) throws BadLocationException {	    
			if ((fb.getDocument().getLength() + str.length() - length) > 1) {
				return;
			}
			if (! str.isEmpty() && ! isInteger(str.charAt(0))) {
				return;
			}
			fb.replace(offset, length, str, attr);	         
		}
	}
	
	//method to check if it is an integer and if it !=0
	public static boolean isInteger(Character s) {
		String t = Character.toString(s);
	    try { 
	    
	        Integer.parseInt(t); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    if (t.equals("0")) {
	    	return false;
	    }
	    return true;
	}
	
	
}