package com.controller;

import com.domain.Adminuser;
import com.domain.Registration;
import com.domain.Sellervarify;
import com.domain.TaxRate;
import com.exception.BadRequestAlertException;
import com.exception.NotFoundAlertException;
import com.service.UserService;
import com.service.VarifyService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/admin/user/")
public class AdminUserControll {
    private static final Logger logger = Logger.getLogger(TaxControll.class);
    private UserService userService;
    private VarifyService varifyService;

    public AdminUserControll(UserService userService,VarifyService varifyService) {

        this.userService=userService;
        this.varifyService=varifyService;

    }
    @RequestMapping("/admins")
    public ResponseEntity<List<Adminuser>> admins() {
        List<Adminuser> adminusers = userService.getAll();
        return ResponseEntity.ok().body(adminusers);
    }
    @CrossOrigin
    @RequestMapping("/regis")
    public ResponseEntity<Adminuser> create(@Valid @RequestBody Adminuser adminUser) throws Exception {
        if (adminUser.getId() != null) {
            throw new BadRequestAlertException("A new authority cannot have an id value");
        }

        userService.insert(adminUser);
        return ResponseEntity.created(new URI("/admins/"))
                .body(adminUser);
    }
    @RequestMapping("/update")
    public ResponseEntity<Adminuser> update(@Valid @RequestBody Adminuser adminuser) throws Exception {
        if (adminuser.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        userService.update(adminuser);
        return ResponseEntity.created(new URI("/admins/"))
                .body(adminuser);
    }
    @RequestMapping("/admin/{id}")
    public ResponseEntity<Adminuser> getbyid(@PathVariable Long id) {
        Optional<Adminuser> adminuser = Optional.ofNullable(userService.get(id));
        if (adminuser.isPresent()) {
            return ResponseEntity.ok().body(adminuser.get());
        }
        throw new NotFoundAlertException("Record not found [" + id + "]");
    }
    @RequestMapping("/updatepassword")
    public ResponseEntity<Adminuser> updatepassword(@Valid @RequestBody Adminuser adminuser) throws Exception {
        if (adminuser.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        userService.updatepassword(adminuser);
        return ResponseEntity.created(new URI("/admins/"))
                .body(adminuser);
    }
    @RequestMapping("/seller/varify")
    public ResponseEntity<List<Registration>> users() {
        List<Registration> registrations = varifyService.getAll();


        return ResponseEntity.ok().body(registrations);
    }

    @RequestMapping("/seller/varify/update")
    public ResponseEntity<Registration> updatevarify(@Valid @RequestBody Registration registrations) throws Exception {
        if (registrations.getId() == null) {
            throw new BadRequestAlertException("Invalid id");
        }
        varifyService.update(registrations);
        return ResponseEntity.created(new URI("/seller/varify/"))
                .body(registrations);
    }
    @RequestMapping("/seller/varify/{id}")
    public ResponseEntity<Registration> getbyidvarify(@PathVariable Long id) {
        Optional<Registration> registration = Optional.ofNullable(varifyService.get(id));
        if (registration.isPresent()) {
            return ResponseEntity.ok().body(registration.get());
        }
        throw new NotFoundAlertException("Record not found [" + id + "]");
    }

}
