package in.ac.vnrvjiet;

import javax.swing.*;

public class Maze {
	public static void main(String[] args){
		new Maze();
	}

        Board pnlBoard;
	public Maze(){
		JFrame f = new JFrame();
		f.setTitle("Maze Game");
		pnlBoard = new Board();
                pnlBoard.setFocusable(true);
                f.add(pnlBoard);
		f.setSize(464, 485);
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

