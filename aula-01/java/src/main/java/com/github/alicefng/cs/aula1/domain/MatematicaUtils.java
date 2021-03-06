/*
 * Copyright (c) 2019.
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.alicefng.cs.aula1.domain;

import java.util.stream.IntStream;

/**
 * Implementação de várias operações matemáticas básicas.
 */
public final class MatematicaUtils {

    /**
     * Previne a classe utilitária de ser instanciada.
     */
    private MatematicaUtils() {
    }

    /**
     * Produz o resto da divisão entre dois numeros inteiros.
     *
     * @param numerador   O numerador da divisão
     * @param denominador O denominador da divisão
     * @return O resto obtido na divisão: numerador/denominador
     * @throws IllegalArgumentException Se o numerador for menor ou igual 0 ou
     *                                  se o denominador for menor que 0
     */
    public static int restoDaDivisaoInteira(final int numerador,
                                            final int denominador) {
        final int limiteMinimoNum = 1;
        final int limiteMinimoDen = 0;
        if (numerador <= limiteMinimoNum || denominador < limiteMinimoDen) {
            throw new IllegalArgumentException(
                    String.format("numerador: %d, denominador: %d",
                            numerador, denominador));
        }

        int resto = numerador;
        while (resto >= denominador) {
            resto = resto - denominador;
        }

        return resto;
    }

    /**
     * Resulta no produto entre o primeiro e o segundo argumento.
     *
     * @param multiplicando O número a ser multiplicado
     * @param multiplicador O número a multiplicar
     * @return O produto de dois numeros: multiplicando*multiplicador
     * @throws IllegalArgumentException Se o multiplicando ou multiplicador
     *                                  for menor que 0
     */
    public static int produto(final int multiplicando,
                              final int multiplicador) {
        final int limiteMinimo = 0;
        if (multiplicando < limiteMinimo || multiplicador < limiteMinimo) {
            throw new IllegalArgumentException(
                    String.format("multiplicando: %d, multiplicador %d",
                            multiplicando, multiplicador));
        }

        final int totalParcelas = multiplicador < multiplicando
                ? multiplicador
                : multiplicando;
        final int parcela = multiplicador < multiplicando
                ? multiplicando
                : multiplicador;

        int produto = 0;
        for (int i = 1; i <= totalParcelas; i++) {
            produto = produto + parcela;
        }
        return produto;
    }

    /**
     * Produz o resultado da exponenciação, considerando base e expoente
     * inteiros.
     *
     * @param base     A base da exponenciação
     * @param expoente O expoente da exponenciação
     * @return O resultado da base elevada ao expoente
     * @throws IllegalArgumentException Se base ou expoente forem menores que 0
     */
    public static int potencia(final int base, final int expoente) {
        final int limiteMinimo = 0;
        if (base < limiteMinimo || expoente < limiteMinimo) {
            throw new IllegalArgumentException(String.format(
                    "base: %d, expoente: %d", base, expoente));
        }

        int potencia = 1;
        for (int i = 1; i <= expoente; i++) {
            potencia = produto(potencia, base);
        }

        return potencia;
    }

    /**
     * Verifica se um número está contido em um intervalo fechado.
     *
     * @param numero            O número a ser avaliado
     * @param limiteInferior O limite inferior a ser considerado
     * @param limiteSuperior O limite superior a ser considerado
     * @return Verdadeiro, se o número está dentro do intervalo fornecido,
     * Falso, caso esteja fora do intervalo
     */
    public static boolean contidoNoIntervalo(final int numero,
                                             final int limiteInferior,
                                             final int limiteSuperior) {
        return numero >= limiteInferior && numero <= limiteSuperior;
    }

    /**
     * Verifica se o argumento satisfaz a propriedade matemática 3025.
     * Essa propriedade é tal que 30 + 25 = 55 e o quadrado desta soma resulta
     * no próprio número, 55^2 = 3025.
     *
     * @param numero O número a ser avaliado
     * @return Verdadeiro, se o número satisfaz a propriedade
     * Falso, caso não
     * @throws IllegalArgumentException Se parâmetro está fora do intervalo
     *                                  permitido [0,9999]
     */
    public static boolean satisfazPropriedade3025(final int numero) {
        final int intervaloMinino = 0;
        final int intervaloMaximo = 9999;
        if (!contidoNoIntervalo(numero, intervaloMinino, intervaloMaximo)) {
            throw new IllegalArgumentException(String.format("numero: %d",
                    numero));
        }

        final int quociente = numero / 100;
        final int resto = restoDaDivisaoInteira(numero, 100);
        final int resultado = potencia(quociente + resto, 2);

        return numero == resultado;
    }

