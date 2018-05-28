package FilmesApp.Repository;

import org.springframework.data.repository.CrudRepository;

import FilmesApp.Models.Filmes;

public interface FilmeReposiroty extends CrudRepository<Filmes, String> {
	Filmes findByCodigo(long codigo);
}
