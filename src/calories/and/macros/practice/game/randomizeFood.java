/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calories.and.macros.practice.game;

import java.util.Random;

/**
 *
 * @author tetrocs
 */
public class randomizeFood { 

    public String begin = "https://ndb.nal.usda.gov/ndb/foods/show/";
    public String end = "?fgcd=&manu=&lfacet=&format=&count=&max=35&offset=&sort=&qlookup=";
    Random rn = new Random();
    public String link;

    public randomizeFood() { //generates a random number and produces a URL with that food id, leading to a different food
       int randomNum = rn.nextInt(40000) + 1; 
       link = begin + randomNum + end; 
    }
}
