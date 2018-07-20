package com.epam.lab.app.enums;

public enum ExtraCommand {
	COURSES_BY_SPECIALITY(1, "Print all speciality courses"), DELETE_ALL_GRADES(2,
			"Delete all student grades"), DELETE_WITH_TRANSFER(3,
					"Delete speciality with students transfer (task3)"), INCREASE_YEAR(4,
							"Increase every student year"), EXIT(5, "Exit"), DEFAULT();
	private int number;
	private String description;

	private ExtraCommand() {

	}

	private ExtraCommand(int number, String description) {
		this.number = number;
		this.description = description;
	}

	public static ExtraCommand get(int number) {
		ExtraCommand currentCommand = DEFAULT;
		for (ExtraCommand command : ExtraCommand.values()) {
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
