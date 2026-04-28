package br.ifg.urutai.weathergrpcsystem.dto;


public class CidadeDTO {
    private String nome;
    private double temperatura;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}
