package app.agreement.domain.life;

import app.agreement.domain.AgreementAdapter;
import app.utils.domain.Season;

public abstract class LifeAgreement extends AgreementAdapter {
    private long insuredID;

    public LifeAgreement(long insurerID, Season season, double insuranceIndemnity, double monthlyPayment, long insuredID) {
        super(insurerID, season, insuranceIndemnity, monthlyPayment);
        setInsuredID(insuredID);
    }

    @Override
    public long getInsuredID() {
        return insuredID;
    }

    @Override
    public void setInsuredID(long insuredID) {
        if(insuredID < 0){
            throw new IllegalArgumentException("'insuredID' is negative.");
        }
        this.insuredID = insuredID;
    }
}
