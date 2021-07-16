package service;

import model.Product;

public class Report {
    public String[][] prepareReport(Product[] products) {
        String[][] csvReport = new String[products.length][5];
        int index = 0;
        for(Product product: products) {
            String[] reportRow = new String[] {
                    product.getProductName(),
                    product.getBrand(),
                    product.getColors(),
                    product.getPrice(),
                    product.getArticleID()
            };
            csvReport[index++] = reportRow;
        }
        return csvReport;
    }
}
