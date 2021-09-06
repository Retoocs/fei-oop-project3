package app.user.web.resource;

import app.user.domain.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserResource {
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String birthNumber;
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String perPostalCode;
    @NotEmpty
    private String perCity;
    @NotEmpty
    private String perStreet;
    @NotEmpty
    private String perHouseNumber;

    private boolean correspondence;
    //@NotEmpty
    private String corPostalCode;
    //@NotEmpty
    private String corCity;
    //@NotEmpty
    private String corStreet;
    //@NotEmpty
    private String corHouseNumber;

    public UserResource(User user){
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.birthNumber = user.getBirthNumber();
        this.email = user.getEmail();

        this.perPostalCode = user.getPermanentAddress().getPostalCode();
        this.perCity = user.getPermanentAddress().getCity();
        this.perStreet = user.getPermanentAddress().getStreet();
        this.perHouseNumber = user.getPermanentAddress().getHouseNumber();

        this.correspondence = user.getIsCorrespondence();
        this.corPostalCode = user.getCorrespondenceAddress().getPostalCode();
        this.corCity = user.getCorrespondenceAddress().getCity();
        this.corStreet = user.getCorrespondenceAddress().getStreet();
        this.corHouseNumber = user.getCorrespondenceAddress().getHouseNumber();
    }

    public void setCorrespondence(boolean correspondence){
        this.correspondence = correspondence;
    }

    public boolean getCorrespondence(){
        return correspondence;
    }
}
