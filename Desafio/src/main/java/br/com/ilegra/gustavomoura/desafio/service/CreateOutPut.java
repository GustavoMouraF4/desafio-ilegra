package br.com.ilegra.gustavomoura.desafio.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateOutPut {
    private int fileCount = 0;

    public void createOutputFile() throws IOException {
        fileCount++;
        FileOutputStream file = new FileOutputStream("/home/ilegra/data/out/result" + fileCount + ".done.dat");
        PrintWriter pr = new PrintWriter(file);

        pr.println("Amount of customers: " + ProcessFile.getQuantityOfCustomers());
        pr.println("Amount of salesman: " + ProcessFile.getQuantityOfSalesmen());
        if (!ProcessFile.getMostExpansiveSale().isEmpty()){
            pr.println("ID of the most expensive sale: " + ProcessFile.getMostExpansiveSale().get(0).getSaleId());
        } else {
            pr.println("input file does not have a purchase made");
        }
        if (!ProcessFile.getWorstSalesman().isEmpty()){
            pr.println("Worst salesman ever: " + ProcessFile.getWorstSalesman().get(0).getName());
        }else {
            pr.println("input file does not have a salesman");
        }

        pr.close();
        file.close();
    }
}
