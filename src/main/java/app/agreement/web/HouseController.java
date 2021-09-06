package app.agreement.web;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.nonlife.HouseInsurance;
import app.agreement.domain.nonlife.classifiers.TypeOfProperty;
import app.agreement.service.AgreementService;
import app.agreement.web.resource.nonlife.HouseResource;
import app.user.service.UserService;
import app.utils.domain.Address;
import app.utils.domain.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HouseController {
    private final AgreementService agreementService;
    private final UserService userService;

    @Autowired
    public HouseController(AgreementService agreementService, UserService userService){
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/user/{userID}/agreement/edit/house-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(agreement.getInsurerID() != userID){
                throw new IllegalArgumentException("InsurerID is different.");
            }

            model.addAttribute("houseResource", new HouseResource(agreement));
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("typesOfProperty", TypeOfProperty.values());
            model.addAttribute("agreementID", agreementID);
            model.addAttribute("action", "edit");

            return "agreement/add-edit/house-insurance";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/edit/house-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, @ModelAttribute("houseResource") @Valid HouseResource houseResource, BindingResult bindingResult, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(bindingResult.hasErrors()){
                model.addAttribute("typesOfProperty", TypeOfProperty.values());
                model.addAttribute("insurer", userService.getUser(agreement.getInsurerID()));
                model.addAttribute("agreementID", agreementID);
                model.addAttribute("action", "edit");

                return "/agreement/add-edit/house-insurance";
            }

            houseResource.toAgreement(agreement);

            return "redirect:/user/" + userID;
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/user/{userID}/agreement/add/house-insurance")
    public String addAgreement(@PathVariable long userID, Model model){
        try{
            model.addAttribute("houseResource", new HouseResource());
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("typesOfProperty", TypeOfProperty.values());
            model.addAttribute("agreementID", "none");
            model.addAttribute("action", "add");

            return "agreement/add-edit/house-insurance";
        }
        catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/add/house-insurance/{agreementID}")
    public String addAgreement(@ModelAttribute @Valid HouseResource houseResource, BindingResult bindingResult, Model model, @PathVariable long userID){
        try{
            if(bindingResult.hasErrors()){
                model.addAttribute("typesOfProperty", TypeOfProperty.values());
                model.addAttribute("insurer", userService.getUser(userID));
                model.addAttribute("agreementID", "none");
                model.addAttribute("action", "add");

                return "agreement/add-edit/house-insurance";
            }

            HouseInsurance agreement = new HouseInsurance(
                    houseResource.getInsurerID(),
                    new Season(houseResource.getBeginningDate(), houseResource.getEndingDate()),
                    houseResource.getInsuranceIndemnity(),
                    houseResource.getMonthlyPayment(),
                    houseResource.getTypeOfProperty(),
                    new Address(houseResource.getPropertyPostalCode(), houseResource.getPropertyCity(), houseResource.getPropertyStreet(), houseResource.getPropertyHouseNumber()),
                    houseResource.getValueOfProperty(),
                    houseResource.getIsGarageInsured()
            );

            agreementService.addAgreement(agreement);

            return "redirect:/user/" + userID;
        }
        catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }
}
