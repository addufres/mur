package com.tdg.mur.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import lombok.AllArgsConstructor;
 
@Service
@AllArgsConstructor
public class MailBuilder {

	private final TemplateEngine templateEngine;
	
	String build(String message) {
		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process("mailTemplate", context);
	}
}
