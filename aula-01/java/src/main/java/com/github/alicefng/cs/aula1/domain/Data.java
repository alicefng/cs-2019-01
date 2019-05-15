/*
 * Copyright (c) 2019.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.alicefng.cs.aula1.domain;

/**
 * Implementação do algoritmo para calcular o  dia da semana de acordo com uma data válida fornecida
 */

public class Data {

    /**
     * Valida dia
     *
     * @param dia Inteiro que informa dia do mês
     * @throws DataInvalidaException    Se o dia for inválido
     * @throws IllegalArgumentException Se o argumento for null
     */
    public static void validaDia(int dia) {
        if (dia == null) {
            throw new IllegalArgumentException("argumento é null");
        }
        if (dia < 1 || dia > 31) {
            throw new DataInvalidaException("dia invalido: " + dia);
        }
    }

    /**
     * Valida mês
     *
     * @param mes Inteiro que informa mês do ano
     * @throws DataInvalidaException    Se o mês for inválido
     * @throws IllegalArgumentException Se o argumento for null
     */
    public static void validaMes(int mes) {
        if (mes == null) {
            throw new IllegalArgumentException("argumento é null");
        }
        if (mes < 1 || mes > 12) {
            throw new DataInvalidaException("mes inválido: " + mes);
        }
    }

    /**
     * Valida ano
     *
     * @param ano Inteiro que informa ano
     * @throws DataInvalidaException    Se o ano for inválido
     * @throws IllegalArgumentException Se o argumento for null
     */
    public static void validaAno(int ano) {
        if (ano == null) {
            throw new IllegalArgumentException("argumento é null");
        }
        if (ano < 1754) {
            throw new DataInvalidaException("ano inválido: " + ano);
        }
    }

    /**
     * Transforma inteiros (0 a 6) em strings correspondetes aos dias da semana: "segunda-feira" (índice 0),
     * "terça-feira" (índice 1) e assim sucessivamente, até "domingo" (índice 6).
     *
     * @param dia Inteiro que representa o dia da semana (0 = segunda, 1 = terça, e assim sucessivamente)
     * @return String que representa o nome do dia da semana
     * @throws IllegalArgumentException se o dia informado estiver fora do amplitude
     */
    private static String diaDaSemanaToString(int dia) {
        if (dia < 0 || dia > 6) {
            throw new IllegalArgumentException("Dia inválido: " + dia + ". Dia deve estar entre 0 e 6.");
        }

        String nomesDiasDaSemana[] = {"segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira",
                "sábado", "domingo"};

        return nomesDiasDaSemana[dia];
    }

    /**
     * Calcula o dia da semana equivalente a data fornecida
     *
     * @return O nome do dia da semana correspondente a data fornecida
     */
    public static String diaDaSemana(int dia, int mes, int ano) {
        validaDia(dia);
        validaMes(mes);
        validaAno(ano);

        if (mes == 1 | mes == 2) {
            mes = mes + 12;
            ano = ano - 1;
        }
        int calculoDiaDaSemanaParcial = dia + 2 * mes + 3 * (mes + 1) / 5 + ano + ano / 4 - ano / 100 + ano / 400;
        int calculoDiaDaSemanaFinal = calculoDiaDaSemanaParcial % 7;

        return diaDaSemanaToString(calculoDiaDaSemanaFinal);
    }

}