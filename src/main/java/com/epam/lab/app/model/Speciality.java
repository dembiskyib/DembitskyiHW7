package com.epam.lab.app.model;

public class Speciality {
	private int specialityId;
	private String specialityName;
	private String specialityDescription;

	public Speciality() {

	}

	public Speciality(int specialityId, String specialityName, String specialityDescription) {
		this.specialityId = specialityId;
		this.specialityName = specialityName;
		this.specialityDescription = specialityDescription;
	}

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialityDescription() {
		return specialityDescription;
	}

	public void setSpecialityDescription(String specialityDescription) {
		this.specialityDescription = specialityDescription;
	}
}
