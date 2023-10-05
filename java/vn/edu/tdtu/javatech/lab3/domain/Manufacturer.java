package vn.edu.tdtu.javatech.lab3.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.validation.constraints.Size;

@Entity
@Table(name="Manufacturer")
@NoArgsConstructor
@Setter @Getter
public class Manufacturer {
    @Id
    private Long id;
    @Column(nullable = false)
    @Size(min = 3, max = 128)
    private String name;
    @Column(nullable = false)
    private String location;
    private Integer employeeNumber;
    @OneToMany(mappedBy = "manufacturer")
    private List<Phone> phoneList;

    public Manufacturer(Long id, String name, String location, Integer employeeNumber) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employeeNumber = employeeNumber;
    }

    public void print() {
        System.out.println("id = " + id + ", name = " + name +  ", location = " + location + ", employeeNumber = " + employeeNumber);
    }
}
