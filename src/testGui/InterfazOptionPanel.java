package testGui;

import java.awt.Component;

import vista.IOptionPane;

public interface InterfazOptionPanel extends IOptionPane {
	void ShowMessage(Component parent, String mensaje);
}
