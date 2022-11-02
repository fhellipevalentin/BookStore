package io.github.fhellipe.bookstore.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // subtipo
public class ItemPedidoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    // referencia para pedido
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    //referencia para livro
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public ItemPedidoPK(){}

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return Objects.equals(pedido, that.pedido) && Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, livro);
    }
}
