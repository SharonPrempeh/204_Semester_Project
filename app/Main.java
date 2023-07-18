package app;

public class Main {
    public static Map graph = new Map();

    public static void main(String[] args) {

        Location mainGate = new Location("Main Gate");
        Location UGFireService = new Location("UG Fire Service");
        Location greatHall = new Location("Great Hall");
        Location nb= new Location("N Block");
        Location jqb = new Location("JQB");
        Location pentHall = new Location("Pentagon Hall");
        Location balmeLibrary = new Location("Balme Library");
        Location nnb = new Location("NNB");
        Location ugcs = new Location("UGCS");
        Location CSDepartment = new Location("CS Department");
        Location athleticOval = new Location("Athletic Oval");
        Location voltaHall = new Location("Volta Hall");
        Location akuafoHall = new Location("Akuafo Hall");
        Location legonHall = new Location("Legon Hall");
        Location nightMarket = new Location("Night Market");
        Location diasporaHalls = new Location("Diaspora");
        Location businessSchool = new Location("Business School");
        Location lawSchool = new Location("Law School");
        Location commonWealthHall = new Location("Common Wealth Hall");

        graph.addVertex(mainGate);
        graph.addVertex(greatHall);
        graph.addVertex(nb);
        graph.addVertex(UGFireService);
        graph.addVertex(jqb);
        graph.addVertex(pentHall);
        graph.addVertex(balmeLibrary);
        graph.addVertex(nnb);
        graph.addVertex(ugcs);
        graph.addVertex(CSDepartment);
        graph.addVertex(athleticOval);
        graph.addVertex(voltaHall);
        graph.addVertex(akuafoHall);
        graph.addVertex(legonHall);
        graph.addVertex(nightMarket);
        graph.addVertex(diasporaHalls);
        graph.addVertex(businessSchool);
        graph.addVertex(lawSchool);
        graph.addVertex(commonWealthHall);


        graph.addEdge(new Path(mainGate, jqb, 300));
        graph.addEdge(new Path(mainGate, UGFireService, 240));
        graph.addEdge(new Path(mainGate, akuafoHall, 500));

        graph.addEdge(new Path(UGFireService, akuafoHall, 650));

        graph.addEdge(new Path(greatHall, commonWealthHall, 100));

        graph.addEdge(new Path(nb, balmeLibrary, 600));
        graph.addEdge(new Path(nb, businessSchool, 500));
        graph.addEdge(new Path(nb, nnb, 500));
        graph.addEdge(new Path(nb, CSDepartment, 500));
        graph.addEdge(new Path(nb, ugcs, 500));

        graph.addEdge(new Path(voltaHall, businessSchool, 200));
        graph.addEdge(new Path(voltaHall, commonWealthHall,450));
        graph.addEdge(new Path(voltaHall, legonHall, 190));

        graph.addEdge(new Path(businessSchool, ugcs, 70));

        graph.addEdge(new Path(legonHall, akuafoHall, 400));
        graph.addEdge(new Path(legonHall, athleticOval, 450));
        graph.addEdge(new Path(legonHall, commonWealthHall, 750));

        graph.addEdge(new Path(jqb, akuafoHall, 700));
        graph.addEdge(new Path(jqb, lawSchool, 350));

        graph.addEdge(new Path(lawSchool, pentHall, 900));

        graph.addEdge(new Path(ugcs, balmeLibrary, 200));
        graph.addEdge(new Path(ugcs, voltaHall, 350));

        graph.addEdge(new Path(CSDepartment, balmeLibrary, 650));
        graph.addEdge(new Path(CSDepartment, akuafoHall, 850));
        graph.addEdge(new Path(CSDepartment, lawSchool, 650));

        graph.addEdge(new Path(balmeLibrary, akuafoHall, 270));
        graph.addEdge(new Path(balmeLibrary, legonHall, 500));

        graph.addEdge(new Path(athleticOval, akuafoHall, 550));
        graph.addEdge(new Path(athleticOval, nightMarket, 800));

        graph.addEdge(new Path(akuafoHall, nightMarket, 1000));

        graph.addEdge(new Path(nightMarket, diasporaHalls, 850));

        new UserInterface();

    }
}