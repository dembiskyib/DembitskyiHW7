package com.epam.lab.app.view;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.controller.CourseController;
import com.epam.lab.app.controller.GradeController;
import com.epam.lab.app.controller.MainController;
import com.epam.lab.app.controller.SpecialityController;
import com.epam.lab.app.controller.StudentController;
import com.epam.lab.app.dataBaseInteraction.ConnectionFactory;
import com.epam.lab.app.enums.MenuCommand;

public class UserMenu {
	private static final Logger logger = LogManager.getLogger(UserMenu.class);
	private Scanner scanner;
	private MainController mainController;
	private TableMenu tableMenu;
	private ExtraMenu extraMenu;

	public UserMenu() {
		mainController = new MainController();
		extraMenu = new ExtraMenu();
		scanner = new Scanner(System.in);
	}

	public void createUI() {
		boolean end = false;
		while (!end) {
			printMenu();
			MenuCommand command = MenuCommand.get(scanner.nextInt());
			switch (command) {
			case META:
				mainController.printMeta();
				break;
			case COURSE:
				tableMenu = new TableMenu(new CourseController());
				tableMenu.createUI();
				break;
			case GRADE:
				tableMenu = new TableMenu(new GradeController());
				tableMenu.createUI();
				break;
			case SPECIALITY:
				tableMenu = new TableMenu(new SpecialityController());
				tableMenu.createUI();
				break;
			case STUDENT:
				tableMenu = new TableMenu(new StudentController());
				tableMenu.createUI();
				break;
			case EXTRA_QUERIES:
				extraMenu.createUI();
				break;
			case EXIT:
				try {
					ConnectionFactory.getConnection().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		List<MenuCommand> menuCommand = Arrays.asList(MenuCommand.values());
		menuCommand.stream().filter(command -> Objects.nonNull(command.getDescription()))
				.forEach(command -> logger.info(command.getNumber() + " - " + command.getDescription()));
	}

}
