package petsis.pet.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import petsis.pet.model.Formu;

@Repository
@Transactional
public interface FormuRepository  extends JpaRepository<Formu, Long>{

    @Query("select f from Formu f where f.deletado = 'FALSE' and f.cod = ?1")
	List<Formu> findFormuListCod(String codList);
}
