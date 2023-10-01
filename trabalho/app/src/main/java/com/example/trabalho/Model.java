package com.example.trabalho;

public class Model {

    private String tarefa, desci, id, data;

    public Model(){

    }

    public Model(String tarefa, String desci, String id, String data){
        this.tarefa = tarefa;
        this.desci = desci;
        this.id = id;
        this.data = data;
    }

    public String getTarefa() {
        return tarefa;
    }

    public String getDesci() {
        return desci;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public void setDesci(String desci) {
        this.desci = desci;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
