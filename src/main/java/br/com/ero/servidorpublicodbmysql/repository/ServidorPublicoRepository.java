package br.com.ero.servidorpublicodbmysql.repository;

import br.com.ero.servidorpublicodbmysql.entity.ServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorPublicoRepository extends CrudRepository<ServidorPublico, Long> {
}
