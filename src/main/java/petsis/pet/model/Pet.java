package petsis.pet.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Pet implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomePet;
    
    @ForeignKey(name="ongPet")
	@ManyToOne
    private Usuario ongPet;
    
    public Usuario getOngPet() {
        return ongPet;
    }

    public void setOngPet(Usuario ongPet) {
        this.ongPet = ongPet;
    }

    private String sexoPet;
    private String tipoPet;

    @Column(columnDefinition  = "text")
    private String txtPet;

    private String tag;
    private String fotoPet;

    @Column(name = "dataCadPet")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadPet;


    public LocalDate getDataCadPet() {
        return dataCadPet;
    }

    public void setDataCadPet(LocalDate dataCadPet) {
        this.dataCadPet = dataCadPet;
    }

    private Boolean deletado; 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getSexoPet() {
        return sexoPet;
    }

    public void setSexoPet(String sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(String tipoPet) {
        this.tipoPet = tipoPet;
    }

    public String getTxtPet() {
        return txtPet;
    }

    public void setTxtPet(String txtPet) {
        this.txtPet = txtPet;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFotoPet() {
        return fotoPet;
    }

    public void setFotoPet(String fotoPet) {
        this.fotoPet = fotoPet;
    }

    public Boolean isDeletado() {
        return deletado;
    }

    public void setDeletado(Boolean deletado) {
        this.deletado = deletado;
    }

}
