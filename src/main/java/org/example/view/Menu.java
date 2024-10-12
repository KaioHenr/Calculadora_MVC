package org.example.view;

import org.example.dto.RequestDTO;
import org.example.dto.ResponseDTO;
import org.reflections.Reflections;
import java.util.ArrayList;
import java.util.Set;
import org.example.model.operation.IOperation;

import java.util.Scanner;


public class Menu {


    public RequestDTO show(){
        ArrayList<String> lst = this.showMenu();
        return this.captureValues(lst);
    }

    private RequestDTO captureValues (ArrayList<String> lst){

        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();

        // Loop até receber uma opção válida
        while (opcao < 1 || opcao > lst.size()) {
            System.out.println("Escolha uma operação (1 a " + lst.size() + "):");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println((i + 1) + ". " + lst.get(i));
            }
            opcao = input.nextInt();

            if (opcao < 1 || opcao > lst.size()) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Informe o primeiro valor:");
        int valor1 = input.nextInt();
        System.out.println("Informe o segundo valor:");
        int valor2 = input.nextInt();

        return new RequestDTO(lst.get(opcao-1),valor1,valor2);
    }

    private ArrayList<String> showMenu(){

        System.out.println("---->Olá Meu fi!<----");
        System.out.println("Escolha uma opração da lista abaixo:");
        Reflections reflections = new Reflections("org.example.model.operation");
        Set<Class<? extends IOperation>> classes = reflections.getSubTypesOf(IOperation.class);
        ArrayList<String> lstOperation = new ArrayList<>();
        int aux = 1;
        for (Class<? extends IOperation> clazz : classes) {
            lstOperation.add(clazz.getSimpleName());
            System.out.println(aux + " - " + clazz.getSimpleName());
            aux++;
        }
        return lstOperation;
    }

    public void showResult (ResponseDTO responseDTO){
        System.out.println("O Resultado é: "+responseDTO.getResult());
    }


}
