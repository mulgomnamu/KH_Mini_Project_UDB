package service;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextHint extends JTextField implements FocusListener {

	//textfield에 어떤걸 써야될지 알려주는 hint 함수, 가이드라인 글자 출력
	
	  private final String hint;
	  private boolean showingHint;

	  public TextHint(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    super.addFocusListener(this);
	  }

	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      showingHint = false;
	    }
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	    }
	  }

	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
	}

