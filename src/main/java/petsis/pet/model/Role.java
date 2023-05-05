package petsis.pet.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private long id;

    
    private String nomeRole;

    @Override
    public String getAuthority() { // ROLE_ADMIN, ROLE_USER
        return this.nomeRole;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "nomeRole")
    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

