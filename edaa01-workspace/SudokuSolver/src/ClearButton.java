

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class ClearButton extends JButton implements ActionListener {
	private Solver solver;
	private SolverGUI gui;
	

	public ClearButton(Solver solver, SolverGUI gui) {
		super("Clear");
		this.solver= solver;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
	solver.clear();
	gui.clear();

	}
}