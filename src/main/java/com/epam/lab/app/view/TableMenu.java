package com.epam.lab.app.view;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.controller.TableController;
import com.epam.lab.app.enums.TableCommand;

public class TableMenu extends Menu {
	private static final Logger logger = LogManager.getLogger(UserMenu.class);
	private TableController tableController;

	public TableMenu(TableController tableController) {
		this.tableController = tableController;
	}

	public void createUI() {
		boolean end = false;
		while (!end) {
			printMenu(Arrays.asList(TableCommand.values()));
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
}
