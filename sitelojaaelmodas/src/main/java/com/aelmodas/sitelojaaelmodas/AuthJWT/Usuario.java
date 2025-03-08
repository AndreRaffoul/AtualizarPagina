package com.aelmodas.sitelojaaelmodas.AuthJWT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	//@JsonIgnore
	private String senha;	
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "unique_role_user", uniqueConstraints = @jakarta.persistence.UniqueConstraint(
			columnNames = {"usuario_id", "role_id"}, name = "unique_role_user"),
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario", unique = false,
			foreignKey = @jakarta.persistence.ForeignKey(name = "usuario_fk", value = jakarta.persistence.ConstraintMode.CONSTRAINT)),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role", unique = false,
			foreignKey = @jakarta.persistence.ForeignKey(name = "role_fk", value = jakarta.persistence.ConstraintMode.CONSTRAINT)))	
    private List<Role> roles;
	
	public Usuario() { 
		 if (this.roles == null) {
	        this.roles = new ArrayList<>();
	    }
	}
	
	public Usuario(Long id, String login, String email, String senha, List<Role> roles) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNomeRole()))
	            .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, roles, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(roles, other.roles) && Objects.equals(senha, other.senha);
	}
	
}
