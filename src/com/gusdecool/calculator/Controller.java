package com.gusdecool.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	public void run() {
		View view = new View();
		view.open(this);
	}

	public ActionListener actionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();

				// IF debug enabled, write console log
				if(Main.DEBUG_MODE) {
					System.out.println("Button \"" + button.getText() + "\" pressed");
				}

			}
		};
	}
}
