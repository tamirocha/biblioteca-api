package br.com.projeto.bibliotecaapi.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "livro")

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String titulo;

    @NotNull
    private String autor;

    private Long quantidadeTotal = 0l;

    private Long quantidadeDisponivel = 0l;

    private int numeroPaginas;

    @NotNull
    private boolean disponibilidade = true;

    private LocalDate dataEntrega;

    @ManyToMany(mappedBy = "livrosEmprestados", cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();

    public void decrementarQuantidadeDisponivel() {

        quantidadeDisponivel--;
    }

    public void incrementarQuantidadeDisponivel() {

        quantidadeDisponivel++;
    }

}
