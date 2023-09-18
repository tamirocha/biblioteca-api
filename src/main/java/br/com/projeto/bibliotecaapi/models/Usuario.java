package br.com.projeto.bibliotecaapi.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String cpf;

    @NotNull
    private String nome;

    @NotNull
    private String matricula;

    // OneToMany
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "livro_usuario", joinColumns = {@JoinColumn(name = "codigo_livro")}, inverseJoinColumns = {@JoinColumn(name = "codigo_usuario")})
    private List<Livro> livrosEmprestados = new ArrayList<>();

}
