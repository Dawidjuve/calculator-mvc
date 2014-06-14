package com.gusdecool.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	private Calculator calculator;
	private View view;
	private String displayedValue = "0";

	public Controller() {
		this.calculator = new Calculator();
	}

	public void run() {
		calculator.addData("0");
		view = new View();
		view.open(this);
	}

	public ActionListener actionListener() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				String value = button.getText();

				// IF debug enabled, write console log
				if(Main.DEBUG_MODE) {
					System.out.println("Button \"" + value + "\" pressed");
				}

				if(calculator.findDataType(value) == Calculator.Data.TYPE_OPERATOR) {
					calculator.addData(value);
					calculator.addData("0");
					displayedValue = calculator.getLastInput();
				} else if (calculator.findDataType(value) == Calculator.Data.TYPE_COMMAND) {
					switch (value) {
						case "=":
							displayedValue = calculator.calculateResult();
							break;
						case "C":
							calculator.resetLastInput();
							displayedValue = "0";
							break;
						case "CE":
							calculator.reset();
							displayedValue = "0";
							break;
					}
				} else {
					if(displayedValue.equals("0")) {
						displayedValue = value;
					} else {
						displayedValue += value;
					}
					calculator.editLatestInput(displayedValue);
				}

				view.setDisplayedText(displayedValue);
			}
		};
	}
}
