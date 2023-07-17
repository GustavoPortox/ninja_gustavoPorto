/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ninjateste_;

/**
 *
 * @author gusta
 */
public class Resultado {
    private String codigoPiloto;
    private String nomePiloto;
    private int voltasCompletadas;
    private String tempoTotalProva;

        public Resultado(String codigoPiloto, String nomePiloto, int voltasCompletadas, String tempoTotalProva) {
            this.codigoPiloto = codigoPiloto;
            this.nomePiloto = nomePiloto;
            this.voltasCompletadas = voltasCompletadas;
            this.tempoTotalProva = tempoTotalProva;
        }

        public String getCodigoPiloto() {
            return codigoPiloto;
        }

        public String getNomePiloto() {
            return nomePiloto;
        }

        public int getVoltasCompletadas() {
            return voltasCompletadas;
        }

        public String getTempoTotalProva() {
            return tempoTotalProva;
        }
    
}
