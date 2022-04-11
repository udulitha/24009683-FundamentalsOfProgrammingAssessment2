import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class reads the information of students, their last name, first name, student id,
 * marks of assigment 1, marks of assigment 2 and marks of assigment 3.
 * Then calculate their total marks, find the failed students, find top 10 heighest marks students and lowest marks students.
 * 
 * @author (Sanduni Dissanayake)
 * @version (02-04-2022)
 */
public class StudentMarks
{
    //Instance variables
    private String unitName;
    private ArrayList<String> firstName;
    private ArrayList<String> lastName;
    private ArrayList<String> studentId;
    private ArrayList<Double> assessment1;
    private ArrayList<Double> assessment2;
    private ArrayList<Double> assessment3;
    private ArrayList<Double> totalMarks;
    private ArrayList<String> studentRecord;
    private String line;
    private String[] tokens;
    private  int option;
    private double cutoffMarks;
    private boolean exit;

    /**
     * Constructor for objects of class People
     */
    public StudentMarks()
    {
        // Initialise instance variables
        firstName=new ArrayList<>();
        lastName=new ArrayList<>();
        studentId=new ArrayList<>();
        assessment1=new ArrayList<Double>();
        assessment2=new ArrayList<Double>();
        assessment3=new ArrayList<Double>();
        totalMarks=new ArrayList<Double>();
        studentRecord =new ArrayList<>();
    }
    
        /**
         * Method readFile
         * This method reads dat fron CSV file
         * 
         */
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
    
    /**
     * Method getUnitName
     * This method gets data of the first row and first colum from the file
     *
     */
    public void getUnitName(){
        unitName = (tokens[0]);
    }
    
    /**
     * Method getLastName
     * This method adds all the first items from the file
     * to the lastName collection
     *
     */
    public void getLastName(){
        firstName.add(tokens[0]);
    }
    
    /**
     * Method getFirstName
     * This method adds all the scecond items from the file
     * to the firstName collection
     *
     */
    public void getFirstName(){
        lastName.add(tokens[1]);
    }
    
    /**
     * Method getStudentId
     * This method adds all the third items from the file
     * to the studentId collection
     *
     */
    public void getStudentId(){
        studentId.add(tokens[2]);
    }
    
    /**
     * Method getAssessment1Marks
     * This method adds all the fourth items from the file
     * to the assessment1 collection
     *
     */
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
    
    /**
     * Method getAssessment2Marks
     * This method adds all the fifth items from the file
     * to the assessment2 collection
     *
     */
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
    
    /**
     * Method getAssessment3Marks
     * This method adds all the sixth items from the file to the assessment3 collection
     *
     */
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
    
    /**
     * Method calculateTotalMarks
     * This method calculates the total marks of each student in the collection, 
     * total marks formula is Total= assessment1 marks+assesstmet2 marks+assessment3 marks
     *
     */
    public void calculateTotalMarks(){
        int i = 0;
        for(String name : firstName) {
            totalMarks.add(assessment1.get(i)+assessment2.get(i)+assessment3.get(i));
            i++;
        }
    }
    
    /**
     * Method printStudentMarks
     * This method prints the each assessment marks and total marks of each person
     * in the collection
     *
     */
    public void printStudentMarks(){
        int i = 0;
        for(String name : firstName) {
            System.out.println(firstName.get(i)+"  "+lastName.get(i)+"  "+studentId.get(i)+"  "+assessment1.get(i)+"  "+assessment2.get(i)+"  "
            +assessment3.get(i)+"  Total marks of the "+lastName.get(i)+" : "+totalMarks.get(i));
             i++;
        }
    }
    
    /**
     * Method getFailStudents
     * This method finds failed students and prints names, student id and total marks   
     * of the failed students in the collection
     *
     */
    public void getFailStudents(){
        int j=0;
        while(j<1){
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Input the cuttoff mark:");
                cutoffMarks = Double.parseDouble(in.nextLine());
                System.out.println("*************Failed Studens*************");
                for(int i=0;i<firstName.size();i++){
                    if(cutoffMarks > totalMarks.get(i)){
                       System.out.println(firstName.get(i)+"  "+lastName.get(i)+"  "+studentId.get(i)+" : "+totalMarks.get(i));
                    }
                }
                j++;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect value, please type a number");
            }
        } 
    }
    
    /**
     * Method topTenStudents
     * This method sort the students in ascending order and descending order 
     * to find and print top 10 students with lowest marks and top 10 students with heighest marks 
     * in the collection
     * 
     */
    public void topTenStudents(){
        int i = 0;
        for(String name : firstName) {
            studentRecord.add(totalMarks.get(i) + " " +firstName.get(i)+"  "+ lastName.get(i)+"  "+studentId.get(i));
            i++;
        }
        System.out.println("*************Top 10 Students with Lowest Marks***********");
        Collections.sort(studentRecord);
        for(int x=0;x<10;x++){
            System.out.println(studentRecord.get(x));
        }
        System.out.println("*************Top 10 Students with Heighest Marks***********");
        Collections.sort(studentRecord,Collections.reverseOrder());
        for(int x=0;x<10;x++){
            System.out.println(studentRecord.get(x));
        }
    }
    
    public void displayMenu(){
        System.out.println("**********************Student Marks Application*****************");
        System.out.println("Please select one of the options below: ");
        System.out.println("1: Show All Student Marks");
        System.out.println("2: Show Failed Students");
        System.out.println("3: Show Top 10 Students with Lowest/heighest marks");
        System.out.println("4: Quit");
    }

    /**
     * Method getMenuOption
     * This method shows a menu to user to select methods 
     * to find all marks of each students, failed students,
     * top 10 students with lowest marks and heghest marks
     * in the collection
     *
     */
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
                    topTenStudents();
                    j++;
                }else if(option == 4){
                    exit = true;
                    j++;
                }else{
                    System.out.println("incorrect number range, please type 1,2,3 or 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("incorrect number, please type the intiger");
            }
        }
    }

    /**
     * Method run
     * This method calls readFile() method, calculateTotalMarks() method,
     * while runing the displayMenu() and getMenuOption methods
     *
     */
    public void run(){
        readFile();
        calculateTotalMarks();
        exit = false;
        while(!exit){
            displayMenu();
            getMenuOption();
        }
    }

   
    /**
     * Method main
     *
     * @param args A parameter
     */
    public static void main(String[] args){
        StudentMarks myObj=new StudentMarks();
        myObj.run();
    }
}
