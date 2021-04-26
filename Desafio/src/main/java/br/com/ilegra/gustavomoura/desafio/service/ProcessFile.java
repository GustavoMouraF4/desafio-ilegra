package br.com.ilegra.gustavomoura.desafio.service;

import br.com.ilegra.gustavomoura.desafio.exception.FileProcessException;
import br.com.ilegra.gustavomoura.desafio.exception.FileReadException;
import br.com.ilegra.gustavomoura.desafio.model.Customer;
import br.com.ilegra.gustavomoura.desafio.model.Item;
import br.com.ilegra.gustavomoura.desafio.model.Sale;
import br.com.ilegra.gustavomoura.desafio.model.Salesman;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class ProcessFile {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();
    private static ArrayList<Salesman> salesmen = new ArrayList<>();

    public void readAndProcessFile(File file) throws FileReadException, FileProcessException {
        Scanner scan;
        try {
            scan = new Scanner(file);
        }catch (Exception e){
            throw new FileReadException("File not found");
        }
        try {
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                processLine(line);
            }
        }catch (Exception e){
            throw new FileProcessException("the data is corrupted or in the wrong format");
        }
    }

    public void processLine(String line) throws FileProcessException {
        try {
            if (line.startsWith("001")) {
                String cpf = line.split("ç")[1];
                String name = line.split("ç")[2];
                double salary = Double.parseDouble(line.split("ç")[3]);
                salesmen.add(new Salesman(cpf, name, salary));
            } else if (line.startsWith("002")) {
                String cnpj = line.split("ç")[1];
                String name = line.split("ç")[2];
                String businessArea = line.split("ç")[3];
                customers.add(new Customer(cnpj, name, businessArea));
            } else {
                Integer saleId = Integer.valueOf(line.split("ç")[1]);
                String salesmanName = line.split("ç")[3];
                String grossItem = line.split("ç")[2];
                String item = grossItem.substring(1, grossItem.length() - 1);
                if (item.contains(",")) {
                    String[] itemData = item.split(",");
                    for (String itemDatum : itemData) {
                        Integer itemId = Integer.valueOf(itemDatum.split("-")[0]);
                        Integer itemQuant = Integer.valueOf(itemDatum.split("-")[1]);
                        Double itemPrice = Double.valueOf(itemDatum.split("-")[2]);
                        items.add(new Item(itemId, itemQuant, itemPrice));
                    }
                } else {
                    Integer itemId = Integer.valueOf(item.split("-")[0]);
                    Integer itemQuant = Integer.valueOf(item.split("-")[1]);
                    Double itemPrice = Double.valueOf(item.split("-")[2]);
                    items.add(new Item(itemId, itemQuant, itemPrice));
                }
                sales.add(new Sale(saleId, items, salesmanName));
            }
        }catch (Exception e){
            throw new FileProcessException("the data is corrupted or nonstandard");
        }
    }

    public static int getQuantityOfCustomers(){
        return customers.size();
    }

    public static int getQuantityOfSalesmen(){
        return salesmen.size();
    }

    public static List<Sale> getMostExpansiveSale(){
        if (!sales.isEmpty()){
            for (Sale sale : sales){
                for (int i = 0; i < sale.getItems().size(); i++){
                    sale.setTotalPrice(sale.getItems().get(i).getItemPrice() * sale.getItems().get(i).getItemQuantity());
                }
            }
            return sales.stream().sorted(Comparator.comparing(Sale::getTotalPrice)).collect(Collectors.toList());
        }else {
            return Collections.emptyList();
        }
    }

    public static List<Salesman> getWorstSalesman(){
        if (!salesmen.isEmpty()){
            for (Sale sale : sales){ calculateSalesRevenue(sale); }
            return salesmen.stream().sorted(Comparator.comparing(Salesman::getSalesRevenue)).collect(Collectors.toList());
        }else {
            return Collections.emptyList();
        }
    }

    public static void calculateSalesRevenue(Sale sale){
        double revenue = 0;
        for (Salesman salesman : salesmen){
            if (salesman.getName().equals(sale.getSalesmanName())){
                for (int i = 0; i < sale.getItems().size(); i++){
                    revenue = sale.getItems().get(i).getItemQuantity() * sale.getItems().get(i).getItemQuantity();
                }
                salesman.setSalesRevenue(revenue);
            }
        }
    }
}
