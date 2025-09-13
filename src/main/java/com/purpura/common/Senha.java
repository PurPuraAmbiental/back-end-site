package com.purpura.common;

// classe para diferenciar senha de String comum
public class Senha {
    private String senha;

    public Senha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    // verifica igualdade
    public boolean equals(String senha) {
        return this.senha.equals(senha);
    }
}
