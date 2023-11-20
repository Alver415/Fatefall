package com.alver.fatefall.server.controller;

import com.alver.fatefall.core.api.RootApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController implements RootApi {

	@GetMapping("/")
	public String index(){
		return System.currentTimeMillis() + "ms";
	}
}
