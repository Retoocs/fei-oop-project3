package app.agreement.domain.life;

import app.agreement.domain.life.classifiers.PurposeOfTrip;
import app.utils.domain.Season;

public class TravelInsurance extends LifeAgreement {
    private boolean isInEurope;
    private PurposeOfTrip purposeOfTrip;

    public TravelInsurance(long insurerID, Season season, double insuranceIndemnity, double monthlyPayment, long insuredID, boolean isInEurope, PurposeOfTrip purposeOfTrip){
        super(insurerID, season, insuranceIndemnity, monthlyPayment, insuredID);
        setInEurope(isInEurope);
        setPurposeOfTrip(purposeOfTrip);
    }

    @Override
    public boolean isInEurope() {
        return isInEurope;
    }

    @Override
    public void setInEurope(boolean inEurope) {
        isInEurope = inEurope;
    }

    @Override
    public PurposeOfTrip getPurposeOfTrip() {
        return purposeOfTrip;
    }

    @Override
    public void setPurposeOfTrip(PurposeOfTrip purposeOfTrip) {
        if(purposeOfTrip == null){
            throw new IllegalArgumentException("'purposeOfTrip' is null.");
        }
        this.purposeOfTrip = purposeOfTrip;
    }

    @Override
    public String getClassName(){
        return "travel";
    }
}
