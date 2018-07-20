package com.epam.lab.app.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.controller.ExtraController;
import com.epam.lab.app.enums.ExtraCommand;

public class ExtraMenu {
	private static final Logger logger = LogManager.getLogger(ExtraMenu.class);
	private Scanner scanner;
	private ExtraController extraController;

	public ExtraMenu() {
		extraController = new ExtraController();
		scanner = new Scanner(System.in);
	}

	public void createUI() {
		boolean end = false;
		while (!end) {
			printMenu();
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

	private void printMenu() {
		logger.info("Print:");
		List<ExtraCommand> extraCommand = Arrays.asList(ExtraCommand.values());
		extraCommand.stream().filter(command -> Objects.nonNull(command.getDescription()))
				.forEach(command -> logger.info(command.getNumber() + " - " + command.getDescription()));
	}
}
