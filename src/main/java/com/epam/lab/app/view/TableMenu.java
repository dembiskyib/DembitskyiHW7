package com.epam.lab.app.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.controller.TableController;
import com.epam.lab.app.enums.TableCommand;

public class TableMenu {
	private static final Logger logger = LogManager.getLogger(UserMenu.class);
	TableController tableController;
	private Scanner scanner;

	public TableMenu(TableController tableController) {
		this.tableController = tableController;
		scanner = new Scanner(System.in);
	}

	public void createUI() {
		boolean end = false;
		while (!end) {
			printMenu();
			TableCommand command = TableCommand.get(scanner.nextInt());
			switch (command) {
			case CREATE:
				tableController.create();
				break;
			case READ:
				tableController.read();
				break;
			case UPDATE:
				tableController.update();
				break;
			case DELETE:
				tableController.delete();
				break;
			case EXIT:
				end = true;
				break;
			case DEFAULT:
				logger.info("Wrong input");
				break;
			}
		}
	}

	private void printMenu() {
		logger.info("Print:");
		List<TableCommand> tableCommand = Arrays.asList(TableCommand.values());
		tableCommand.stream().filter(command -> Objects.nonNull(command.getDescription()))
				.forEach(command -> logger.info(command.getNumber() + " - " + command.getDescription()));
	}
}
