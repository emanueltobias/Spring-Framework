package com.emanueltobias.drinks.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emanueltobias.drinks.model.Cliente;

@Controller
public class ClientesController {
		
	@RequestMapping("clientes/novo")
	public String novo(Cliente cliente) {
		// model.addAttribute(new Cerveja());
		return "cliente/CadastroCliente";
	}

	@RequestMapping(value = "clientes/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			// model.addAttribute(cerveja);
			// model.addAttribute("mensagem", "Erro formulário");
			return novo(cliente);
		}

		// Salvar no banco de dados...
		System.out.println(">>>> Nome:" + cliente.getNome());
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");
		return "redirect:/clientes/novo";
	}

}
