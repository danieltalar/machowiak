/*The format of these data files is:
    number of problems
    for each problem in turn:
       number of jobs (n)
       for each job i (i=1,...,n) in turn:
          p(i), a(i), b(i)
          Wszystko co potrzebne znajduje sie w mapach: klucz "PROBLEM nr i" (i od 1), wartość: lista (p(i) lub a(i) lub b(i))
          LICZBA ZADAŃ - JOBS
          LICZBA PROBLEMÓW - PROBLEMS
          W TABLICACH POSZCZEGOLNE WARTOSCI DLA KAZDEGO Z ZADAN
* */

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class Parser {

    public List<Problem> problemList = new ArrayList<>();

    private Map<Integer, List<Integer>> p_i_values = new HashMap<Integer, List<Integer>>();
    private Map<Integer, List<Integer>> a_i_values = new HashMap<Integer, List<Integer>>();
    private Map<Integer, List<Integer>> b_i_values = new HashMap<Integer, List<Integer>>();
    private int problems;
    private int jobs;
    //for parsing - unnecessary
    private String line;
    private String[] splitStr;
    private String p_i;
    private String a_i;
    private String b_i;
    private ArrayList<Integer> p_i_all_numbers = new ArrayList<>();
    private ArrayList<Integer> a_i_all_numbers = new ArrayList<>();
    private ArrayList<Integer> b_i_all_numbers = new ArrayList<>();
    private int problem_no = 1;

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void parseFile(String pathname) throws IOException {
        Scanner scanner = new Scanner(new File(pathname));
        problems = scanner.nextInt();
        jobs = scanner.nextInt();
        int end = jobs;
        int start = 0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            splitStr = line.trim().split("\\s+");
            if (splitStr.length == 3) {
                p_i = splitStr[0];
                a_i = splitStr[1];
                b_i = splitStr[2];
                p_i_all_numbers.add(Integer.valueOf(p_i));
                a_i_all_numbers.add(Integer.valueOf(a_i));
                b_i_all_numbers.add(Integer.valueOf(b_i));

            }
        }
        //w mapach mamy dla kazdego problemu poszczegolne wartosci pi, ai, bi - klucz: "PROBLEM NR I", I zaczyna się od 1, wartość: lista dla każdego zadania
        for (int i = 0; i < problems; i++) {

            p_i_values.put( problem_no, p_i_all_numbers.subList(start, end));
            a_i_values.put( problem_no, a_i_all_numbers.subList(start, end));
            b_i_values.put( problem_no, b_i_all_numbers.subList(start, end));
            start = start + jobs;
            end = start + jobs;
            problem_no++;
        }
    }
    public void showAllTasks() {
        problem_no = 1;
        System.out.println("Number of problems: " + problems);
        System.out.println("Number of jobs: " + jobs);
        for (int i = 0; i < problems; i++) {
//            System.out.println("PROBLEM NR " + problem_no);
//            System.out.println("-------------------------------------------------------------------------");
//            System.out.println("Wartosci pi wynosza: " + p_i_values.get( problem_no));
//            System.out.println("Wartosci ai wynosza: " + a_i_values.get(problem_no));
//            System.out.println("Wartosci bi wynosza: " + b_i_values.get( problem_no));
            problem_no++;
        }
    }

    public void setValueTaskAndProblems(double h){
//        Parser parser = new Parser();
//        try {
//            parser.parseFile("tests/sch1000.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        for (int i=0;i<problems;i++){
            Problem problem = new Problem();
            List<Task> tasksList = new ArrayList<>();
           System.out.println(jobs);

            for (int j=0;j<jobs;j++){
                tasksList.add(new Task(j,p_i_values.get(i+1).get(j),a_i_values.get(i+1).get(j),b_i_values.get(i+1).get(j)));

            }
            int sum = 0;
            for (int k=0;k<tasksList.size();k++){

                sum +=tasksList.get(k).getTime();
            }
            problem.setTasks(tasksList);
            problem.setSumTasks(sum);
            int dueDate =(int) (h*sum);
            problem.setDueDate(dueDate);
            System.out.println( problem.getDueDate());
            System.out.println( problem.getSumTasks());
            problemList.add(problem);

        }



    }

    public int getProblems() {
        return problems;
    }

    public int getJobs() {
        return jobs;
    }

    public Map<Integer, List<Integer>> getP_i_values() {
        return p_i_values;
    }

    public Map<Integer, List<Integer>> getA_i_values() {
        return a_i_values;
    }

    public Map<Integer, List<Integer>> getB_i_values() {
        return b_i_values;
    }
}
