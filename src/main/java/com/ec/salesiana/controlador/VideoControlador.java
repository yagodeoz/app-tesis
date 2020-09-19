package com.ec.salesiana.controlador;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/video")
public class VideoControlador {

	@Autowired
	private ControlRecursoStreaming handler;

	private final static File FILE_MATRIZ = new File("E:\\video");

	private final static String DIR_MATRIZ = "E:\\video\\";

	// supports byte-range requests
	@GetMapping("/index")
	public String home() {

		return "index";
	}

	@GetMapping("/listaCamaras")
	public List<String> listaCamaras(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File[] listado = FILE_MATRIZ.listFiles();
		List<String> lResultado = new ArrayList<String>();
		for (File archivo : listado) {
			if (archivo.isDirectory())
				lResultado.add(archivo.getName());
		}
		return lResultado;
	}

	@GetMapping("/listaDirCamaras/{camara}")
	public List<String> listaCamaras(@PathVariable("camara") String camara, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		File[] listado = new File(DIR_MATRIZ + camara).listFiles();
		List<String> lResultado = new ArrayList<String>();
		for (File archivo : listado) {
			if (archivo.isDirectory())
				lResultado.add(archivo.getName());
		}
		return lResultado;
	}

	@GetMapping("/listaVideo/{camara}/{dir}")
	public List<String> listaVideo(@PathVariable("camara") String camara, @PathVariable("dir") String directorio,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		File[] listado = new File(DIR_MATRIZ + camara + "\\" + directorio).listFiles();
		List<String> lResultado = new ArrayList<String>();
		if (listado != null) {
			for (File archivo : listado) {
				if (archivo.isFile())
					lResultado.add(archivo.getName());
			}
		}
		return lResultado;
	}

	// supports byte-range requests
	@GetMapping("/verVideo/{camara}/{dir}/{rutaVideo}")
	public void verVideo(@PathVariable("camara") String camara, @PathVariable("dir") String directorio, @PathVariable("rutaVideo") String rutaVideo, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String lRutaFinal = DIR_MATRIZ + camara + "\\" + directorio + "\\" + rutaVideo;
		
		System.out.println("VIDEO "  + lRutaFinal);
		
		request.setAttribute(ControlRecursoStreaming.ATRIBUTO, new File(lRutaFinal));
		handler.handleRequest(request, response);
	}

}
