package petsis.pet.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import petsis.pet.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    //QUERY
    @Query("select u from Usuario u where u.deletado = 'FALSE' and  u.login like ?1 and u.senha like ?2")
    Usuario findOngLogin(String login, String senha);

    @Query("select u from Usuario u where u.deletado = 'FALSE' AND u.login = ?1")
	Usuario findUserByLogin(String login);

    @Query("select u from Usuario u where u.deletado = 'FALSE' AND u.nomeOng = ?1")
	Usuario findUserByNomeOng(String nome);
    
    @Query("select u from Usuario u where u.id = ?1")
	Usuario findUserByid(Long id);

    // @Query("select u from Usuario u where u.deletado = 'FALSE' AND u.login = ?1")
	// Usuario findUserByLoginn(String login);

    // @Query("select u from Usuario u where u.deletado = 'FALSE' AND u.nomeOng= ?1")
	// Usuario findUserByNome(String nomeOng);

    // @Query("select u from Usuario u where u.deletado = 'FALSE' AND u.regisOng= ?1")
	// Usuario findUserByRegis(String regisOng);

    @Query("select u from Usuario u where u.deletado = 'FALSE'")
	List<Usuario> findUser();

    @Query("select u from Usuario u where u.deletado = 'FALSE' and u.nomeOng = ?1")
	List<Usuario> findUserListOng(String nomeOngList);
    @Query("select u from Usuario u where u.deletado = 'FALSE' and u.login = ?1")
	List<Usuario> findUserListLogin(String loginOngList);
    @Query("select u from Usuario u where u.deletado = 'FALSE' and u.regisOng = ?1")
	List<Usuario> findUserListRegis(String resgisOngList);


    // @Query("select u from Usuario u where u.nomeOng = ?1")
	// Usuario findUserByName(String nomeOng);

    //LIST
    @Query("select u from Usuario u "		
			+ "where u.deletado = 'FALSE' "
			+ "AND (u.login like ?1) "
            + "AND (u.regisOng like ?1)"
            + "AND (u.nomeOng like ?1)"
            )
    Usuario verificarUsuario(String login, String regisOng, String nomeOng);

    //PAGE
    @Query("select u from Usuario u where u.deletado = 'FALSE' ")
	Page<Usuario> findUseByidPage(Pageable pageable);

    @Query("select u from Usuario u "
			+ "where u.deletado = 'FALSE' "
			+ "AND u.nomeOng like %?1% OR lower(u.nomeOng) like lower(concat('%', ?1,'%')) "
            + "OR u.respOng like %?1% OR lower(u.respOng) like lower(concat('%', ?1,'%')) "
            + "OR u.localOng like %?1% OR lower(u.localOng) like lower(concat('%', ?1,'%')) "
            + "OR u.foneOng like %?1% OR lower(u.foneOng) like lower(concat('%', ?1,'%')) "
            + "OR u.regisOng like %?1% OR lower(u.regisOng) like lower(concat('%', ?1,'%')) "
            // + "OR to_char(u.dataCadUse, 'DD/MM/YYYY') like %?1% OR to_char(u.dataCadUse, 'DD/MM/YYYY') like %?1%"
            + "OR CAST(u.id AS string) like %?1% OR CAST(u.id AS string) like lower(concat('%', ?1,'%')) "
            )
	Page<Usuario> UserPesquisar(String pesquisar, Pageable pageable);
}
