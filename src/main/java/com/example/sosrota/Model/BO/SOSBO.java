package com.example.sosrota.Model.BO;


/*
 * Aqui fica as Regras de Negocios, onde devem ficar todas as funções que de
 * fato vão realizar as validações, calculos, verificações e tratamentos.
 */

public class SOSBO {

    public static boolean emailValido(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean loginValidoADM(String email, String senha) {

        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return false;
        }

        // Compara com valores do EnvConfig
        boolean emailCorreto = email.equals(EnvConfig.get("E_ADM"));
        boolean senhaCorreta = senha.equals(EnvConfig.get("S_ADM"));

        return emailCorreto && senhaCorreta;
    }
}
