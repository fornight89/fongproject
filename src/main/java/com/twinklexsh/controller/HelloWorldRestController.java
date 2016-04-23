package com.twinklexsh.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.twinklexsh.domain.Message;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class HelloWorldRestController {
	
	@RequestMapping(value="/hello/{player}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> message(@PathVariable String player) {
 
        Message msg = new Message(player, "Hello " + player);
        List<Message> abc = new ArrayList<Message>();
        abc.add(msg);
        return abc;
    }
	
	@RequestMapping(value="/hello", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> message() {
		System.out.println("=============================================================");
        Message msg = new Message("test", "Hello " + "test");
        List<Message> abc = new ArrayList<Message>();
        abc.add(msg);
        return abc;
    }

}
