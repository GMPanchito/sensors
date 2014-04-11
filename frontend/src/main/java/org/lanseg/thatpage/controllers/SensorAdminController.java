package org.lanseg.thatpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lans
 */

@Controller
public class SensorAdminController {
   
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public void test(Model model) {
        model.addAttribute("preved", "medved");
    }
}
