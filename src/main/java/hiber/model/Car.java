package hiber.model;

import javax.persistence.*;

// создаем машину

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column
    private Long id;

    @Column
    private String model;

    @Column
    private int series;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Car() {}

    public Car(String model, int series, User user) {
        this.model = model;
        this.series = series;
        this.user = user;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}