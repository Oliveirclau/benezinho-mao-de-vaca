package br.com.fiap.produto.model;

import br.com.fiap.fornecedor.model.Fornecedor;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TB_PRODUTO")
@DiscriminatorColumn(name = "TP_PRODUTO")
public abstract class Produto {

    @Id
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", allocationSize = 1, initialValue = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_PRODUTO"
    )
    @Column(name = "ID_PRODUTO")
    Long id;

    @Column(name = "NM_PRODUTO")
    private String nome;

    @Column(name = "DS_PRODUTO")
    private String descricao;

    @Column(name = "PR_PRODUTO")
    private double preco;
    @Column(name = "DT_FABRICACAO")
    private LocalDateTime fabricacao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FORNECEDOR",
            referencedColumnName = "ID_FORNECEDOR",
            foreignKey = @ForeignKey(name = "FK_PRODUTO_FORNECEDOR")
    )
    private Fornecedor fornecedor;


    public Produto() {
    }

    public Produto(String nome, String descricao, double preco, LocalDateTime fabricacao, Fornecedor fornecedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.fabricacao = fabricacao;
        this.fornecedor = fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDateTime getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(LocalDateTime fabricacao) {
        this.fabricacao = fabricacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", fabricacao=" + fabricacao +
                ", fornecedor=" + fornecedor +
                '}';
    }
}
