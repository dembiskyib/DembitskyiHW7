package com.epam.lab.app.model;

import java.sql.Date;

import com.epam.lab.app.annotations.Field;
import com.epam.lab.app.annotations.Table;

@Table
public class Student {
	@Field
	private int studentId;
	@Field
	private String secondName;
	@Field
	private String firstName;
	@Field
	private String patronymic;
	@Field
	private String motherFullName;
	@Field
	private String fatherFullName;
	@Field
	private String address;
	@Field
	private String phone;
	@Field
	private String passportData;
	@Field
	private int recordBookNumber;
	@Field
	private Date startDate;
	@Field
	private String group;
	@Field
	private int year;
	@Field
	private int specialityId;
	@Field
	private boolean onExtramural;

	public Student() {

	}

	public Student(int studentId) {
		this.studentId = studentId;
	}

	public Student(int studentId, String secondName, String firstName, String patronymic, String motherFullName,
			String fatherFullName, String address, String phone, String passportData, int recordBookNumber,
			Date startDate, String group, int year, int specialityId, boolean onExtramural) {
		this.studentId = studentId;
		this.secondName = secondName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.motherFullName = motherFullName;
		this.fatherFullName = fatherFullName;
		this.address = address;
		this.phone = phone;
		this.passportData = passportData;
		this.recordBookNumber = recordBookNumber;
		this.startDate = startDate;
		this.group = group;
		this.year = year;
		this.specialityId = specialityId;
		this.onExtramural = onExtramural;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPassportData() {
		return passportData;
	}

	public void setPassportData(String passportData) {
		this.passportData = passportData;
	}

	public String getMotherFullName() {
		return motherFullName;
	}

	public void setMotherFullName(String motherFullName) {
		this.motherFullName = motherFullName;
	}

	public String getFatherFullName() {
		return fatherFullName;
	}

	public void setFatherFullName(String fatherFullName) {
		this.fatherFullName = fatherFullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRecordBookNumber() {
		return recordBookNumber;
	}

	public void setRecordBookNumber(int recordBookNumber) {
		this.recordBookNumber = recordBookNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(int specialityId) {
		this.specialityId = specialityId;
	}

	public boolean isOnExtramural() {
		return onExtramural;
	}

	public void setOnExtramural(boolean onExtramural) {
		this.onExtramural = onExtramural;
	}

}
