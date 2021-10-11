package com.lab.persona.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.persona.model.Datos;
import com.lab.persona.model.Respuesta;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	@GetMapping("/obtienedatos")
	public ResponseEntity<List<Datos>> obtieneDatos() {
		Datos data = new Datos();
		data.setNombre("Benjamin");
		data.setApellido("Escobar");
		data.setEdad(35);
		data.setTelefono("12345678");
		
		List<Datos> list = new ArrayList<>();
		list.add(data);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/nuevovalorp")
	public ResponseEntity<Respuesta> nuevoValorPersona(@RequestBody Datos datos){
		
				
		Respuesta res = new Respuesta();
		res.setId(200);
		res.setRespuesta("Info agregada " + datos.getNombre());
		
		return new ResponseEntity<Respuesta>(res, HttpStatus.OK);
		
	}
	
	
}


