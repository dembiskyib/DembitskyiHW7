package com.epam.lab.app;

import com.epam.lab.app.dataBaseInteraction.DataBaseCreator;
import com.epam.lab.app.view.UserMenu;

public class Main {
	public static void main(String args[]) {
		DataBaseCreator dataBaseCreator = new DataBaseCreator();
		dataBaseCreator.login();
		UserMenu userMenu = new UserMenu();
		userMenu.createUI();
	}
}