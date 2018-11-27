
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final  double H = 0.8;

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
    public static Problem basicAlgorithm(Problem problem){
        List<Task> sortedList = new ArrayList<>();
        List<Task> taskList = problem.getTasks();

        System.out.println("DUE DATE: "+problem.getDueDate());
        int currentLowestPenalty=0;
        Task currentTaskToAdd=null;
        int currentTime=0;
        List<Task> taskList1 = problem.getTasks();
        while (taskList1.size()>0) {
            for (int i = 0; i < taskList.size(); i++) {
                int penalty;
                int futureTime = currentTime + taskList.get(i).getTime();
                if (futureTime <= problem.getDueDate()) {
                    penalty = (problem.getDueDate() - futureTime) * taskList.get(i).getEarliness();

                } else {
                    penalty = (futureTime - problem.getDueDate()) * taskList.get(i).getDelay();

                }
                if (i == 0) {
                    currentLowestPenalty = penalty;
                    currentTaskToAdd = taskList.get(i);
                } else {
                    if (currentLowestPenalty > penalty) {
                        currentLowestPenalty = penalty;
                        currentTaskToAdd = taskList.get(i);
                    }
                }

            }
            taskList1.remove(currentTaskToAdd);
            sortedList.add(currentTaskToAdd);
            System.out.println(currentTaskToAdd.getId());
        }
        problem.setTasks(sortedList);
        sortedList.forEach(p->System.out.println());
        return problem;
    }
    public static void main(String[] args) {
        Parser parser = new Parser();
        try {
            parser.parseFile("sch10.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        parser.showAllTasks();
        parser.setValueTaskAndProblems(H);
        Problem problem = parser.getProblemList().get(3);
        basicAlgorithm(problem);
        int penalty = calculatePenalty(problem);
        System.out.println("H: "+problem.getDueDate());
        System.out.println( penalty);
        createResultFile(problem, penalty);
    }
}
