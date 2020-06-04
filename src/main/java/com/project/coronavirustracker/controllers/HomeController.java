package com.project.coronavirustracker.controllers;

import com.project.coronavirustracker.models.LocationStats;
import com.project.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> totStats =coronaVirusDataService.getAllStats();
        int totalReportedCases= totStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases= totStats.stream().mapToInt(stat -> stat.getDifferenceInCases()).sum();
        model.addAttribute("locationStats",totStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);

        return "home";
    }
}
