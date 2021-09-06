package app.agreement.web.resource.nonlife;

import app.agreement.domain.AgreementListener;
import app.utils.domain.Address;
import app.utils.domain.Season;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class HouseholdResource extends NonLifeResource {
    @Min(0)
    protected double equipmentValue;

    public HouseholdResource(AgreementListener agreement){
        super(agreement);
        this.equipmentValue = agreement.getEquipmentValue();
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
        agreement.setEquipmentValue(this.equipmentValue);
    }
}
