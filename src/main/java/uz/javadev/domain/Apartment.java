package uz.javadev.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.javadev.domain.enums.ApartType;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "apartment")
public class Apartment extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "apart_num", unique = true)
    private Integer apartNum;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "room_count")
    private Integer roomCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ApartType type;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "price")
    private Double price;
}
