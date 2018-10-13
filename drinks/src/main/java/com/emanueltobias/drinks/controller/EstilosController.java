package com.emanueltobias.drinks.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emanueltobias.drinks.model.Estilo;
import com.emanueltobias.drinks.service.CadastroEstiloService;
import com.emanueltobias.drinks.service.excepition.NomeEstiloJaCadastradoExcepition;

@Controller
@RequestMapping("/estilos")
public class EstilosController {

	@Autowired
	private CadastroEstiloService cadastroEstiloService;

	@RequestMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");

		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			// model.addAttribute(cerveja);
			// model.addAttribute("mensagem", "Erro formulário");
			return novo(estilo);
		}

		// Salvar no banco de dados...
		try {
			cadastroEstiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoExcepition e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estilo);
		}

		System.out.println(">>> estilo: " + estilo.getNome());
		cadastroEstiloService.salvar(estilo);
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso !");
		return new ModelAndView("redirect:/estilos/novo");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}

		estilo = cadastroEstiloService.salvar(estilo);
		return ResponseEntity.ok(estilo);

	}

}
