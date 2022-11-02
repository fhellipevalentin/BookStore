package io.github.fhellipe.bookstore.model;

import io.github.fhellipe.bookstore.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PagamentoComBoleto extends Pagamento{
    private LocalDateTime dataDePagamento;
    private LocalDateTime dataDeVencimento;

    public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, LocalDateTime dataDePagamento, LocalDateTime dataDeVencimento) {
        super(id, estadoPagamento, pedido);
        this.dataDePagamento = dataDePagamento;
        this.dataDeVencimento = dataDeVencimento;
    }

    public PagamentoComBoleto() {

    }

    public LocalDateTime getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDateTime dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public LocalDateTime getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDateTime dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PagamentoComBoleto that = (PagamentoComBoleto) o;
        return Objects.equals(dataDePagamento, that.dataDePagamento) && Objects.equals(dataDeVencimento, that.dataDeVencimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dataDePagamento, dataDeVencimento);
    }
}