    /**
     * Verifica se o argumento satisfaz a propriedade matemática 153.
     * Essa propriedade é tal que a soma do cubo dos dígitos do número
     * resulta no próprio número. Exemplo numero 153: 1^3 + 5^3 + 3^3 = 153.
     *
     * @param numero O número a ser avaliado
     * @return Verdadeiro, se o número satisfaz a propriedade,
     * Falso, caso não
     * @throws IllegalArgumentException Se parâmetro está fora do intervalo
     *                                  permitido [100,999]
     */
    public static boolean satisfazPropriedade153(final int numero) {
        final int intervaloMinino = 100;
        final int intervaloMaximo = 999;
        if (!contidoNoIntervalo(numero, intervaloMinino, intervaloMaximo)) {
            throw new IllegalArgumentException(String.format("numero: %d",
                    numero));
        }

        final int primeiroDigito = numero / 100;
        final int resto = restoDaDivisaoInteira(numero, 100);
        final int segundoDigito = resto / 10;
        final int terceiroDigito = restoDaDivisaoInteira(resto, 10);
        final int resultado = potencia(primeiroDigito, 3)
                + potencia(segundoDigito, 3) + potencia(terceiroDigito, 3);

        return resultado == numero;
    }

    /**
     * Produz a soma dos números naturais, iniciando em 0 até o argumento.
     *
     * @param numero O número até o qual deve-se produzir a soma dos naturais
     * @return A soma dos naturais
     * @throws IllegalArgumentException Se parâmetro for menor que 1
     */
    public static int somaDosPrimeirosNaturais(final int numero) {
        final int limiteMinimo = 1;
        if (numero < limiteMinimo) {
            throw new IllegalArgumentException(String.format("numero: %d",
                    numero));
        }

        return IntStream.range(1, numero + 1).sum();
    }

    /**
     * Produz o fatorial do argumento.
     *
     * @param numero O número que se quer calcular o fatorial
     * @return A fatoração do argumento
     * @throws IllegalArgumentException Se parâmetro for menor que 1
     */
    public static int fatorial(final int numero) {
        final int limiteMinimo = 1;
        if (numero < limiteMinimo) {
            throw new IllegalArgumentException(String.format("numero: %d",
                    numero));
        }

        int fatorial = 1;
        for (int i = 2; i <= numero; i++) {
            fatorial = fatorial * i;
        }

        return fatorial;
    }

    /**
     * Produz o valor de Pi de acordo com a precisão informada no argumento.
     *
     * @param precisao Valor que representa a precisão desejada (quanto maior,
     *                 maior a precisão de Pi)
     * @return O valor de pi, dada a precisão
     * @throws IllegalArgumentException Se precisao for menor que 1
     */
    public static double pi(final int precisao) {
        final int limiteMinimo = 1;
        if (precisao < limiteMinimo) {
            throw new IllegalArgumentException(String.format("precisao: %d",
                    precisao));
        }

        double pi = 0;
        double s = -1;
        double d = -1;

        for (int i = 1; i <= precisao; i++) {
            final int constante = 4;
            d = d + 2;
            s = -s;
            pi = pi + constante * s / d;
        }

        return pi;
    }

    /**
     * Produz o logaritmo natural (base número de euler) de um expoente
     * fornecido, e a precisão desejada.
     *
     * @param expoente O expoente para o qual se quer calcular o logaritmo
     *                 natural
     * @param precisao A precisão desejada no resultado do logaritmo calculado
     *                 (quanto maior o valor, maior a precisão)
     * @return Resultado do logaritmo natural, dado o expoente e precisão
     * informadas
     * @throws IllegalArgumentException Se expoente for menor que 1 ou se
     *                                  precisao for menor que 2
     */
    public static double logaritmoNatural(final double expoente,
                                          final double precisao) {
        if (expoente < 1 || precisao < 2) {
            throw new IllegalArgumentException(
                    String.format("expoente: %f, precisao: %f",
                            expoente, precisao));
        }

        double logaritmoNatural = 1 + expoente;
        double numerador = expoente;
        double denominador = 1;

        for (double i = 2; i <= precisao; i++) {
            numerador = numerador * numerador;
            denominador = denominador + i;
            logaritmoNatural = logaritmoNatural + numerador / denominador;
        }

        return logaritmoNatural;
    }

