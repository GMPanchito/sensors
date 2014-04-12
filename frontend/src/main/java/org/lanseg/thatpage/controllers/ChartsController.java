package org.lanseg.thatpage.controllers;

import org.lanseg.sensors.data.api.SensorDataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lans
 */
@Controller
@RequestMapping("/charts")
public class ChartsController {

    @Autowired
    private SensorDataStorage sensorStorage;

    @RequestMapping()
    public String main(Model model) {
        model.addAttribute("sensors", sensorStorage.getAllSensors());
        return "charts";
    }
}
