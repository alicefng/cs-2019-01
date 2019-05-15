/*
 * Copyright (c) 2019.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.alicefng.cs.aula1.domain;

/**
 * Implementação de algoritomos para validar CPF
 */
public class CPF {

    /**
     * Avalia se uma String contém apenas dígitos
     *
     * @param cpf A string contendo o CPF a ser avaliado
     * @return verdadeiro, se a string contém apenos dígitos
     * falso, se a string contém outros caracteres
     */
    private static boolean sequenciaContemApenasDigitos(final String cpf) {
        return cpf.chars().allMatch(c -> Character.isDigit(c));
    }

    /**
     * Transforma caracteres numéricos em inteiros
     *
     * @param cpf String que contém CPF
     * @return Vetor contendo em cada posição um dígito do CPF como inteiro
     */
    private static int[] converteCaracteresEmInteiros(String cpf) {
        return cpf.chars().map(c -> Character.getNumericValue(c)).toArray();
    }

    /**
     * Avalia se CPF fornecido é válido dado seus dígitos verificados (dois últimos dígitos) - algoritmo 1
     *
     * @param cpf String que contém CPF
     * @return verdadeiro, se dígitos verificadores do CPF estiverem corretos, falso, se não
     * @throws IllegalArgumentException Se argumento é null
     * @throws IllegalArgumentException Se argumento não contém 11 dígitos
     * @throws IllegalArgumentException Se argumento contém algo diferente de dígitos (0 a 9)
     */
    public static boolean validarDigitosCPF(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("argumento é null");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos: " + cpf);
        }
        if (!sequenciaContemApenasDigitos(cpf)) {
            throw new IllegalArgumentException("CPF deve conter somente dígitos (0 a 9): " + cpf);
        }

        int[] digitosCpf = converteCaracteresEmInteiros(cpf);
        int calculoParcialDigito10 = digitosCpf[0];
        int calculoParcialDigito11 = digitosCpf[1];

        for (int i = 1; i < 9; i++) {
            calculoParcialDigito10 += digitosCpf[i] * (i + 1);
        }
        for (int i = 2; i < 10; i++) {
            calculoParcialDigito11 += digitosCpf[i] * i;
        }
        int calculoFinalDigito10 = (calculoParcialDigito10 % 11) % 10;
        int calculoFinalDigito11 = (calculoParcialDigito11 % 11) % 10;

        return calculoFinalDigito10 == digitosCpf[9] & calculoFinalDigito11 == digitosCpf[10];
    }

    /**
     * Avalia se CPF fornecido é válido dado seus dígitos verificados (dois últimos dígitos) - algoritmo 2
     *
     * @param cpf String que contém CPF
     * @return verdadeiro, se dígitos verificadores do CPF estiverem corretos,
     * falso, se não
     * @throws IllegalArgumentException Se argumento é null
     * @throws IllegalArgumentException Se argumento não contém 11 dígitos
     * @throws IllegalArgumentException Se argumento contém algo diferente de dígitos (0 a 9)
     */
    public static boolean validarDigitosCPF2(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("argumento é null");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos: " + cpf);
        }
        if (!sequenciaContemApenasDigitos(cpf)) {
            throw new IllegalArgumentException("CPF deve conter somente dígitos (0 a 9): " + cpf);
        }

        int[] digitosCpf = converteCaracteresEmInteiros(cpf);
        int calculoParcialDigito11 = digitosCpf[8];
        int calculoParcialDigito10 = calculoParcialDigito11;

        for (int i = 7; i >= 0; i--) {
            calculoParcialDigito11 += digitosCpf[i];
            calculoParcialDigito10 += calculoParcialDigito11;
        }
        int calculoFinalDigito10 = (calculoParcialDigito10 % 11) % 10;
        int calculoFinalDigito11 = ((calculoParcialDigito10 - calculoParcialDigito11 + 9 * digitosCpf[9]) % 11) % 10;

        return calculoFinalDigito10 == digitosCpf[9] & calculoFinalDigito11 == digitosCpf[10];
    }

}
