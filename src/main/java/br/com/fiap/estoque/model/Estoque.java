package br.com.fiap.estoque.model;

import br.com.fiap.produto.model.Produto;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
@Entity
@Table(name = "TB_ESTOQUE")
public class Estoque {

    @Id
    @SequenceGenerator(name = "SQ_ESTOQUE", sequenceName = "SQ_ESTOQUE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_ESTOQUE"
    )
    @Column(name = "ID_ESTOQUE")
    private Long id;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_PRODUTO",
            joinColumns = @JoinColumn(
                    name = "ID_ESTOQUE",
                    referencedColumnName = "ID_ESTOQUE",
                    foreignKey = @ForeignKey(
                            name = "FK_ESTOQUE_PRODUTO")))
    //PRODUTO_ESTOCADO
    private Collection<Produto> produtos = new LinkedHashSet<>();
    @Column(name = "LC_ESTOQUE")

    private String local;

    public Estoque() {
    }

    public Estoque(Collection<Produto> produtos, String local) {
        this.produtos = produtos;
        this.local = local;
    }

    public Collection<Produto> getProdutos() {
        return Collections.unmodifiableCollection(produtos);
    }

    public Estoque addProduto(Produto produto) {
        this.produtos.add(produto);
        return this;
    }

    public Estoque removeProduto(Produto produto) {
        this.produtos.remove(produto);
        return this;
    }

    public String getLocal() {
        return local;
    }

    public Estoque setLocal(String local) {
        this.local = local;
        return this;
    }


    @Override
    public String toString() {
        return "Estoque{" +
                "produtos=" + produtos +
                ", local='" + local + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Estoque setId(Long id) {
        this.id = id;
        return this;
    }
}
