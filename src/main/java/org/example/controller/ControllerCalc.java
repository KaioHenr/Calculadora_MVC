package org.example.controller;

import org.example.dto.RequestDTO;
import org.example.dto.ResponseDTO;
import org.example.model.Calc;
import org.example.model.CalcX;
import org.example.model.ICalc;
import org.example.model.operation.*;

import java.util.Objects;

public class ControllerCalc {

    public ResponseDTO calc(RequestDTO requestDTO){
        int result = 0;
        ICalc calc = null;
        if (Objects.equals(requestDTO.getOpcao(), "Somar")){
            calc = new Calc();

        }else {
            calc = new CalcX();
        }

        String opr = requestDTO.getOpcao();

        IOperation operation = null;
        try {
            operation = (IOperation) Class.forName("org.example.model.operation."+opr).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        result = calc.calculation(operation,requestDTO.getValor1(),requestDTO.getValor2());
        return new ResponseDTO(result);

    }
}

