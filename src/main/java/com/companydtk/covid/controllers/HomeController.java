package com.companydtk.covid.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.companydtk.covid.CoronavirusApplication;
import com.companydtk.covid.models.LocationStats;
import com.companydtk.covid.service.CoronavirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronavirusDataService coronavirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStates = coronavirusDataService.getAllStates();
		int totalReportedCases = allStates.stream().mapToInt(stat -> stat.getLatestTotalReportCases()).sum();
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("listState",allStates);
		return "index";
	}
}
