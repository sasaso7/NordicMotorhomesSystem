import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner doku = new Scanner(new File("C:/Users/emil-/Desktop/EksamenProj/postnumre.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("C:/Users/emil-/Desktop/EksamenProj/postSQL.txt", false));

        ArrayList<City> list = new ArrayList<>();

        while (doku.hasNextLine()){  //fylder arraylisten uden duplicated på zip
            String line = doku.nextLine();
            char[] tokens = line.toCharArray();
            String zip = tokens[0] + "" + tokens[1] + "" + tokens[2] + "" + tokens[3]; //her bliver zip coden lavet
            String city = "";

            for(int i = 5; i < tokens.length; i++){  //Her bliver bynavnet lavet
                city = city + "" + tokens[i];
            }

            City obj = new City(zip, city);

            if(list.size() < 2){  //De første 2 er ikke duplicates.
                list.add(obj);
            } else {
                if(!list.get(list.size()-1).getZip().equals(obj.getZip())){ //checker om det nye objekts zip code er det samme som det sidste i arraylistens
                    list.add(obj);
                }
            }
        }

        for(int i = 0; i < list.size(); i++){
            out.println("('" + list.get(i).getZip() + "', \"" + list.get(i).getCityName() + "\")," );  //Printer SQL Script til txt filen
            System.out.println("('" + list.get(i).getZip() + "', \"" + list.get(i).getCityName() + "\"),");
        }
    }
}
