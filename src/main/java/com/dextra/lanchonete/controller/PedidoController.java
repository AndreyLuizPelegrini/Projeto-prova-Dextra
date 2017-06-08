package com.dextra.lanchonete.controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dextra.lanchonete.model.Ingredientes;
import com.dextra.lanchonete.model.Pedido;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	private Pedido pedido;
	double valorTotal;
	private Map<String, ArrayList<Ingredientes>> mapaLanches;

	public PedidoController() {
		pedido = new Pedido();
		mapaLanches = getMap();
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroPedido");
		mv.addObject("todosIngredientes", Ingredientes.values());
		mv.addObject("opcoesLanches", mapaLanches);
		return mv;
	}

	@RequestMapping(value = "/ingrediente", method = RequestMethod.POST)
	public ModelAndView adicionarIngrediente(Ingredientes ingrediente) throws ParseException {

		pedido.adicionarIngredientes(ingrediente);
		valorTotal = pedido.getValorTotalPedido();

		valorTotal = formataValorPedido(valorTotal);

		ModelAndView mv = populaValores(valorTotal);
		mv.addObject("valorPedido", valorTotal);

		return mv;
	}

	@RequestMapping(value = "/lanche", method = RequestMethod.POST)
	public ModelAndView adicionarLanche(String lanche) throws ParseException {
		List<Ingredientes> listaDeIngredientes = new ArrayList<>();
		for (String i : mapaLanches.keySet()) {
			if (i.equals(lanche))
				listaDeIngredientes.addAll(mapaLanches.get(i));
		}

		for (Ingredientes i : listaDeIngredientes) {
			valorTotal += i.getValor();
			pedido.adicionarIngredientes(i);
		}

		valorTotal = formataValorPedido(valorTotal);

		ModelAndView mv = populaValores(valorTotal);
		mv.addObject("valorPedido", valorTotal);
		return mv;
	}

	private double formataValorPedido(double valorTotal) throws ParseException {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		String s = nf.format(valorTotal);
		return nf.parse(s).doubleValue();
	}

	private ModelAndView populaValores(double valorFinal) {
		ModelAndView mv = new ModelAndView("CadastroPedido");
		mv.addObject("todosIngredientes", Ingredientes.values());
		mv.addObject("listaIngredientes", pedido.getIngredientes());
		mv.addObject("opcoesLanches", mapaLanches);
		mv.addObject("valorPedido", valorFinal);
		return mv;
	}

	private Map<String, ArrayList<Ingredientes>> getMap() {
		Map<String, ArrayList<Ingredientes>> mapaLanches = new HashMap<String, ArrayList<Ingredientes>>();
		mapaLanches.put("X-Bacon",
				new ArrayList<>(Arrays.asList(Ingredientes.BACON, Ingredientes.HAMBURGER, Ingredientes.QUEIJO)));
		mapaLanches.put("X-Burger", new ArrayList<>(Arrays.asList(Ingredientes.HAMBURGER, Ingredientes.QUEIJO)));
		mapaLanches.put("X-Egg",
				new ArrayList<>(Arrays.asList(Ingredientes.OVO, Ingredientes.HAMBURGER, Ingredientes.QUEIJO)));
		mapaLanches.put("X-Egg Bacon", new ArrayList<>(
				Arrays.asList(Ingredientes.OVO, Ingredientes.BACON, Ingredientes.HAMBURGER, Ingredientes.QUEIJO)));
		return mapaLanches;
	}

}
