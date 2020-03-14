package sample;

import Model.ADT.*;
import Model.Expression.*;
import Model.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;
import View.ExitCommand;
import View.RunExample;
import View.TextMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.ControllerOld;
import java.io.BufferedReader;
import java.util.concurrent.CountDownLatch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();//FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene mainScene= new Scene(root, 900, 450);
        primaryStage.setScene(mainScene);

        Controller controller= fxmlLoader.getController();
        controller.setMainStage(primaryStage);
        setup(controller);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    private void setup(Controller ctrl){
        MyILatchTable lt11=new MyLatchTable();
        MyILatchTable lt12=new MyLatchTable();

        IRepository repository1= new Repository("log1.txt");
        IRepository repository2= new Repository("log2.txt");
        IRepository repository3= new Repository("log3.txt");
        IRepository repository4= new Repository("log4.txt");
        IRepository repository5= new Repository("log5.txt");
        IRepository repository6= new Repository("log6.txt");
        IRepository repository7= new Repository("log7.txt");
        IRepository repository8= new Repository("log8.txt");
        IRepository repository9= new Repository("log9.txt");
        IRepository repository10= new Repository("log10.txt");
        IRepository repository11= new Repository("log11.txt");
        IRepository repository12= new Repository("log12.txt");

        MyIDictionary<StringValue, BufferedReader> ft1= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft2= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft3= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft4= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft5= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft6= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft7= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft8= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft9= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft10= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft11= new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> ft12= new MyDictionary<>();

        //IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
         //       new CompStmt(new AssignStmt("v",new ValueExp(new BoolValue())), new PrintStmt(new VarExp("v"))));

        IStmt ex1= new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),  new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                        new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                        new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new IntValue(1)))),
                                new PrintStmt(new VarExp("b"))))));
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),new CompStmt(new IfStmt(new VarExp("a"),
                        new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                        new PrintStmt(new VarExp("v"))))));
        IStmt ex4=new CompStmt(new VarDeclStmt("varf",new StringType()),new CompStmt(new AssignStmt("varf",new ValueExp(new StringValue("test.in"))),
                new CompStmt(new openRFile(new VarExp("varf")),new CompStmt(new VarDeclStmt("varc",new IntType()),new CompStmt(new
                        readFile(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new
                        CompStmt(new readFile(new VarExp("varf"),"varc"),new CompStmt(new PrintStmt(new VarExp("varc")), new
                        closeRFile(new VarExp("varf"))))))))));
        IStmt ex5=new CompStmt(new VarDeclStmt("v",new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelExp(new VarExp("v"),new ValueExp(new IntValue(0)),">"),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        IStmt ex6= new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new NewStmt("a",new VarExp("v")),
                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new VarExp("a")))))));
        IStmt ex7=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new NewStmt("a",new VarExp("v")),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),new PrintStmt(new ArithExp('+',new rH(new rH(new VarExp("a"))),new ValueExp(new IntValue(5)))))))));
        IStmt ex8=new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new NewStmt("v",new ValueExp(new IntValue(20))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v"))),new CompStmt(new wH("v",new ValueExp(new IntValue(30))),
                        new PrintStmt(new ArithExp('+',new rH(new VarExp("v")),new ValueExp(new IntValue(5))))))));
        IStmt ex9= new CompStmt(new VarDeclStmt("v",new RefType(new IntType())),new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a",new RefType(new RefType(new IntType()))),new CompStmt(new NewStmt("a",new VarExp("v")),
                        new CompStmt(new NewStmt("v",new ValueExp(new IntValue(30))),new CompStmt(new PrintStmt(new rH(new rH(new VarExp("a")))),
                                new CompStmt(new VarDeclStmt("i",new RefType(new RefType(new RefType(new IntType())))),new CompStmt(
                                        new NewStmt("i",new VarExp("a")),new PrintStmt(new rH(new rH(new rH(new VarExp("i")))))))))))));
        IStmt ex10=new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                        new CompStmt(new NewStmt("a",new ValueExp(new IntValue(22))),
                                new CompStmt(new forkStmt(new CompStmt(new wH("a",new ValueExp(new IntValue(30))),
                                        new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                                                new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rH(new VarExp("a"))))))),
                                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new rH(new VarExp("a"))))))) ));
        //
        IStmt ex11=new CompStmt(new VarDeclStmt("a",new RefType(new IntType())),new CompStmt(new VarDeclStmt("b",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new NewStmt("a",new ValueExp(new IntValue(0))),
                        new CompStmt(new NewStmt("b",new ValueExp(new IntValue(0))),new CompStmt(new wH("a",new ValueExp(new IntValue(1))),
                        new CompStmt(new wH("b",new ValueExp(new IntValue(2))),
                                new CompStmt(new CondAssignStmt(new RelExp(new rH(new VarExp("a")),new rH(new VarExp("b")),"<"),
                                        new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200)),"v"),
                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                new CompStmt(new CondAssignStmt(new RelExp(new ArithExp('-',new rH(new VarExp("b")),new ValueExp(new IntValue(2))),new rH(new VarExp("a")),">"),
                                                        new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200)),"v"),new PrintStmt(new VarExp("v"))))))))))));

        /*IStmt ex12=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),
                   new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new NewStmt("v1",new ValueExp(new IntValue(2))),new CompStmt(new NewStmt("v2",new ValueExp(new IntValue(3))),
                           new CompStmt(new NewStmt("v3",new ValueExp(new IntValue(4))),new CompStmt(new newLatch("cnt",new rH(new VarExp("v2"))),
                                   new CompStmt(new forkStmt(new wH("v1",new ArithExp('*',new rH(new VarExp("v1")),new ValueExp(new IntValue(10))))),new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                           new CompStmt(new CountDownStmt("cnt"),
                                                   new CompStmt(new forkStmt(new wH("v2",new ArithExp('*',new rH(new VarExp("v2")),new ValueExp(new IntValue(10))))),
                                                           new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),new CompStmt( new CountDownStmt("cnt"),
                                                                   new CompStmt(new forkStmt(new wH("v3",new ArithExp('*',new rH(new VarExp("v3")),new ValueExp(new IntValue(10))))),
                                                                           new CompStmt(new PrintStmt(new rH(new VarExp("v3"))),new CompStmt(new CountDownStmt("cnt"),
                                                                                   new CompStmt(new awaitStmt("cnt"),new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                                                                           new CompStmt(new CountDownStmt("cnt"),new PrintStmt(new ValueExp(new IntValue(100)))))))))))))))))))))));



        IStmt ex12=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new NewStmt("v1",new ValueExp(new IntValue(2))),new CompStmt(new NewStmt("v2",new ValueExp(new IntValue(3))),
                        new CompStmt(new NewStmt("v3",new ValueExp(new IntValue(4))),new CompStmt(new newLatch("cnt",new rH(new VarExp("v2"))),
                                //new CompStmt(
                                        new forkStmt(new CompStmt(new wH("v1",new ArithExp('*',new rH(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                                        //,
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                        new CompStmt(new CountDownStmt("cnt"),
                                        //new CompStmt(
                                        new forkStmt(new CompStmt(new wH("v2",new ArithExp('*',new rH(new VarExp("v2")),
                                        new ValueExp(new IntValue(10)))),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),new CompStmt( new CountDownStmt("cnt"),
                                        //new CompStmt(
                                                new forkStmt(new CompStmt(new wH("v3",new ArithExp('*',new rH(new VarExp("v3")),new ValueExp(new IntValue(10)))),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v3"))),new CompStmt(new CountDownStmt("cnt")
                                                ,
                                        new CompStmt(new awaitStmt("cnt"),new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                        new CompStmt(new CountDownStmt("cnt"),new PrintStmt(new ValueExp(new IntValue(100))))))))))))))))))))))))));// -


        */

        ////////
        /*IStmt ex12=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new NewStmt("v1",new ValueExp(new IntValue(2))),new CompStmt(new NewStmt("v2",new ValueExp(new IntValue(3))),
                        new CompStmt(new NewStmt("v3",new ValueExp(new IntValue(4))),new CompStmt(new newLatch("cnt",new rH(new VarExp("v2"))),
                                //new CompStmt(
                                new forkStmt(new CompStmt(new wH("v1",new ArithExp('*',new rH(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                                        //,
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                                new CompStmt(new CountDownStmt("cnt"),
                                                        //new CompStmt(
                                                        new forkStmt(new CompStmt(new wH("v2",new ArithExp('*',new rH(new VarExp("v2")),
                                                                new ValueExp(new IntValue(10)))),
                                                                new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),new CompStmt( new CountDownStmt("cnt"),
                                                                        //new CompStmt(
                                                                        new forkStmt(new CompStmt(new wH("v3",new ArithExp('*',new rH(new VarExp("v3")),new ValueExp(new IntValue(10)))),
                                                                                new CompStmt(new PrintStmt(new rH(new VarExp("v3"))),new CompStmt(new CountDownStmt("cnt")
                                                                                        ,
                                                                                        new CompStmt(new awaitStmt("cnt"),new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                                                                                new CompStmt(new CountDownStmt("cnt"),new PrintStmt(new ValueExp(new IntValue(100))))))))))))))))))))))))));// -



         */
        /////////////

        IStmt ex12=new CompStmt(new VarDeclStmt("v1",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v2",new RefType(new IntType())),new CompStmt(new VarDeclStmt("v3",new RefType(new IntType())),
                new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new NewStmt("v1",new ValueExp(new IntValue(2))),new CompStmt(new NewStmt("v2",new ValueExp(new IntValue(3))),
                        new CompStmt(new NewStmt("v3",new ValueExp(new IntValue(4))),new CompStmt(new newLatch("cnt",new rH(new VarExp("v2"))),
                                new CompStmt(
                                        new forkStmt(new CompStmt(new wH("v1",new ArithExp('*',new rH(new VarExp("v1")),new ValueExp(new IntValue(10)))),
                                        //,
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                                        new CompStmt(new CountDownStmt("cnt"),
                                        //new CompStmt(
                                        new forkStmt(new CompStmt(new wH("v2",new ArithExp('*',new rH(new VarExp("v2")),
                                        new ValueExp(new IntValue(10)))),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),new CompStmt( new CountDownStmt("cnt"),
                                        //new CompStmt(
                                                new forkStmt(new CompStmt(new wH("v3",new ArithExp('*',new rH(new VarExp("v3")),new ValueExp(new IntValue(10)))),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v3"))),new CompStmt(new CountDownStmt("cnt")
                                                ,
                                        //new CompStmt(new awaitStmt("cnt"),
                                               // new CompStmt(
                                                        new PrintStmt(new ValueExp(new IntValue(100)))))))))))))))//, )
                                                ,
                                        new CompStmt(new awaitStmt("cnt"),new CompStmt(new CountDownStmt("cnt"),new PrintStmt(new ValueExp(new IntValue(100))))))))))))));//)))))))))))));// -

        //
        //MyIDictionary<String, Type> typeEnv=new MyDictionary<>();
        //ex1.typecheck(typeEnv);
        //
        MyIDictionary<String, Value> dict1= new MyDictionary<>();
        MyIDictionary<String, Value> dict2= new MyDictionary<>();
        MyIDictionary<String, Value> dict3= new MyDictionary<>();
        MyIDictionary<String, Value> dict4= new MyDictionary<>();
        MyIDictionary<String, Value> dict5= new MyDictionary<>();
        MyIDictionary<String, Value> dict6= new MyDictionary<>();
        MyIDictionary<String, Value> dict7= new MyDictionary<>();
        MyIDictionary<String, Value> dict8= new MyDictionary<>();
        MyIDictionary<String, Value> dict9= new MyDictionary<>();
        MyIDictionary<String, Value> dict10= new MyDictionary<>();
        MyIDictionary<String, Value> dict11= new MyDictionary<>();
        MyIDictionary<String, Value> dict12= new MyDictionary<>();

        MyIList<Value> list1= new MyList<>();
        MyIList<Value> list2= new MyList<>();
        MyIList<Value> list3= new MyList<>();
        MyIList<Value> list4= new MyList<>();
        MyIList<Value> list5= new MyList<>();
        MyIList<Value> list6= new MyList<>();
        MyIList<Value> list7= new MyList<>();
        MyIList<Value> list8= new MyList<>();
        MyIList<Value> list9= new MyList<>();
        MyIList<Value> list10= new MyList<>();
        MyIList<Value> list11= new MyList<>();
        MyIList<Value> list12= new MyList<>();

        MyIStack<IStmt> stk1= new MyStack<>();
        MyIStack<IStmt> stk2= new MyStack<>();
        MyIStack<IStmt> stk3= new MyStack<>();
        MyIStack<IStmt> stk4= new MyStack<>();
        MyIStack<IStmt> stk5= new MyStack<>();
        MyIStack<IStmt> stk6= new MyStack<>();
        MyIStack<IStmt> stk7= new MyStack<>();
        MyIStack<IStmt> stk8= new MyStack<>();
        MyIStack<IStmt> stk9= new MyStack<>();
        MyIStack<IStmt> stk10= new MyStack<>();
        MyIStack<IStmt> stk11= new MyStack<>();
        MyIStack<IStmt> stk12= new MyStack<>();


        MyIHeap<Integer,Value> heapTable1=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable2=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable3=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable4=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable5=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable6=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable7=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable8=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable9=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable10=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable11=new MyHeap<>();
        MyIHeap<Integer,Value> heapTable12=new MyHeap<>();

        PrgState prg1= new PrgState(stk1,dict1,list1,ft1,heapTable1,ex1);
        PrgState prg2= new PrgState(stk2,dict2,list2,ft2,heapTable2,ex2);
        PrgState prg3= new PrgState(stk3,dict3,list3,ft3,heapTable3,ex3);
        PrgState prg4= new PrgState(stk4,dict4,list4,ft4,heapTable4,ex4);
        PrgState prg5= new PrgState(stk5,dict5,list5,ft5,heapTable5,ex5);
        PrgState prg6= new PrgState(stk6,dict6,list6,ft6,heapTable6,ex6);
        PrgState prg7= new PrgState(stk7,dict7,list7,ft7,heapTable7,ex7);
        PrgState prg8= new PrgState(stk8,dict8,list8,ft8,heapTable8,ex8);
        PrgState prg9= new PrgState(stk9,dict9,list9,ft9,heapTable9,ex9);
        PrgState prg10= new PrgState(stk10,dict10,list10,ft10,heapTable10,ex10);
        PrgState prg11= new PrgState(stk11,dict11,list11,ft11,heapTable11,lt11,ex11);
        PrgState prg12= new PrgState(stk12,dict12,list12,ft12,heapTable12,lt12,ex12);

        repository1.add(prg1);
        repository2.add(prg2);
        repository3.add(prg3);
        repository4.add(prg4);
        repository5.add(prg5);
        repository6.add(prg6);
        repository7.add(prg7);
        repository8.add(prg8);
        repository9.add(prg9);
        repository10.add(prg10);
        repository11.add(prg11);
        repository12.add(prg12);

        ControllerOld controller1= new ControllerOld(repository1);
        ControllerOld controller2= new ControllerOld(repository2);
        ControllerOld controller3= new ControllerOld(repository3);
        ControllerOld controller4= new ControllerOld(repository4);
        ControllerOld controller5= new ControllerOld(repository5);
        ControllerOld controller6= new ControllerOld(repository6);
        ControllerOld controller7= new ControllerOld(repository7);
        ControllerOld controller8= new ControllerOld(repository8);
        ControllerOld controller9= new ControllerOld(repository9);
        ControllerOld controller10= new ControllerOld(repository10);
        ControllerOld controller11= new ControllerOld(repository11);
        ControllerOld controller12= new ControllerOld(repository12);

        //
        //
        //ctrl.addCommand(new ExitCommand("0","exit"));
        /*.addCommand(new RunExample("0",ex1.toString(),controller1));
        ctrl.addCommand(new RunExample("1",ex2.toString(),controller2));
        ctrl.addCommand(new RunExample("2",ex3.toString(),controller3));
        ctrl.addCommand(new RunExample("3",ex4.toString(),controller4));
        ctrl.addCommand(new RunExample("4",ex5.toString(),controller5));
        ctrl.addCommand(new RunExample("5",ex6.toString(),controller6));
        ctrl.addCommand(new RunExample("6",ex7.toString(),controller7));
        ctrl.addCommand(new RunExample("7",ex8.toString(),controller8));
        ctrl.addCommand(new RunExample("8",ex9.toString(),controller9));
        ctrl.addCommand(new RunExample("9",ex10.toString(),controller10));
        ctrl.addCommand(new RunExample("10",ex11.toString(),controller11));
        ctrl.addCommand(new RunExample("11",ex12.toString(),controller12));*/
        /*ctrl.addCommand(new RunExample("0",ex2.toString(),controller2));
        ctrl.addCommand(new RunExample("1",ex3.toString(),controller3));
        ctrl.addCommand(new RunExample("2",ex4.toString(),controller4));
        ctrl.addCommand(new RunExample("3",ex5.toString(),controller5));
        ctrl.addCommand(new RunExample("4",ex6.toString(),controller6));
        ctrl.addCommand(new RunExample("5",ex7.toString(),controller7));
        ctrl.addCommand(new RunExample("6",ex8.toString(),controller8));
        ctrl.addCommand(new RunExample("7",ex9.toString(),controller9));
        ctrl.addCommand(new RunExample("8",ex10.toString(),controller10));*/
        ctrl.addCommand(new RunExample("0",ex11.toString(),controller11));
        ctrl.addCommand(new RunExample("1",ex12.toString(),controller12));
        ctrl.show();
        /*TextMenu menu=new TextMenu();

        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.addCommand(new RunExample("4",ex4.toString(),controller4));
        menu.addCommand(new RunExample("5",ex5.toString(),controller5));
        menu.addCommand(new RunExample("6",ex6.toString(),controller6));
        menu.addCommand(new RunExample("7",ex7.toString(),controller7));
        menu.addCommand(new RunExample("8",ex8.toString(),controller8));
        menu.addCommand(new RunExample("9",ex9.toString(),controller9));
        menu.addCommand(new RunExample("10",ex10.toString(),controller10));
        menu.show();*/
    }
}
