package com.giovannicarmo.projetocurso.carmoeletro.domain.enums;

public enum Profile {

    ADIMIN (1, "ROLE_ADMIN"),
    CLIENT (2, "ROLE_CLIENT");

    private int id;
    private String description;

    Profile(int id, String description) {
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

    public static Profile toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (Profile x : Profile.values()) {

            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + id);
    }
}
