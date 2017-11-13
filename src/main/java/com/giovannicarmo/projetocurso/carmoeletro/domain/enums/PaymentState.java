package com.giovannicarmo.projetocurso.carmoeletro.domain.enums;

public enum PaymentState {

    PENDENTE (1, "Pendente"),
    QUITADO (2, "Quitado"),
    CANCELADO (3, "Cancelado");

    private Integer id;
    private String description;

    PaymentState(Integer id, String description) {
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

    public static PaymentState toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        for (PaymentState x : PaymentState.values()) {

            if (id.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + id);
    }
}
