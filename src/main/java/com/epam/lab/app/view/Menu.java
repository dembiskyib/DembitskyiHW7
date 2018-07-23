package com.epam.lab.app.view;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.enums.Command;

public abstract class Menu {
	private static final Logger logger = LogManager.getLogger(ExtraMenu.class);
	protected Scanner scanner;

	public Menu() {
		scanner = new Scanner(System.in);
	}

	public abstract void createUI();

	public void printMenu(List<Command> commands) {
		logger.info("Print:");
		commands.stream().filter(command -> Objects.nonNull(command.getDescription()))
				.forEach(command -> logger.info(command.getNumber() + " - " + command.getDescription()));
	}
}
