package com.alver.fatefall.controller;

import com.alver.fatefall.api.ApplicationApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApplicationController implements ApplicationApi {

	@GetMapping("/")
	public String index(){
		return System.currentTimeMillis() + "ms";
	}
}
