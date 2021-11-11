package sample;

import java.util.Arrays;

public class TestDFS {
    public static void main(String[]args){
        DFS test = new DFS();


//        test.SearchTech("812043765", "012345678");
//
//        System.out.println("-------------------------------------------------------");
//
//        test.reset();

        test.SearchTech("125340678", "012345678");
        System.out.println("-------------------------------------------------------");
        System.out.println(test.pathToGoal().size());

    }
}
