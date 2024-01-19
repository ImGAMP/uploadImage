package org.example.jsp.controller;

import org.example.jsp.model.Image;
import org.example.jsp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Controller
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping("/upload")
	public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
		if (!file.isEmpty()) {
			try {
				Image image = new Image();
				image.setImageBase64(convertToBase64(file.getBytes())); // Convierte la imagen a base64

				// Procesa la imagen utilizando el servicio de ImageService
				image = imageService.processImage(image);

				// Aquí puedes convertir el resultado en JSON si es necesario
				String jsonResult = "{\"message\": \"Procesamiento completado\"}";

				// Agrega la imagen procesada y el JSON al modelo para mostrarlos en la vista result.jsp
				model.addAttribute("image", image);
				model.addAttribute("jsonResult", jsonResult);

				return "result"; // Nombre de la vista result.jsp
			} catch (Exception e) {
				// Manejo de errores
				return "error"; // Puedes crear una vista de error
			}
		} else {
			// Manejo de errores si el archivo está vacío
			return "error"; // Puedes crear una vista de error
		}
	}

	// Método de utilidad para convertir bytes a base64
	private String convertToBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
}
