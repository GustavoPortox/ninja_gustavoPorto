/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ninjateste_;

/**
 *
 * @author gusta
 */
public class Piloto {
        private String nomePiloto;
        private String codigoPiloto;
        private int voltasCompletadas;
        private double tempoTotal;

        public Piloto(String nomePiloto, String codigoPiloto) {
            this.nomePiloto = nomePiloto;
            this.codigoPiloto = codigoPiloto;
        }

        public String getNomePiloto() {
            return nomePiloto;
        }

        public int getVoltasCompletadas() {
            return voltasCompletadas;
        }

        public double getTempoTotal() {
            return tempoTotal;
        }

        public void incrementarVoltasCompletadas() {
            voltasCompletadas++;
        }

        public void adicionarTempoTotal(double tempo) {
            tempoTotal += tempo;
        }

        public String getCodigoPiloto() {
            return codigoPiloto;
        }
}
