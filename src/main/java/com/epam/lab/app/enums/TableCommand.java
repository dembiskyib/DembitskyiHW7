package com.epam.lab.app.enums;

public enum TableCommand {
	CREATE(1, "Create new row"), READ(2, "Read all"), UPDATE(3, "Update row"), DELETE(4, "Delete row"), EXIT(5,
			"Exit"), DEFAULT();

	private int number;
	private String description;

	private TableCommand() {

	}

	private TableCommand(int number, String description) {
		this.number = number;
		this.description = description;
	}

	public static TableCommand get(int number) {
		TableCommand currentCommand = DEFAULT;
		for (TableCommand command : TableCommand.values()) {
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
