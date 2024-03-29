package io.github.fhellipe.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime instante;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id")
    private Endereco enderecoEntrega;

    // as classes não estão diretamente associadas, portanto usa-se a ligação entre eles
    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {

    }

    public Pedido(Integer id, LocalDateTime instante, Usuario usuario, Endereco enderecoEntrega) {
        this.id = id;
        this.instante = instante;
        this.usuario = usuario;
        this.enderecoEntrega = enderecoEntrega;
    }

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido{");
        DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        sb.append("Pedido Nº: ").append(getId());
        sb.append(getId());
        sb.append(", Instante: ").append(getInstante().format(formatadorBarra));
        sb.append(", Usuario: ");
        sb.append(getUsuario().getNome());
        sb.append(", Situação do Pagamento=").append(getPagamento().getEstadoPagamento().getDesc());
        sb.append("\nDetalhes:\n");
        for (ItemPedido item: getItens()) {
            sb.append(item.toString());
        }
        sb.append("Valor Total: ").append(nf.format(getValorTotal()));
        return sb.toString();
    }
}
