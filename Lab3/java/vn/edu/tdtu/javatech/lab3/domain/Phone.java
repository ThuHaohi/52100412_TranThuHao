package vn.edu.tdtu.javatech.lab3.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Phone")
@NoArgsConstructor
@Setter @Getter
public class Phone {
    @Id
    private Long id;
    @Column(nullable = false)
    @Size(min = 3, max = 128)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String color;
    private String country;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manufacturer_id")
    private Manufacturer manufacturer;

    public Phone(Long id, String name, String color, String country, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.country = country;
        this.price = price;
        this.quantity = quantity;
    }

    public void print() {
        System.out.println("id = " + id + ", name = " + name + ", price = "
                + price + ", color = " + color + ", country = " + country + ", quantity = " + quantity
        );
    }
}
