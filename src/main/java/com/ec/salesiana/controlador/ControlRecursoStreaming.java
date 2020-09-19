package com.ec.salesiana.controlador;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class ControlRecursoStreaming extends ResourceHttpRequestHandler {
	
	final static String ATRIBUTO = ControlRecursoStreaming.class.getName() + ".file";

	@Override
	protected Resource getResource(HttpServletRequest request) throws IOException {

		final File file = (File) request.getAttribute(ATRIBUTO);
		return new FileSystemResource(file);
	}

}