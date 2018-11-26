
import java.io.*;
import java.util.List;


public class Main {

    private static final  float H = 1;

    public static int calculatePenalty( Problem problem){
        int current =0;
        int penalty=0;
        for (int i=0;i<problem.getTasks().size();i++)
        {
            System.out.println( problem.tasks.get(i).getTime());
           Task currentTask =  problem.getTasks().get(i);
           current += currentTask.getTime();
           int diff = current-problem.getDueDate();
           int penaltyCurrentTask;
           if (diff>0){
               penaltyCurrentTask = diff*currentTask.getDelay();

           }
           else {
               penaltyCurrentTask = Math.abs(diff)*currentTask.getEarliness();
           }
            penalty+=penaltyCurrentTask;
        }


        return penalty;


        }

    public static void createResultFile(Problem problem, int penalty){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("result.txt"), "utf-8"))) {
            writer.write(String.valueOf(penalty+"\n"));
            writer.write(String.valueOf(H)+"\n");
            List<Task> tasks = problem.getTasks();
            writer.write(problem.getBeginTime()+"\n");
            for (int i = 0; i< tasks.size(); i++){
                writer.write(tasks.get(i).getTime()+ " "+tasks.get(i).getEarliness() +" "+tasks.get(i).getDelay()+"\n");

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            parser.parseFile("tests/sch10.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser.showAllTasks();
        parser.setValueTaskAndProblems(H);
        Problem problem = parser.getProblemList().get(3);
        int penalty = calculatePenalty(problem);
        System.out.println("H: "+problem.getDueDate());
        System.out.println( penalty);
        createResultFile(problem, penalty);
    }
}
