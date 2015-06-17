import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.util.*;

public class SolverGUI extends JFrame {
	private Solver solver;
	private GridLayout gridLayout;
	private JPanel gridPanel;
	private JPanel[] gridSubPanel;
	private OneLetterField[][] square;

	public SolverGUI(Solver solver) {
		super("Sudoku Solver"); // makes a JFrame: Frame window = new
		this.solver = solver;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 1 mainPanel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(5, 5));
		add(mainPanel);

		// 1 3x3 gridPanel
		gridPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(9, 9);
		gridPanel.setLayout(gridLayout);
		Color lavender = new Color(230, 230, 250);
		gridPanel.setBorder(new LineBorder(lavender));
		gridPanel.setBackground(lavender);
		mainPanel.add(gridPanel, "Center");
		square = new OneLetterField[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				square[i][j] = new OneLetterField();
				if (i % 2 == 0) {
					gridPanel.add(square[i][j]);
					square[i][j].setText("");
				} else {
					gridPanel.add(square[i][j]);
					square[i][j].setText("");
				}
			}
		}
		
		// set background color
		Color snow = new Color(255, 250, 250);
		Color lightBlue = new Color(224, 255, 255);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				square[i][j].setBackground(lightBlue);
			}
		}
		for (int i = 3; i < 6; i++) {
			for (int j = 3; j < 6; j++) {
				square[i][j].setBackground(lightBlue);
			}
		}
		for (int i = 6; i < 9; i++) {
			for (int j = 6; j < 9; j++) {
				square[i][j].setBackground(lightBlue);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 6; j < 9; j++) {
				square[i][j].setBackground(lightBlue);
				square[j][i].setBackground(lightBlue);
			}
		}

		// add 1 buttonPanel
		JPanel buttonPanel = new JPanel();
		GridLayout buttonLayout = new GridLayout(1, 2);
		buttonPanel.setLayout(buttonLayout);
		mainPanel.add(buttonPanel, "South");

		// add Solve and Clear button
		JButton solvebutton = new SolveButton(solver, this);
		buttonPanel.add(solvebutton);
		JButton clearbutton = new ClearButton(solver, this);
		buttonPanel.add(clearbutton);


		pack();
		setVisible(true);
	}

	// method to clear all the values
	public void clear() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				square[i][j].setText("");
			}
		}

	}

	// method to read and set all the values
	public void readandset() {
		int number;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String value = square[i][j].getText();
				if (value.equals("")) {
					number = 0;
				} else {
					number = Integer.parseInt(square[i][j].getText());
				}

				solver.setValue(i, j, number);

			}
		}
	}

	// method to solve the sudoku
	public void solve() {
		boolean solved = solver.solve();
		if (solved == true) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int value = solver.getValue(i, j);
					square[i][j].setText(String.valueOf(value));
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "There is no solution.");
		}
	}

}
