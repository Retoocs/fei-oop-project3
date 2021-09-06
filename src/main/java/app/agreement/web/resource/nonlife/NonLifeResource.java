package app.agreement.web.resource.nonlife;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.agreement.web.resource.AgreementResource;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public abstract class NonLifeResource extends AgreementResource {
    @NotNull
    protected TypeOfProperty typeOfProperty;
    @NotNull
    @NotEmpty
    protected String propertyPostalCode;
    @NotNull
    @NotEmpty
    protected String propertyCity;
    @NotNull
    @NotEmpty
    protected String propertyStreet;
    @NotNull
    @NotEmpty
    protected String propertyHouseNumber;
    @Min(0)
    protected double valueOfProperty;

    public NonLifeResource(AgreementListener agreement){
        super(agreement);
        this.typeOfProperty = agreement.getTypeOfProperty();
        this.propertyPostalCode = agreement.getAddressOfProperty().getPostalCode();
        this.propertyCity = agreement.getAddressOfProperty().getCity();
        this.propertyStreet = agreement.getAddressOfProperty().getStreet();
        this.propertyHouseNumber = agreement.getAddressOfProperty().getHouseNumber();
        this.valueOfProperty = agreement.getValueOfProperty();
    }
}
