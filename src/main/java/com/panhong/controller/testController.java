package com.panhong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
	@RequestMapping(value="/")
	public String test(){
		return "maintain_app";
	}
}