    /**
     * Produz a razão áurea considerando dois números inteiros positivos.
     *
     * @param numeroA  O primero inteiro a ser considerado
     * @param numeroB  O segundo inteiro a ser considerado
     * @param precisao A precisão desejada do resultado retornado, quanto maior
     *                 o valor, maior a precisão
     * @return A razão áurea de acordo com a precisão informada
     * @throws IllegalArgumentException Se o numeroA for menor que 0 ou
     *                                  maior/igual ao numeroB ou se a precisão
     *                                  é menor ou igual a 0
     */
    public static double razaoAurea(final double numeroA, final double numeroB,
                                    final double precisao) {
        final int limiteMinimo = 0;
        if (numeroA < limiteMinimo || numeroA >= numeroB
                || precisao <= limiteMinimo) {
            throw new IllegalArgumentException(
                    String.format("numeroA: %f, numeroB: %f, precisao: %f",
                            numeroA, numeroB, precisao));
        }

        double numerador = numeroB;
        double denominador = numeroA;
        for (int i = 1; i <= precisao; i++) {
            final double temporario = numerador;
            numerador = numerador + denominador;
            denominador = temporario;
        }

        return numerador / denominador;
    }

    /**
     * Avalia se o argumento é um quadrado perfeito.
     *
     * @param numero O argumento a ser avaliado
     * @return Verdadeito, se o numero é um quadrado perfeito, Falso, se não
     * @throws IllegalArgumentException Se o argumento é menor que 1
     */
    public static boolean isQuadradoPerfeito(final int numero) {
        final int limiteMinimo = 1;
        if (numero < limiteMinimo) {
            throw new IllegalArgumentException(String.format("numero: %d",
                    numero));
        }

        int soma = 1;
        final int inicio = 3;
        for (int i = inicio; soma < numero; i = i + 2) {
            soma = soma + i;
        }

        return soma == numero;
    }

    /**
     * Produz a raiz quadrada de um número de acordo com a precisão fornecida.
     *
     * @param radicando O número que se deseja calcular a raiz quadrada
     * @param precisao  A precisão desejada do resultado retornado, quanto
     *                  maior o valor, maior a precisão
     * @return O resultado da raiz quadrada do radicando dada a precisao
     * fornecida
     * @throws IllegalArgumentException Se radicando é menor ou igual a 0
     */
    public static double raizQuadrada(final double radicando,
                                      final int precisao) {
        final int limiteMinimo = 0;
        if (radicando <= limiteMinimo) {
            throw new IllegalArgumentException(
                    String.format("radicando: %f", radicando));
        }

        int precisaoAux = precisao;
        double raizQuadrada = 1;
        while (precisaoAux >= 0) {
            raizQuadrada = (raizQuadrada + radicando / raizQuadrada) / 2;
            precisaoAux--;
        }

        return raizQuadrada;
    }

