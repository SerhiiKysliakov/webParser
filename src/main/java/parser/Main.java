package parser;

import parser.model.Product;
import parser.service.ContentLoader;
import parser.service.ContentToProductParser;
import parser.service.ProductsToFileWriter;
import parser.service.Report;

import java.io.IOException;


public class Main {
    public static final int SUBPAGES_AMOUNT = 5;

    public static void main(String[] args) throws IOException {
        ContentLoader contentLoader = new ContentLoader();
        ContentToProductParser parser = new ContentToProductParser();
        Report report = new Report();
        ProductsToFileWriter productsToFileWriter = new ProductsToFileWriter();

        int subpageIndex = 1;
        int productsCount = 0;
        while (subpageIndex <= SUBPAGES_AMOUNT) {
            String subPageUrl = "https://api-cloud.aboutyou.de/v1/products?with=attributes%3Akey"
                    + "%28brand%7CbrandLogo%7CbrandAlignment%7Cname%7CquantityPerPack%7CplusSize%7"
                    + "CcolorDetail%7CsponsorBadge%7CsponsoredType%7CmaternityNursing%7Cexclusive%7"
                    + "Cgenderage%7CspecialSizesProduct%7CmaterialStyle%7CsustainabilityIcons%7"
                    + "CassortmentType%7CdROPS%29%2CadvancedAttributes%3Akey%28materialComposition"
                    + "Textile%7Csiblings%29%2Cvariants%2Cvariants.attributes%3Akey%28shopSize%7"
                    + "CcategoryShopFilterSizes%7Ccup%7Ccupsize%7CvendorSize%7Clength%7Cdimension3%"
                    + "7CsizeType%7Csort%29%2Cimages.attributes%3Alegacy%28false%29%3Akey%28imageNext"
                    + "DetailLevel%7CimageBackground%7CimageFocus%7CimageGender%7CimageType%7Cimage"
                    + "View%29%2CpriceRange&filters%5Bcategory%5D=20290&sortDir=desc&sortScore=category_scores"
                    + "&sortChannel=sponsored_web_default&page=" + subpageIndex + "&per"
                    + "Page=20&campaignKey=px&shopId=605";

            String pageContent = contentLoader.getContent(subPageUrl);
            subpageIndex++;
            Product[] products = parser.contentToProductParser(pageContent);
            productsCount += products.length;
            String[][] reportProducts = report.prepareReport(products);
            productsToFileWriter.writeToFile(reportProducts);
        }
        System.out.println("Amount of HTTP requests: " + (subpageIndex - 1));
        System.out.println("Amount of products: " + productsCount);
    }
}
