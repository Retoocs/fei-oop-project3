package app.agreement.web;

import app.agreement.domain.AgreementListener;
import app.agreement.service.AgreementService;
import app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AgreementController {
    private final AgreementService agreementService;
    private final UserService userService;

    @Autowired
    public AgreementController(AgreementService agreementService, UserService userService){
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/user/{userID}/agreement/{agreementID}")
    public String showAgreement(@PathVariable long userID, @PathVariable long agreementID, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(agreement.getInsurerID() != userID){
                throw new IllegalArgumentException("InsurerID is different.");
            }

            model.addAttribute("agreement", agreement);
            model.addAttribute("insurer", userService.getUser(userID));
            if(agreement.getInsuredID() != -1){
                model.addAttribute("insured", userService.getUser(agreement.getInsuredID()));
            }

            return "agreement/details/" + agreement.getClassName() + "-insurance";
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/user/{userID}/agreement/add")
    public String addAgreement(@PathVariable long userID, Model model){
        model.addAttribute("userID", userID);

        return "agreement/add-edit/add";
    }

    @GetMapping("/user/{userID}/agreement/remove/{agreementID}")
    public String removeUser(@PathVariable long userID, @PathVariable long agreementID, Model model){
        try{
            AgreementListener agreement = agreementService.getAgreement(agreementID);

            if(agreement.getInsurerID() != userID){
                throw new IllegalArgumentException("InsurerID is different.");
            }

            agreementService.removeAgreement(agreementID);
            return "redirect:/user/" + userID;
        }
        catch (IllegalArgumentException e){
            model.addAttribute("message", e.getMessage());
            return "error";
        }
    }


}
