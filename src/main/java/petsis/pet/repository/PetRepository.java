package petsis.pet.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import petsis.pet.model.Formu;
import petsis.pet.model.Pet;

@Repository
@Transactional
public interface PetRepository extends JpaRepository<Pet, Long>{
    @Query("select p from Pet p where p.deletado = 'FALSE' ")
	Page<Pet> findPetByidPage(Pageable pageable);

    @Query("select p from Pet p where p.id = ?1")
	Pet findPetByid(Long id);

    @Query("select p from Pet p where p.deletado = 'FALSE' and p.tag = ?1")
	List<Pet> findPetListTag(String tagList);

    @Query("select p from Pet p where p.deletado = 'FALSE' ")
	List<Pet> findPet();

    @Query("select p from Pet p where p.deletado = 'FALSE' AND p.ongPet.id = ?1 ")
	List<Pet> findPetUser(Long id);


    @Query("select p from Pet p where p.ongPet = ?1")	
    public List<Pet> getPets(Long petid);




}
