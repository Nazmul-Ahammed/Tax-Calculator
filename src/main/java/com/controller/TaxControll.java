package com.controller;

import com.domain.Adminuser;
import com.domain.Variables;
import com.exception.BadRequestAlertException;
import com.exception.NotFoundAlertException;
import com.service.TaxService;
import com.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController

@RequestMapping("/tax")
public class TaxControll {

    private static final Logger logger = Logger.getLogger(TaxControll.class);
    private TaxService taxService;
    private UserService userService;

    public TaxControll(TaxService taxService,UserService userService) {
        this.taxService=taxService;
        this.userService=userService;


    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/open")
    public String form(Model model) {
        Variables variables = new Variables();
        model.addAttribute("variables", variables);
        model.addAttribute("yearTax", "");
        model.addAttribute("monthTax", "");
        return "taxCalculator";
    }


    @RequestMapping("/calculator")
    public String calculator(@Valid @ModelAttribute("variables") Variables variables, BindingResult bindingResult, Model model) {






            if(bindingResult.hasErrors())
            {
                return "taxCalculator";
            }
            else
            {
                Variables variables1= taxService.calcu(variables);






                model.addAttribute("ti", variables.getTaxble_income());
                model.addAttribute("yearTax", variables1.getTotal_tax());
                model.addAttribute("monthTax", variables1.getM_tax());
                return "taxCalculator";
            }




    }
    @RequestMapping("/get")
    public ResponseEntity<List<Adminuser>> getAllLeaveApplications() {
        List<Adminuser> variables = userService.getAll();

        return ResponseEntity.ok().body(variables);
    }
    @CrossOrigin
    @RequestMapping("/regis")
    public ResponseEntity<Adminuser> createAuthority(@Valid @RequestBody Adminuser adminUser) throws Exception {
        if (adminUser.getId() != null) {
            throw new BadRequestAlertException("A new authority cannot have an id value");
        }
System.out.println("ssss");
        userService.insert(adminUser);
        return ResponseEntity.created(new URI("/history/"+ adminUser.getId()))
                .body(adminUser);
    }
//ssss

    @RequestMapping("/history/{id}")
    public ResponseEntity<List<Variables>> gethistory(@PathVariable Long id) {

        List<Variables> variables = taxService.gethistory(id);
        return ResponseEntity.ok().body(variables);

    }
    @RequestMapping("/calcu")
    public ResponseEntity<Variables> createcalcu(@Valid @RequestBody Variables variables) throws Exception {
        if (variables.getId() != null) {
            throw new BadRequestAlertException("A new authority cannot have an id value");
        }
        taxService.calcu(variables);
        return ResponseEntity.created(new URI("/calcu/"))
                .body(variables);
    }
    @RequestMapping("/update")
    public ResponseEntity<Adminuser> updateAuthority(@Valid @RequestBody Adminuser adminUser) throws Exception {
        if (adminUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        userService.update(adminUser);

        return ResponseEntity.created(new URI("/profile/"+ adminUser.getId()))
                .body(adminUser);
    }
    @RequestMapping("/profile/{id}")
    public ResponseEntity<Adminuser> getAuthority(@PathVariable Long id) {
        Optional<Adminuser> user = Optional.ofNullable(userService.get(id));
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        }
        throw new NotFoundAlertException("Record not found [" + id + "]");
    }

}
