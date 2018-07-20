package com.epam.lab.app.controller;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.lab.app.model.Speciality;
import com.epam.lab.app.service.SpecialityService;

public class SpecialityController implements TableController {
	private static final Logger logger = LogManager.getLogger(SpecialityController.class);
	SpecialityService specialityService;
	Scanner scanner;

	public SpecialityController() {
		specialityService = new SpecialityService();
		scanner = new Scanner(System.in);
	}

	public void create() {
		logger.info("Print specialityId, specialityName and specialityDescription");
		Speciality speciality = new Speciality(scanner.nextInt(), scanner.next(), scanner.next());
		specialityService.create(speciality);
	}

	public void read() {
		logger.info("Specialities:");
		logger.info(String.format("%20s %40s %s ", "specialityId", "specialityName", "specialityDescription"));
		specialityService.getAll().forEach(speciality -> logger.info(String.format("%20d %40s %s",
				speciality.getSpecialityId(), speciality.getSpecialityName(), speciality.getSpecialityDescription())));
	}

	public void update() {
		read();
		logger.info("Print `specialityId` to update");
		Speciality speciality = specialityService.readById(scanner.nextInt());
		logger.info("Print\n 1 - to update `specialityName`\n 2 - to update `specialityDescription`");
		switch (scanner.nextInt()) {
		case 1:
			logger.info("Print new specialityName");
			speciality.setSpecialityName(scanner.next());
			break;
		case 2:
			logger.info("Print new specialityDescription");
			speciality.setSpecialityDescription(scanner.next());
			break;
		default:
			logger.info("Wrong number");
			break;
		}
		specialityService.update(speciality);
	}

	public void delete() {
		read();
		logger.info("Print `specialityId` to delete");
		specialityService.deleteById(scanner.nextInt());
	}
}
