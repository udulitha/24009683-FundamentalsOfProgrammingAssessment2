import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Write a description of class StudentMarks here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentMarks
{
    private ArrayList<String> firstName;
    private ArrayList<String> lastName;
    private ArrayList<String> studentId;
    private ArrayList<Double> assessment1;
    private ArrayList<Double> assessment2;
    private ArrayList<Double> assessment3;
    private ArrayList<Double> totalMarks;
    private ArrayList<Double> cuttoffMarks;
    private String line;
    private String[] tokens;
    private  int option;
    
    //private final int NUMBER_OF_ASSIGNMENTS=3;

    /**
     * Constructor for objects of class People
     */
    public StudentMarks()
    {
        // initialise instance variables
        firstName=new ArrayList<>();
        lastName=new ArrayList<>();
        studentId=new ArrayList<>();
        assessment1=new ArrayList<Double>();
        assessment2=new ArrayList<Double>();
        assessment3=new ArrayList<Double>();
        totalMarks=new ArrayList<Double>();
    }
    
        public void readFile(){
        int countLines=0; 
        try{
            File myFile=new File("prog5001_students_grade_2022.CSV");
            Scanner myScanner=new Scanner(myFile);
            
            while(myScanner.hasNextLine()){
                line=myScanner.nextLine();
                countLines++;
                if(countLines>1){
                    tokens = line.split(",");
                    getFirstName();
                    //getLastName();
                    //getStudentId();
                    //getAssessment1Marks();
                    //getAssessment2Marks();
                    //getAssessment3Marks();
                }
            }
            myScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found");
            e.printStackTrace();
        }
    }
    
        public void getFirstName(){
        firstName.add(tokens[0]);
    }


    public static void main(String[] args){
        StudentMarks myObj=new StudentMarks();
        myObj.run();
        // myObj.printHighestAndLowestPeoplesBMI();

    }

    
}
