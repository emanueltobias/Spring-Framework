package com.emanueltobias.drinks.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emanueltobias.drinks.model.Estilo;

@Controller
public class EstilosController{

	@RequestMapping("estilos/novo")
	public String novo(Estilo estilo) {
		// model.addAttribute(new Cerveja());
		return "estilo/CadastroEstilo";
	}

	@RequestMapping(value = "estilos/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			// model.addAttribute(cerveja);
			// model.addAttribute("mensagem", "Erro formulário");
			return novo(estilo);
		}

		// Salvar no banco de dados...
		System.out.println(">>>> Estilo:" + estilo.getNome());
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
		return "redirect:/usuarios/novo";
	}


}
