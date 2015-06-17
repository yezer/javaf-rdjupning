
import javax.swing.*;

import java.awt.event.*;

public class SolveButton extends JButton implements ActionListener {
	private Solver solver;
	private SolverGUI gui;

	public SolveButton(Solver solver, SolverGUI gui) {
		super("Solve");
		this.solver= solver;
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		gui.readandset();
		gui.solve();
	}
}