package fu.hanoi.swp.VDShop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "provinces")
public class Province extends BaseEntity {
    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "province")
    private Set<UserAddress> userAddresses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "province")
    private Set<Ward> wards = new LinkedHashSet<>();

}