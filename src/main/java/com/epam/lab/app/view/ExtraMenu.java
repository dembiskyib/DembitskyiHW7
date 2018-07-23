package com.epam.lab.app.view;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.controller.ExtraController;
import com.epam.lab.app.enums.ExtraCommand;

public class ExtraMenu extends Menu {
	private static final Logger logger = LogManager.getLogger(ExtraMenu.class);
	private ExtraController extraController;

	public ExtraMenu() {
		extraController = new ExtraController();
	}

	public void createUI() {
		boolean end = false;
		while (!end) {
			printMenu(Arrays.asList(ExtraCommand.values()));
			ExtraCommand command = ExtraCommand.get(scanner.nextInt());
			switch (command) {
			case COURSES_BY_SPECIALITY:
				extraController.printCourses();
				break;
			case DELETE_ALL_GRADES:
				extraController.deleteAllStudentGrades();
				break;
			case DELETE_WITH_TRANSFER:
				extraController.deleteWithTransfer();
				break;
			case INCREASE_YEAR:
				extraController.increaseYear();
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
