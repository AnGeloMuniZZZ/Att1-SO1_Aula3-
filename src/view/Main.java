package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {

		RedesController redeController = new RedesController();
		String i = JOptionPane.showInputDialog(null, "1 - IP\n 2 - PING\n 3 - SAIR", "Atividade OS",
				JOptionPane.PLAIN_MESSAGE);

		switch (Integer.parseInt(i)) {
		case 1: {
			redeController.IP();
			break;
		}
		case 2: {
			redeController.PING();
			break;
		}
		default:
			JOptionPane.showMessageDialog(null, "Adeus");
			break;
		}
	}

}
