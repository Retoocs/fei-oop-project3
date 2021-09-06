package app.agreement.domain;

import app.utils.domain.AgreementIdGenerator;
import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.agreement.domain.life.classifiers.Territory;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.utils.domain.Address;
import app.utils.domain.Season;

import java.time.LocalDate;

public abstract class AgreementAdapter implements AgreementListener{
    private final long id;
    private LocalDate dateOfCreation;
    private long insurerID;
    private Season season;
    private double insuranceIndemnity;
    private double monthlyPayment;

    public AgreementAdapter(long insurerID, Season season, double insuranceIndemnity, double monthlyPayment){
        this.id = AgreementIdGenerator.newId();
        setDateOfCreation(LocalDate.now());
        setInsurerID(insurerID);
        setSeason(season);
        setInsuranceIndemnity(insuranceIndemnity);
        setMonthlyPayment(monthlyPayment);
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    private void setDateOfCreation(LocalDate dateOfCreation) {
        if(dateOfCreation == null){
            throw new IllegalArgumentException("'dateOfCreation' is null.");
        }
        if(dateOfCreation.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("'dateOfCreation' is in the future.");
        }
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public long getInsurerID() {
        return insurerID;
    }

    @Override
    public void setInsurerID(long insurerID) {
        if(insurerID < 0){
            throw new IllegalArgumentException("'insurerID' is negative.");
        }
        this.insurerID = insurerID;
    }

    @Override
    public Season getSeason() {
        return season;
    }

    @Override
    public void setSeason(Season season){
        if(season == null){
            throw new IllegalArgumentException("'season' is null.");
        }
        this.season = season;
    }

    @Override
    public double getInsuranceIndemnity() {
        return insuranceIndemnity;
    }

    @Override
    public void setInsuranceIndemnity(double insuranceIndemnity) {
        if(insuranceIndemnity < 0){
            throw new IllegalArgumentException("'insuranceIndemnity' is negative.");
        }
        this.insuranceIndemnity = insuranceIndemnity;
    }

    @Override
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    @Override
    public void setMonthlyPayment(double monthlyPayment) {
        if(monthlyPayment < 0){
            throw new IllegalArgumentException("'monthlyPayment' is negative.");
        }
        this.monthlyPayment = monthlyPayment;
    }


    @Override
    public long getInsuredID(){
        return -1;
    }

    @Override
    public void setInsuredID(long insuredID) {
    }

    @Override
    public double getPermanentConsequences() {
        return -1;
    }

    @Override
    public void setPermanentConsequences(double permanentConsequences) {
    }

    @Override
    public double getDeathByInjury() {
        return -1;
    }

    @Override
    public void setDeathByInjury(double deathByInjury) {
    }

    @Override
    public double getDailyCompensation() {
        return -1;
    }

    @Override
    public void setDailyCompensation(double dailyCompensation) {
    }

    @Override
    public Territory getTerritory() {
        return null;
    }

    @Override
    public void setTerritory(Territory territory) {
    }

    @Override
    public boolean isInEurope() {
        return false;
    }

    @Override
    public void setInEurope(boolean inEurope) {
    }

    @Override
    public PurposeOfTrip getPurposeOfTrip() {
        return null;
    }

    @Override
    public void setPurposeOfTrip(PurposeOfTrip purposeOfTrip) {
    }

    @Override
    public TypeOfProperty getTypeOfProperty() {
        return null;
    }

    @Override
    public void setTypeOfProperty(TypeOfProperty typeOfProperty) {
    }

    @Override
    public Address getAddressOfProperty() {
        return null;
    }

    @Override
    public void setAddressOfProperty(Address addressOfProperty) {
    }

    @Override
    public double getValueOfProperty() {
        return -1;
    }

    @Override
    public void setValueOfProperty(double valueOfProperty) {
    }

    @Override
    public double getEquipmentValue() {
        return -1;
    }

    @Override
    public void setEquipmentValue(double equipmentValue) {
    }

    @Override
    public boolean isGarageInsured() {
        return false;
    }

    @Override
    public void setGarageInsured(boolean garageInsured) {
    }
}
