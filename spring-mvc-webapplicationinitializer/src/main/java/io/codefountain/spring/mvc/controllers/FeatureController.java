package io.codefountain.spring.mvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.codefountain.spring.mvc.domain.Feature;
import io.codefountain.spring.mvc.domain.FeatureList;

@Controller
public class FeatureController {

	@GetMapping("/features")
	public String getNewFeature(Model model) {
		Feature feature1 = new Feature("F-001", "Self Healing", "Automated recovery from certain application failures", "2.12.1", new String[] {"Jack", "Ram", "Meera"});
		Feature feature2 = new Feature("F-002", "Sortable Table View", "Table data can now be sorted using columns", "2.12.1", new String[] {"Snigdha", "Jack", "Pawan"});
		Feature feature3 = new Feature("F-003", "Conguable Data Source", "Configuration support for pluggable database", "2.12.1", new String[] {"Jason", "Tim", "Meera"});

		List<Feature> listOfFeatures = new ArrayList<>();
		listOfFeatures.add(feature1);
		listOfFeatures.add(feature2);
		listOfFeatures.add(feature3);
		
		FeatureList featureList = new FeatureList();
		featureList.setListOfFeatures(listOfFeatures);
		
		model.addAttribute("features", featureList);
		return "features";
	}
}
