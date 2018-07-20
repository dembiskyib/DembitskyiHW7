package com.epam.lab.app.controller;

import com.epam.lab.app.task4.MetaDataReader;

public class MainController {
	private MetaDataReader metaDataReader;

	public MainController() {
		metaDataReader = new MetaDataReader();
	}

	public void printMeta() {
		metaDataReader.printMetaData();
	}
}
