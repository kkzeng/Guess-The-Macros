/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calories.and.macros.practice.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author tetrocs
 */
public class getInfo {
    
    public double calories, protein, carb, fat; 
    public String foodTitle, cleanFoodTitle;
    public ArrayList<String> nutrients = new ArrayList<String>(); //arraylist to contain nutrient information
    
    public getInfo(String link) throws IOException {
        
        
        

        try {
            
            
            // Create a URL for the desired page
            URL url = new URL(link);

            // Read all the text returned by the server
            Document doc = Jsoup.connect(link).get();
            
            //Retrieving name of food
            //Splitting ID of food and name of food
            foodTitle = doc.select("div#view-name").text(); //locates the text with tag = "div" and id = "view-name"
            String[] clean = foodTitle.split("(?<=\\d)(?=\\D)"); // \D matches all non-digit characters, while \d matches all digit characters. ?<= is a positive lookbehind (so everything before the current position is asserted to be a non-digit character), ?= is a positive lookahead (so everything after the current position is asserted as a digit).
            
            //Bug catching
            System.out.println("\nSplit Array Contents:");
            int x = 0; //index of array
            for (String a : clean){
                System.out.println(x+": "+a);
                x++;
            }
            System.out.println("Food name (not cleaned): " + foodTitle);
            //Cleaning up name of food for display in app
            String[] clean2 = clean[1].split(",",2); 
            System.out.println("Food name: (number removed): " + clean2[1]);  //more bug catching
            String[] clean3 = clean2[1].split(" ",2); //removes space from beginning of basic 
            cleanFoodTitle = clean3[1];
            System.out.println("Final food title: "+cleanFoodTitle);
                    
            //Retrieving nutritional information of food from HTML table format
            for (Element table : doc.select("tbody")) { //loop every table in the document containing tbody as the tag
                for (Element row : table.select("tr")) { //nested loop for every row in each table in the document containing tr as the tag
                    Elements cell = row.select("td[style*=text-align:right;width: 30px"); //an arraylist containing elements with attribute of style = text-align right and width 30 px (in this case all the nutrients)
                    String temp = cell.text(); //retrieves the body of the elements as one line of String (combined together)
                    nutrients.add(temp); //adds the String containing nutritional information to an arraylist
                }
                
            }
            
            //Parsing calories, protein, fat and carb String to double values
            DecimalFormat df = new DecimalFormat("0.00"); //format with 2 decimal places with leading forced 0 before decimal point if neccessary
            calories = Double.parseDouble(nutrients.get(2))/4.186; //converts kJ to calories
            protein = Double.parseDouble(nutrients.get(3)); 
            fat = Double.parseDouble(nutrients.get(4)); 
            carb = Double.parseDouble(nutrients.get(5));
            
            
            System.out.println("Calories: " + df.format(calories) + " Protein: " + df.format(protein) + " Fat: " + df.format(fat) + " Carbohydrates: " + df.format(carb)); //bug catching

        } catch (MalformedURLException e) {
            System.out.println("Error");
        }

    }
}
