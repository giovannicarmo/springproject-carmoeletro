package com.giovannicarmo.projetocurso.carmoeletro.domain.enums;

public enum ClientType {

    PESSOAFISICA (1, "Pessoa Fisica"),
    PESSOAJURIDICA (2, "Pessoa Juridica");

    private int id;
    private String description;

    ClientType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ClientType toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (ClientType x : ClientType.values()) {

            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + id);
    }
}
