package app.agreement.web;

import app.agreement.domain.AgreementListener;
import app.agreement.domain.life.AccidentInsurance;
import app.agreement.domain.life.classifiers.Territory;
import app.agreement.service.AgreementService;
import app.agreement.web.resource.life.AccidentResource;
import app.user.service.UserService;
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
public class AccidentController {
    private final AgreementService agreementService;
    private final UserService userService;

    @Autowired
    public AccidentController(AgreementService agreementService, UserService userService) {
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/user/{userID}/agreement/edit/accident-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(agreement.getInsurerID() != userID){
                throw new IllegalArgumentException("InsurerID is different.");
            }

            model.addAttribute("accidentResource", new AccidentResource(agreement));
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("users", userService.getUsers().values());
            model.addAttribute("territories", Territory.values());
            model.addAttribute("agreementID", agreementID);
            model.addAttribute("action", "edit");

            return "agreement/add-edit/accident-insurance";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/edit/accident-insurance/{agreementID}")
    public String editAgreement(@PathVariable long userID, @PathVariable long agreementID, @ModelAttribute("accidentResource") @Valid AccidentResource accidentResource, BindingResult bindingResult, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(bindingResult.hasErrors()){
                model.addAttribute("users", userService.getUsers().values());
                model.addAttribute("territories", Territory.values());
                model.addAttribute("insurer", userService.getUser(userID));
                model.addAttribute("agreementID", agreementID);
                model.addAttribute("action", "edit");

                return "/agreement/add-edit/accident-insurance";
            }

            accidentResource.toAgreement(agreement);

            return "redirect:/user/" + userID;
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/user/{userID}/agreement/add/accident-insurance")
    public String addAgreement(@PathVariable long userID, Model model){
        try{
            model.addAttribute("accidentResource", new AccidentResource());
            model.addAttribute("insurer", userService.getUser(userID));
            model.addAttribute("users", userService.getUsers().values());
            model.addAttribute("territories", Territory.values());
            model.addAttribute("agreementID", "none");
            model.addAttribute("action", "add");

            return "agreement/add-edit/accident-insurance";
        }
        catch(IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/{userID}/agreement/add/accident-insurance/{agreementID}")
    public String addAgreement(@ModelAttribute @Valid AccidentResource accidentResource, BindingResult bindingResult, Model model, @PathVariable long userID){
        try{
            if(bindingResult.hasErrors()){
                model.addAttribute("users", userService.getUsers().values());
                model.addAttribute("territories", Territory.values());
                model.addAttribute("insurer", userService.getUser(userID));
                model.addAttribute("action", "add");

                return "agreement/add-edit/accident-insurance";
            }

            AccidentInsurance agreement = new AccidentInsurance(
                    accidentResource.getInsurerID(),
                    new Season(accidentResource.getBeginningDate(), accidentResource.getEndingDate()),
                    accidentResource.getInsuranceIndemnity(),
                    accidentResource.getMonthlyPayment(),
                    accidentResource.getInsuredID(),
                    accidentResource.getPermanentConsequences(),
                    accidentResource.getDeathByInjury(),
                    accidentResource.getDailyCompensation(),
                    accidentResource.getTerritory()
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
