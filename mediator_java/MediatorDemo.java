

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

interface Command {
	void execute();
}

class Mediator {
	
	BtnView btnView;
	BtnSearch btnSearch;
	BtnBook btnBook;
	LblDisplay show;;

	// ....
	void registerView(BtnView v) {
		btnView = v;
	}

	void registerSearch(BtnSearch s) {
		btnSearch = s;
	}

	void registerBook(BtnBook b) {
		btnBook = b;
	}

	void registerDisplay(LblDisplay d) {
		show = d;
	}

	void book() {
		btnBook.setEnabled(false);
		btnView.setEnabled(true);
		btnSearch.setEnabled(true);
		show.setText("booking...");
		//run business logic related to booking
	}

	void view() {
		btnView.setEnabled(false);
		btnSearch.setEnabled(true);
		btnBook.setEnabled(true);
		show.setText("viewing...");
		//run business logic related to booking
	}

	void search() {
		btnSearch.setEnabled(false);
		btnView.setEnabled(true);
		btnBook.setEnabled(true);
		show.setText("searching...");
		//run business logic related to booking
	}
	
}

class BtnView extends JButton implements Command {
	Mediator med;

	BtnView(ActionListener al, Mediator m) {
		super("View");
		addActionListener(al);
		med = m;
		med.registerView(this);
	}

	public void execute() {
		med.view();
	}
}

class BtnSearch extends JButton implements Command {
	Mediator med;

	BtnSearch(ActionListener al, Mediator m) {
		super("Search");
		addActionListener(al);
		med = m;
		med.registerSearch(this);
	}

	public void execute() {
		med.search();
	}
}

class BtnBook extends JButton implements Command {
	Mediator med;

	BtnBook(ActionListener al, Mediator m) {
		super("Book");
		addActionListener(al);
		med = m;
		med.registerBook(this);
	}

	public void execute() {
		med.book();
	}
}

class LblDisplay extends JLabel {
	Mediator med;

	LblDisplay(Mediator m) {
		super("Just start...");
		med = m;
		med.registerDisplay(this);
		setFont(new Font("Arial", Font.BOLD, 24));
	}
}

public class MediatorDemo extends JFrame {

	Mediator med = new Mediator();

	ActionListener actionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			Command comd = (Command) ae.getSource();
			comd.execute();
		}
	};

	MediatorDemo() {
		JPanel p = new JPanel();
		p.add(new BtnView(actionListener, med));
		p.add(new BtnBook(actionListener, med));
		p.add(new BtnSearch(actionListener, med));
		getContentPane().add(new LblDisplay(med), "North");
		getContentPane().add(p, "South");
		setSize(400, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		final MediatorDemo frame = new MediatorDemo();
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure to close this window?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

	}
}