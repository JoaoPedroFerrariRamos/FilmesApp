package FilmesApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import FilmesApp.Models.Filmes;
import FilmesApp.Repository.FilmeReposiroty;

@Controller
public class FilmesController {

	@Autowired
	private FilmeReposiroty fr;

	@RequestMapping(value = "/cadastrarFilme", method = RequestMethod.GET)
	public String form() {
		return "evento/formFilmes";
	}

	@RequestMapping(value = "/cadastrarFilme", method = RequestMethod.POST)
	public String form(Filmes filme) {

		fr.save(filme);

		return "redirect:/cadastrarFilme";
	}
	
	@RequestMapping("/filmes")
	public ModelAndView listaFilmes() {
		ModelAndView mv = new ModelAndView("/index");
		Iterable<Filmes> filmes = fr.findAll();
		mv.addObject("filmes", filmes);
		return mv;
	}
	
	public ModelAndView detalhesFilmes(@PathVariable("codigo") long codigo) {
		Filmes filme = fr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesFilmes");
		mv.addObject("filmes", filme);
		return mv;
	}
	
	@RequestMapping("/deletar")
	public String deletarFilme(long codigo) {
		Filmes filme = fr.findByCodigo(codigo);
		fr.delete(filme);
		return "redirect:/filmes";
	}
}
