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
    private String unitName;
    private ArrayList<String> firstName;
    private ArrayList<String> lastName;
    private ArrayList<String> studentId;
    private ArrayList<Double> assessment1;
    private ArrayList<Double> assessment2;
    private ArrayList<Double> assessment3;
    private ArrayList<Double> totalMarks;
    private ArrayList<Double> highestMarks;
    private String line;
    private String[] tokens;
    private  int option;
    private double cutoffMarks;
    private boolean exit;
    
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
                tokens = line.split(",");
                if(countLines == 1){
                   getUnitName();
                   
                }else if(countLines > 2) {
                     getLastName();
                     getFirstName();
                     getStudentId();
                     getAssessment1Marks();
                     getAssessment2Marks();
                     getAssessment3Marks();
                }
            }
            myScanner.close();
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found");
            e.printStackTrace();
        }
    }
    
    public void getUnitName(){
        unitName = (tokens[0]);
    }
    
    public void getLastName(){
        firstName.add(tokens[0]);
    }
    
    public void getFirstName(){
        lastName.add(tokens[1]);
    }
    
     public void getStudentId(){
        studentId.add(tokens[2]);
    }
    
    public void getAssessment1Marks(){
        if(tokens.length >= 4){
            if(tokens[3].equals("")){
                tokens[3]="0";
            }
            assessment1.add(Double.valueOf(tokens[3]));
        }else{
            assessment1.add(Double.valueOf(0));
        }
    }
    
    public void getAssessment2Marks(){
        if(tokens.length >= 5){
            if(tokens[4].equals("")){
                tokens[4]="0";
            }
            assessment2.add(Double.valueOf(tokens[4]));
        }else{
            assessment2.add(Double.valueOf(0));
        }
    }
    
    public void getAssessment3Marks(){
        if(tokens.length >= 6){
            if(tokens[5].equals("")){
                tokens[5]="0";
            }
            assessment3.add(Double.valueOf(tokens[5]));
        }else{
            assessment3.add(Double.valueOf(0));
        }
    }
    
    public void calculateTotalMarks(){
        int i = 0;
        for(String name : firstName) {
            totalMarks.add(assessment1.get(i)+assessment2.get(i)+assessment3.get(i));
            i++;
        }
    }
    
    public void printStudentMarks(){
        int i = 0;
        for(String name : firstName) {
            System.out.println(firstName.get(i)+"  "+lastName.get(i)+"  "+studentId.get(i)+"  "+assessment1.get(i)+"  "+assessment2.get(i)+"  "+assessment3.get(i)+"  Total marks of the "+lastName.get(i)+" : "+totalMarks.get(i));
             i++;
        }
    }
    
    public void getFailStudents(){
        int j=0;
        while(j<1){
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Input the cuttoff mark:");
                cutoffMarks = Double.parseDouble(in.nextLine());
                for(int i=0;i<firstName.size();i++){
                    if(cutoffMarks > totalMarks.get(i)){
                       System.out.println("Total marks of the fail "+firstName.get(i)+"  "+lastName.get(i)+"  "+studentId.get(i)+" : "+totalMarks.get(i));
                    }
                }
                j++;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect value, please type a number");
            }
            
        } 
        
    }
    
    public void topTenStudents(){
        Collections.sort(totalMarks);
        for(int i=0;i<totalMarks.size();i++){
            System.out.println("The BMI of "+firstName.get(i)+" is "+totalMarks.get(i));
        }
        //highestMarks = list.subList(list.size() -3, list.size())
    }
    
    public void displayMenu(){
        System.out.println("**********************Student Marks Application*****************");
        System.out.println("Please select one of the options below: ");
        System.out.println("1: Show All Student Marks");
        System.out.println("2: Show Failed Students");
        System.out.println("3: Quit");
    }

    public void getMenuOption(){
        int j=0;
        while(j<1){
            try {
                 Scanner userOption=new Scanner(System.in);
                 option = Integer.parseInt(userOption.nextLine());
                 if(option == 1){
                    printStudentMarks();
                    j++;
                } else if(option == 2){
                    getFailStudents();
                    j++;
                }else if(option == 3){
                    System.exit(0);
                    j++;
                }else{
                    System.out.println("incorrect number range, please type 1,2 or 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("incorrect number, please type the intiger");
            }
        }
    }

    public void run(){
        readFile();
        calculateTotalMarks();
        exit = true;
        while(exit){
            displayMenu();
            getMenuOption();
        }

    }

   
    public static void main(String[] args){
        StudentMarks myObj=new StudentMarks();
        myObj.run();
        
    }
}
