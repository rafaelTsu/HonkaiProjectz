package com.example.honkaiproject;

public class ItemListView {
    private int icone;
    private String texto;
    public ItemListView(int icone, String texto){
        this.icone=icone;
        this.texto=texto;
    }
    public int getIcone() {
        return icone;
    }
    public void setIcone(int icone) {
        this.icone = icone;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
