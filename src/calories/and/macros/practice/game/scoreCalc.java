/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calories.and.macros.practice.game;

/**
 *
 * @author tetrocs
 */
public class scoreCalc {

    public int calorieDiff;
    public int carbDiff;
    public int proteinDiff;
    public int fatDiff;
    public int totalScore;

    public scoreCalc(int[] userans, getInfo actual) {
        calorieDiff = (int) Math.abs(userans[0] - actual.calories);
        carbDiff = (int) Math.abs(userans[1] - actual.carb);
        proteinDiff = (int) Math.abs(userans[2] - actual.protein);
        fatDiff = (int) Math.abs(userans[3] - actual.fat);
        totalScore = (int) Math.floor(1/ ((calorieDiff + carbDiff + proteinDiff + fatDiff) * 0.3));
    }
}
