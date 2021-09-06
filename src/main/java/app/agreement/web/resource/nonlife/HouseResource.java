package app.agreement.web.resource.nonlife;

import app.agreement.domain.AgreementListener;
import app.utils.domain.Address;
import app.utils.domain.Season;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HouseResource extends NonLifeResource {
    protected boolean isGarageInsured;

    public HouseResource(AgreementListener agreement){
        super(agreement);
        this.isGarageInsured = agreement.isGarageInsured();
    }

    @Override
    public boolean getIsGarageInsured(){
        return isGarageInsured;
    }

    @Override
    public void setIsGarageInsured(boolean isGarageInsured){
        this.isGarageInsured = isGarageInsured;
    }

    @Override
    public void toAgreement(AgreementListener agreement) {
        agreement.setInsurerID(this.insurerID);
        agreement.setSeason(new Season(this.beginningDate, this.endingDate));
        agreement.setInsuranceIndemnity(this.insuranceIndemnity);
        agreement.setMonthlyPayment(this.monthlyPayment);
        agreement.setTypeOfProperty(this.typeOfProperty);
        agreement.setAddressOfProperty(new Address(this.propertyPostalCode, this.propertyCity, this.propertyStreet, this.propertyHouseNumber));
        agreement.setValueOfProperty(this.valueOfProperty);
        agreement.setGarageInsured(this.isGarageInsured);
    }
}
