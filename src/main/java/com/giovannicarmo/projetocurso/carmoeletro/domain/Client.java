package com.giovannicarmo.projetocurso.carmoeletro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.ClientType;
import com.giovannicarmo.projetocurso.carmoeletro.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;

    private String cpfOrCnpj;
    private Integer type;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telephones = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType type,
                  String password) {
        super();
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.type = (type == null) ? null : type.getId();
        this.password = password;
        addProfile(Profile.CLIENT);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public ClientType getType() {
        return ClientType.toEnum(type);
    }

    public void setType(ClientType type) {
        this.type = type.getId();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getId());
    }

    public Set<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(Set<String> telephones) {
        this.telephones = telephones;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
