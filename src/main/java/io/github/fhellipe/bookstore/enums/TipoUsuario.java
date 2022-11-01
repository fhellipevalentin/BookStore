package io.github.fhellipe.bookstore.enums;

public enum TipoUsuario {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int Cod;
    private String descricao;

    TipoUsuario(int cod, String descricao) {
        Cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return Cod;
    }

    public void setCod(int cod) {
        Cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static TipoUsuario toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TipoUsuario x : TipoUsuario.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
