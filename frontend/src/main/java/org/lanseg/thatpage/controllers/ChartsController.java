package org.lanseg.thatpage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lans
 */

@Controller
@RequestMapping("/admin")
public class SensorAdminController {
   
    @RequestMapping()
    public String test(Model model) {
        System.out.println("InAdmin!");
        model.addAttribute("preved", "medved");
        return "admin";
    }
}
