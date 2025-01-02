package com.user.model;

import java.time.LocalDate;

public class RentalAgreement {

	private int agreementID;

   
    private Property property;

    
    private User tenant;

    private LocalDate startDate;

    private LocalDate endDate;

    private double monthlyRent;

    private AgreementStatus agreementStatus;

    private LocalDate dateCreated;

	public RentalAgreement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentalAgreement(int agreementID, Property property, User tenant, LocalDate startDate, LocalDate endDate,
			double monthlyRent, AgreementStatus agreementStatus, LocalDate dateCreated) {
		super();
		this.agreementID = agreementID;
		this.property = property;
		this.tenant = tenant;
		this.startDate = startDate;
		this.endDate = endDate;
		this.monthlyRent = monthlyRent;
		this.agreementStatus = agreementStatus;
		this.dateCreated = dateCreated;
	}

	public int getAgreementID() {
		return agreementID;
	}

	public void setAgreementID(int agreementID) {
		this.agreementID = agreementID;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getTenant() {
		return tenant;
	}

	public void setTenant(User tenant) {
		this.tenant = tenant;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getMonthlyRent() {
		return monthlyRent;
	}

	public void setMonthlyRent(double monthlyRent) {
		this.monthlyRent = monthlyRent;
	}

	public AgreementStatus getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(AgreementStatus agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "RentalAgreement [agreementID=" + agreementID + ", property=" + property + ", tenant=" + tenant
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", monthlyRent=" + monthlyRent
				+ ", dateCreated=" + dateCreated + "]";
	}
	public enum AgreementStatus {
        ACTIVE, TERMINATED, PENDING
    }

    
}

