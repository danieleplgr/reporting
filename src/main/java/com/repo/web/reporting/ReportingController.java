package com.repo.web.reporting;
import org.apache.log4j.Logger;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.repo.domain.Reporting;

@RequestMapping("/reportings")
@Controller
@RooWebScaffold(path = "reportings", formBackingObject = Reporting.class)
public class ReportingController {
	
	Logger logger = Logger.getLogger(ReportingController.class);
	
	@RequestMapping(value="/open", method = RequestMethod.GET) 
	public void openForm(){
		
	}
	
	@RequestMapping(value="/open", method = RequestMethod.POST) 
	public void openCreate(){
		
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET) 
	public void openForm(@RequestParam(value = "q", required = true) String query){
		logger.info("openForm" + query);
	}
	
}
