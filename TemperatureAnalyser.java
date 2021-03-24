// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP-102-112 - 2021T1, Assignment 3
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

import java.lang.reflect.Array;
import java.util.*;

/** The program contains several methods for analysing the readings of the temperature levels over the course of a day.
 *  There are several things about the temperature levels that a user may be interested in: 
 *    The average temperature level.
 *    How the temperatures rose and fell over the day.
 *    The maximum and the minimum temperature levels during the day.
 */

public class TemperatureAnalyser{
    double average = 0;
    /* analyse reads a sequence of temperature levels from the user and prints out
     *    average, maximum, and minimum level and plots all the levels
     *    by calling appropriate methods
     */
    public void analyse(){
        UI.clearPanes();
        ArrayList<Double> listOfNumbers = UI.askNumbers("Enter levels, end with 'done': ");
        if (listOfNumbers.size() != 0) {
            this.printAverage(listOfNumbers);
            this.plotLevels(listOfNumbers);
            this.medianOfList(listOfNumbers);

            UI.printf("Maximum level was:  %f\n", this.maximumOfList(listOfNumbers));
            UI.printf("Minimum level was:  %f\n", this.minimumOfList(listOfNumbers));
            UI.printf("Median level was:  %f\n" , this.medianOfList(listOfNumbers));
            UI.printf("Average level was:  %f\n" , this.printAverage(listOfNumbers));
        }
        else {
            UI.println("No readings");
        }
    }

    /** Print the average level
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the sum, which 
     *     needs to be initialised to an appropriate value.
     *  CORE
     *
     */
    public double printAverage(ArrayList<Double> listOfNumbers) {
        /*# YOUR CODE HERE */
        int length = listOfNumbers.size();
        for (double nums: listOfNumbers) {
            average = average + nums;
        }
        average = average / length;
        return average;
    }

    /**
     * Plot a bar graph of the sequence of levels,
     * using narrow rectangles whose heights are equal to the level.
     * [Core]
     *   - Plot the bars.
     * [Completion]
     *   - Draws a horizontal line for the x-axis (or baseline) without any labels.
     *   - Any level greater than 400 should be plotted as if it were just 400, putting an
     *         asterisk ("*") above it to show that it has been cut off.
     * [Challenge:] 
     *   - The graph should also have labels on the axes, roughly every 50 pixels.
     *   - The graph should also draw negative temperature levels correctly.
     *   - Scale the y-axis and the bars so that the largest numbers and the smallest just fit on the graph.
     *     The numbers on the y axis should reflect the scaling.
     *   - Scale the x-axis so that all the bars fit in the window.
     */
    public void plotLevels(ArrayList<Double> listOfNumbers) {
        int base = 420;              //base of the graph
        int left = 50;               //left of the graph
        int step = 25;               //distance between plotted points
        int startPos = 0;
        /*# YOUR CODE HERE */
        for (double value: listOfNumbers) {
            if (!(value >= 400)) {
                UI.fillRect(left + startPos, base - value, step, value);
                startPos = startPos + 2 * step;
            } else {
                UI.fillRect(left + startPos, base - 400, step, 400);
                UI.drawString("*",left + startPos + (step >> 1), base - 400);
                startPos = startPos + 2 * step;
            }
        }
        UI.drawLine(left,base,1000,base);
        UI.println("Finished plotting");
    }

    /** Find and return the maximum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the maximum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double maximumOfList(ArrayList<Double> listOfNumbers) {
        /*# YOUR CODE HERE */
        return Collections.max(listOfNumbers);
    }

    /** Find and return the minimum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the minimum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double minimumOfList(ArrayList<Double> listOfNumbers) {
        /*# YOUR CODE HERE */
        return Collections.min(listOfNumbers);
    }
    public double medianOfList(ArrayList<Double> listOfNumbers){
        int size = listOfNumbers.size();
        Double[] array = new Double[size];
        listOfNumbers.toArray(array);
        Arrays.sort(array);

        return array[(size)/2];
    }


    public void setupGUI() {
        UI.initialise();
        UI.addButton("Analyse", this::analyse );
        UI.addButton("Quit", UI::quit );
    }

    public static void main(String[] args) {
        TemperatureAnalyser ta = new TemperatureAnalyser();
        ta.setupGUI();
    }

}
