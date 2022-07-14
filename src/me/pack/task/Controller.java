package me.pack.task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.pack.engine.Model;
import me.pack.lookandfeel.View;

public class Controller implements ActionListener {
	static Model model;
	static View view;

	public Controller(View view, Model model) {
		Controller.view = view;
		Controller.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}