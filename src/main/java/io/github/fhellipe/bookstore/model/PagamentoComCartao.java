package io.github.fhellipe.bookstore.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.github.fhellipe.bookstore.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;

    public PagamentoComCartao() {}


    public PagamentoComCartao(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PagamentoComCartao that = (PagamentoComCartao) o;
        return Objects.equals(numeroDeParcelas, that.numeroDeParcelas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroDeParcelas);
    }
}
