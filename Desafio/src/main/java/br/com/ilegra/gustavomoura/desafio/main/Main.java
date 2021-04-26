package br.com.ilegra.gustavomoura.desafio.main;

import br.com.ilegra.gustavomoura.desafio.service.DataService;

public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService();
        dataService.dataAnalysingRun();
    }
}

