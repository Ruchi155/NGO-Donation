package com.example.demo.models; 
import java.io.Serializable;
import java.util.Collection; 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString; 
@Data
@Entity(name = "Users")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}), name = "Users")
@AllArgsConstructor
@Builder
@NoArgsConstructor 
@ToString
@EqualsAndHashCode 
public class Users implements Serializable{  
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
    private Long id; 
    private String firstName;
    private String lastName;
    @Column(name = "email")
    private String email;
    private String password; 
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles; 
    
    @OneToOne(fetch = FetchType.EAGER, 
			 cascade = CascadeType.ALL  )
	@JoinColumn(name = "profile_id", referencedColumnName = "id" , nullable =  false)
	@JsonManagedReference  
    private UserProfile userProfile;
    
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
 

}


