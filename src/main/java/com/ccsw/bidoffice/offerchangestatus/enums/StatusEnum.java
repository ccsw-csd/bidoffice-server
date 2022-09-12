package com.ccsw.bidoffice.offerchangestatus.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum StatusEnum {

    GONOGO("Pendiente Go/NoGo", new String[] { "En curso", "Desestimada" }),
    REJECT("Desestimada", new String[] { "Pendiente Go/NoGo", "En curso" }),
    INPROGRESS("En curso", new String[] { "Desestimada", "Stand by", "Entregada" }),
    STANDBY("Stand by", new String[] { "En curso", "Desestimada" }),
    DELIVERED("Entregada", new String[] { "Finalizada" }), FINISH("Finalizada", new String[] {});

    private String labelStatus;
    private String[] possibleChanges;

    private StatusEnum(String labelStatus, String[] possibleChanges) {
        this.labelStatus = labelStatus;
        this.possibleChanges = possibleChanges;
    }

    private static final Map<String, String[]> STATUS = new HashMap<>();

    static {
        for (StatusEnum e : values()) {
            STATUS.put(e.labelStatus, e.possibleChanges);
        }
    }

    public static Boolean isValidChangeStatus(String label, String changeOptStatus) {

        Stream<String> options = Arrays.stream(STATUS.get(label));

        return options.anyMatch(item -> item.equals(changeOptStatus));
    }

}
