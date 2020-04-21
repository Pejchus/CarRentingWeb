package tygri.pujcovna.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private AuthorityType name;

    public Authority() {
    }

    public Authority(AuthorityType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorityType getName() {
        return name;
    }

    public void setName(AuthorityType name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (name == AuthorityType.ROLE_ADMIN) {
            return "Admin";
        } else if (name == AuthorityType.ROLE_EMPLOYEE) {
            return "Employee";
        } else {
            return "Customer";
        }
    }
}
