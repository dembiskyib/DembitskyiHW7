package com.epam.lab.app.enums;

public enum MenuCommand {
	META(1, "Print metadata"), COURSE(2, "\"Course\" table"), GRADE(3, "\"Grade\" table"), SPECIALITY(4,
			"\"Speciality\" table"), STUDENT(5,
					"\"Student\" table"), EXTRA_QUERIES(6, "Extra queries menu (task3)"), EXIT(7, "Exit"), DEFAULT();
	private int number;
	private String description;

	private MenuCommand() {

	}

	private MenuCommand(int number, String description) {
		this.number = number;
		this.description = description;
	}

	public static MenuCommand get(int number) {
		MenuCommand currentCommand = DEFAULT;
		for (MenuCommand command : MenuCommand.values()) {
			if (command.number == number) {
				currentCommand = command;
			}
		}
		return currentCommand;
	}

	public int getNumber() {
		return number;
	}

	public String getDescription() {
		return description;
	}
}
