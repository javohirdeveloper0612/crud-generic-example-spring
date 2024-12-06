package uz.javadev.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.javadev.utils.Constants;

import java.io.Serializable;

@Data
@Entity
@Table(name = Constants.USERS)
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "about", columnDefinition = "text")
    private String about;
}
