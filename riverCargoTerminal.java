package app;

import manifest.*;
import service.StowagePlanner;
import terminal.*;
import plan.StowagePlan;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BARRANCABERMEJA RIVER TERMINAL ===");

        List<String> containers = List.of(
            "CNT;MSKU1234567;40;346.2;SELLO-8891",
            "CNT;TCLU8877213;40;336.2;SELLO-8892",
            "CNT;MSKU2222222;40;326.2;SELLO-8893",
            "CNT;TCLU3333333;40;316.2;SELLO-8894",
            "CNT;MSKU4444444;40;306.2;SELLO-8895",
            "CNT;TCLU5555555;40;296.2;SELLO-8896",
            "CNT;MSKU6666666;40;286.2;SELLO-8897",
            "CNT;TCLU7777777;40;146.2;SELLO-8898",
            "CNT;MSKU8888888;40;106.2;SELLO-8899",
            "CNT;TCLU9999999;40;96.2;SELLO-8900",
            "GRA;CARBON;850;13.5",
            "CNT;MSKU999;XX;18.5"
        );

        ContainerRecorder recorder = new ContainerRecorder();
        List<CargoUnit> units = recorder.process(containers);
        System.out.printf("Recorder: CONTAINERS | lines read: %d | accepted: %d | rejected: %d%n",
                containers.size(), units.size(), recorder.getRejected().size());
        recorder.getRejected().forEach(s -> System.out.println("[REJECTED] " + s));

        StowagePlanner planner = new StowagePlanner();
        planner.printPlan(
            planner.createPlan("SP-2026-0148", "BZ-4417",
                    LocalDate.of(2026, 10, 12), new ContainerFactory(), units),
            new ContainerFactory()
        );

        List<String> bulk = List.of(
            "GRA;COAL-A;350;0", "GRA;COAL-B;340;0", "GRA;COAL-C;330;0",
            "GRA;COAL-D;320;0", "GRA;COAL-E;310;0", "GRA;COAL-F;300;0",
            "GRA;COAL-G;290;0", "GRA;COAL-H;150;0", "GRA;COAL-I;110;0",
            "GRA;COAL-J;100;0"
        );
        SolidBulkRecorder bulkRecorder = new SolidBulkRecorder();
        List<CargoUnit> bulkUnits = bulkRecorder.process(bulk);
        System.out.printf("%nRecorder: SOLID BULK | lines read: %d | accepted: %d | rejected: %d%n",
                bulk.size(), bulkUnits.size(), bulkRecorder.getRejected().size());

        TerminalFactory bulkFactory = new SolidBulkFactory();
        planner.printPlan(
            planner.createPlan("SP-2026-0149", "BZ-5502",
                    LocalDate.of(2026, 10, 13), bulkFactory, bulkUnits),
            bulkFactory
        );

        try {
            new StowagePlan.Builder()
                .number("SP-ERROR")
                .terminal("CONTAINERS")
                .sailingDate(LocalDate.of(2026, 10, 14))
                .holds(Map.of("B1", units))
                .totalWeight(100)
                .build();
        } catch (IllegalStateException e) {
            System.out.println("\n[EXCEPTION] " + e.getMessage());
        }
    }
}
