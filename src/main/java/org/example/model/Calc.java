package org.example.model;

import org.example.model.operation.IOperation;

public class Calc implements ICalc{

    public int calculation (IOperation operation, int value1, int value2){

        return operation.calc(value1,value2);
    }

}
