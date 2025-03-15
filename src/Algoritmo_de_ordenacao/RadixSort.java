package Algoritmo_de_ordenacao;

import java.util.Arrays;

public class RadixSort {

    public static void contador_de_frequencia(int[] array, int divisor) {
        int[] contagem = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] arrayOrdenado = new int[array.length];

        for (int numero : array) {
            int digito = (numero / divisor) % 10;
            contagem[digito]++;
        }
        /*
         * {123, 321, 543, 1, 89, 1, 324, 77, 123}
         * [0, 3, 0, 3, 1, 0, 0, 1, 0, 1] - Unidade
         * [2, 0, 4, 0, 1, 0, 0, 1, 1, 0] - Dezena
         * [4, 2, 0, 2, 0, 1, 0, 0, 0, 0] - Centena
         * */

        int acumulador = 0;
        for (int i = 0; i < 10; i++) {
            int temp = contagem[i];
            contagem[i] = acumulador;
            acumulador += temp;
        }
        /*
         * {123, 321, 543, 1, 89, 1, 324, 77, 123}
         * [0, 3, 0, 3, 1, 0, 0, 1, 0, 1] - Unidade
         * Iteração 1 (i = 0):
         * Frequência: contagem[0] = 0.
         * Posição inicial: contagem[0] = 0.
         * Atualiza acumulador: 0 + 0 = 0.
         * O dígito 0 começa na posição 0, e o próximo dígito (1) começará na posição 0.
         *
         * Iteração 2 (i = 1):
         * Frequência: contagem[1] = 3.
         * Posição inicial: contagem[1] = 0.
         * Atualiza acumulador: 0 + 3 = 3.
         * O dígito 1 começa na posição 0, e o próximo dígito (2) começará na posição 3.
         *
         * Iteração 3 (i = 2):
         * Frequência: contagem[2] = 0.
         * Posição inicial: contagem[2] = 3.
         * Atualiza acumulador: 3 + 0 = 3.
         * O dígito 2 começa na posição 3, e o próximo dígito (3) começará na posição 3.
         *
         * [0, 0, 3, 3, 6, 7, 7, 7, 8, 8] - Unidade
         * [0, 2, 2, 6, 6, 7, 7, 7, 8, 9] - Dezena
         * [0, 4, 6, 6, 8, 8, 9, 9, 9, 9] - Centena
         */

        for (int numero : array) {
            int digito = (numero / divisor) % 10;
            arrayOrdenado[contagem[digito]] = numero;
            contagem[digito]++;
        }
        /*Número 123:
         * Dígito das unidades: 3.
         * Posição no arrayOrdenado: contagem[3] = 3.
         * Coloca 123 na posição 3.
         * Incrementa contagem[3] para 4.
         *
         * Contagem atualizado:
         * [0, 0, 3, 4(123), 6, 7, 7, 7, 8, 8]
         *
         * Número 543:
         * Dígito das unidades: 3.
         * Posição no arrayOrdenado: contagem[3] = 4.
         * Coloca 543 na posição 4.
         * Incrementa contagem[3] para 5.
         * [0, 0, 3, 5(123), 6(543), 7, 7, 7, 8, 8]
         * */

        for (int i = 0; i < array.length; i++) {
            array[i] = arrayOrdenado[i];
        }
    }

    public static void radixSort(int[] array) {
        int maiorValor = Arrays.stream(array).max().getAsInt();

        for (int divisor = 1; maiorValor / divisor > 0; divisor *= 10) {
            contador_de_frequencia(array, divisor);
        }
    }

    public static void exibirArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Array exemplo: int[] numeros = {123, 321, 543, 1, 89, 1, 324, 77, 123};
        int[] numeros = {123, 243, 983, 3, 33, 333, 903, 1003, 3};
        System.out.println("Antes da ordenação: ");
        exibirArray(numeros);

        radixSort(numeros);

        System.out.println("Depois da ordenação: ");
        exibirArray(numeros);
    }
}
