package petsis.pet.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeOng;
    private String respOng;
    private String localOng;
    private String foneOng;
    private String regisOng;
    
    private Boolean deletado;
    private String login;
	private String senha;

    @Column(nullable = true, length = 255)
	private String foto;

    @JoinColumn(name="role_id")
    private Role role;

    @Column(name = "dataCadUse")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadUse;

    public LocalDate getDataCadUse() {
        return dataCadUse;
    }
    public void setDataCadUse(LocalDate dataCadUse) {
        this.dataCadUse = dataCadUse;
    }
    
    public Boolean getDeletado() {
        return deletado;
    }
    public void setDeletado(Boolean deletado) {
        this.deletado = deletado;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNomeOng() {
        return nomeOng;
    }
    public void setNomeOng(String nomeOng) {
        this.nomeOng = nomeOng;
    }
    public String getRespOng() {
        return respOng;
    }
    public void setRespOng(String respOng) {
        this.respOng = respOng;
    }
    public String getLocalOng() {
        return localOng;
    }
    public void setLocalOng(String localOng) {
        this.localOng = localOng;
    }
    public String getFoneOng() {
        return foneOng;
    }
    public void setFoneOng(String foneOng) {
        this.foneOng = foneOng;
    }
    public String getRegisOng() {
        return regisOng;
    }
    public void setRegisOng(String regisOng) {
        this.regisOng = regisOng;
    }

    @OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name= "usuario_role",
			joinColumns = @JoinColumn(name = "usuario.id",
				referencedColumnName = "id",
				table = "usuario"),//cria tabela de acesso do usuario

				inverseJoinColumns = @JoinColumn(name="role_id",
					referencedColumnName = "id",
					table = "role"))//cria tabela de acesso do usuario
				
		
    private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role>roles) {
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
    
    public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	@Transient
	public String getFotoImagePath() {
		if(foto == null ) return  null;
		
		return "/petsisFotos/user/**" + id + "/" + foto;
	}
}