    /**
     * Avalia se o argumento é um número primo.
     *
     * @param numero O número a ser avaliado se é primo
     * @return Verdadeiro, se o argumento é primo; Falso, se não é primo
     * @throws IllegalArgumentException Se parâmetro é menor ou igual a 1
     */
    public static boolean isPrimo(final int numero) {
        final int limiteMinimo = 1;
        if (numero <= limiteMinimo) {
            throw new IllegalArgumentException(String.format("numero: %d",
                    numero));
        }

        for (int i = 2; i < numero; i++) {
            if (restoDaDivisaoInteira(numero, i) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calcula o maior divisor comum entre dois números inteiros.
     *
     * @param numeroA O primeiro número a ser avaliado
     * @param numeroB O segundo número a ser avaliado
     * @return O maior divisor comum entre os argumentos fornecidos
     * @throws IllegalArgumentException Se o numeroB for maior que o numeroA,
     *                                  ou se o numero B for menor que 1
     */
    public static int maiorDivisorComumMetodo1(final int numeroA,
                                               final int numeroB) {
        final int limiteMinimo = 0;
        if (numeroB > numeroA || numeroB <= limiteMinimo) {
            throw new IllegalArgumentException(
                    String.format("numeroA: %d, numeroB: %d",
                            numeroA, numeroB));
        }

        int a = numeroA;
        int b = numeroB;
        while (b != 0) {
            final int temporario = restoDaDivisaoInteira(a, b);
            a = b;
            b = temporario;
        }

        return a;
    }

    /**
     * Calcula o maior divisor comum entre dois números inteiros.
     *
     * @param numeroA O primeiro número a ser avaliado
     * @param numeroB O segundo número a ser avaliado
     * @return O maior divisor comum entre os argumentos fornecidos
     * @throws IllegalArgumentException Se o numeroB for maior que o numeroA,
     *                                  ou se o numero B for menor que 1
     */
    public static int maiorDivisorComumMetodo2(final int numeroA,
                                               final int numeroB) {
        final int limiteMinimo = 0;
        if (numeroB > numeroA || numeroB <= limiteMinimo) {
            throw new IllegalArgumentException(
                    String.format("numeroA: %d, numeroB: %d",
                            numeroA, numeroB));
        }

        int a = numeroA;
        int b = numeroB;
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }

        return a;
    }

    /**
     * Produz um vetor que indica quais número são primos (todos as posições
     * do vetor que possuem o valor 1 são primos).
     *
     * @param vetorZeros O vetor com a quantidade de números a serem avaliados
     *                   (devem todos conter valor 0)
     * @return Vetor nos quais as posições com valor 1 são primos
     * @throws IllegalArgumentException Se vetor fornecido possuir menos do
     *                                  que 2 elementos
     * @throws IllegalArgumentException Se números do vetor fornecido não
     *                                  forem iguais a 0
     */
    public static int[] determinarNumerosPrimos(final int[] vetorZeros) {
        final int limiteMinimo = 1;
        if (vetorZeros.length <= limiteMinimo) {
            throw new IllegalArgumentException(String.format(
                    "Tamanho do vetor menor que o permitido: %d",
                    vetorZeros.length));
        }
        for (int i = 0; i < vetorZeros.length; i++) {
            if (vetorZeros[i] != 0) {
                throw new IllegalArgumentException(String.format(
                        "vetorZeros[%d]: %d", i, vetorZeros[i]));
            }
        }


        final int limite = (int) Math.floor(Math.sqrt(vetorZeros.length));
        int[] numerosPrimos = vetorZeros.clone();
        for (int i = 1; i < limite; i++) {
            if (numerosPrimos[i] == 0) {
                for (int multiplo = 2 * i;
                     multiplo < numerosPrimos.length; multiplo =
                             multiplo + i) {
                    numerosPrimos[multiplo] = 1;
                }
            }
        }

        return numerosPrimos;
    }

    /**
     * Produz o resultado do polinômio fornecido.
     *
     * @param numero       A constante do polinômio
     * @param coeficientes Os coeficientes da constante do polinômio
     * @return O valor final do polinômio
     * @throws IllegalArgumentException Se o grau do polinômio for menor que 1
     */
    public static int calcularPolinomio(final int numero,
                                        final int[] coeficientes) {
        final int grauDoPolinomio = coeficientes.length;

        int polinomio = coeficientes[grauDoPolinomio - 1];
        for (int i = grauDoPolinomio - 2; i >= 0; i--) {
            polinomio = polinomio * numero + coeficientes[i];
        }

        return polinomio;
    }

    /**
     * Produz o enésimo termo da sequência de Fibonacci.
     *
     * @param enesimoTermo O número da sequência de Fibonacci que se deseja
     *                     obter
     * @return O valor do enésimo termo da sequência de Fibonnaci
     * @throws IllegalArgumentException Se o argumento for menor que 0
     */
    public static int obterEnesimoTermoDeFibonacci(final int enesimoTermo) {
        final int limiteMinimo = 0;
        if (enesimoTermo < limiteMinimo) {
            throw new IllegalArgumentException(String.format(
                    "enesimoTermo: %d", enesimoTermo));
        }

        if (enesimoTermo == 0 || enesimoTermo == 1) {
            return enesimoTermo;
        }

        int termoProximo = 0;
        int termoAtual = 1;
        for (int i = 2; i <= enesimoTermo; i++) {
            final int temporario = termoAtual;
            termoAtual = termoAtual + termoProximo;
            termoProximo = temporario;
        }

        return termoAtual;
    }

}
