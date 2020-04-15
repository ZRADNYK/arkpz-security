package ua.nure.arkpz.security.model;

import lombok.*;
import net.sf.oval.constraint.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.nure.arkpz.security.util.validator.constraints.CheckUniqueMail;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usr")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    @Range(min = Long.MIN_VALUE, max = Long.MAX_VALUE, message = "Id is out of possible range")
    private Long userId;

    @Column(name = "firstName", length = 45, nullable = true)
    @Length(min = 2, max = 45, message = "First name is out of possible length (2 to 45)")
    @MatchPattern(pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Incorrect symbols in first name")
    private String firstName;

    @Column(name = "lastName", length = 45, nullable = true)
    @Length(min = 2, max = 45, message = "Last name is out of possible length (2 to 45)")
    @MatchPattern(pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Incorrect symbols in last name")
    private String lastName;

    /*@Column(name = "username", length = 45, nullable = false)
    @Length(min = 4, max = 45, message = "Username is out of possible length (4 to 45)")
    @MatchPattern(pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "Incorrect symbols in username")
    @CheckWith(value = CheckUniqueMail.class, message = "Username is not unique, choose other")
    private String username;*/


    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotNull(message = "Please enter the mail")
    @NotEmpty
    @Length(min = 2, max = 100, message = "Email is out of possible length (2 to 100)")
    @MatchPattern(pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
            "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
            "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)" +
            "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "Incorrect email format")
    @CheckWith(value = CheckUniqueMail.class, message = "Email is not unique, choose other")
    private String email;

    @Column(name = "password", length = 450, nullable = false)
    @NotNull(message = "Please enter the password")
    @NotEmpty
    private String password;


    @Column(name = "token")
    private String token;

    @Column(name = "tokenExpirationDate")
    private Date tokenExpirationDate;

    @Column(name = "birthDate")
    @NotNull
    private Date birthDate;

    // private Set<Role> roles;
    @Column(name = "isAccountNonExpired")
    private Boolean isAccountNonExpired;

    @Column(name = "isAccountNonLocked")
    private Boolean isAccountNonLocked;

    @Column(name = "isCredentialsNonExpired")
    private Boolean isCredentialsNonExpired;

    @Column(name = "isEnabled")
    private Boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        // return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getUserId() {
        return Optional.ofNullable(userId).orElse(0L);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getUsername() {
        return Optional.ofNullable(email).orElse("");
    }

    public void setUsername(String username) {
        this.email = username;
    }

    public String getEmail() {
        return  Optional.ofNullable(email).orElse("");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenExpirationDate() {
        return tokenExpirationDate;
    }

    public void setTokenExpirationDate(Date tokenExpirationDate) {
        this.tokenExpirationDate = tokenExpirationDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
