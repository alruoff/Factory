package com.example.demo.services;

import com.example.demo.entities.Order;
import com.example.demo.entities.Technology;
import com.example.demo.entities.sets.*;
import com.example.demo.repositories.OperationARepository;
import com.example.demo.repositories.OperationBRepository;
import com.example.demo.repositories.OperationCRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor

public class MainService {

    private final OrderService orderService;
    private final TechnoService technoService;

    private final OperationARepository OpARepo;
    private final OperationBRepository OpBRepo;
    private final OperationCRepository OpCRepo;

    public String dispatchOrder(Long orderId) throws ParseException {

        Order order = orderService.getOrderById(orderId); // заказ со всеми атрибутами доступен
        OrderBaseSet orderBaseSet = orderService.getOrderVars(orderId); // набор переменных Заказа
        Technology techno = order.getTechno();
        DestrictBase destrictBase = technoService.getTechnoVars(techno.getId()); // набор параметров Технологии

        List<OperationBase> OperationList = new ArrayList<>();
        Map<String, OperationBase> operationMap = new HashMap<>();

        OperationBase o1 = new OperationA("операция 1"); // различные операции на производстве
        OperationBase o2 = new OperationB("операция 2");
        OperationBase o3 = new OperationC("операция 3");
        OperationBase o4 = new OperationD("операция 4");
        OperationBase o5 = new OperationE("операция 5");

        operationMap.put("Склад Бумага", o1); // к имени какой таблице относится
        operationMap.put("Резка", o2);
        operationMap.put("Печать", o3);
        operationMap.put("Упаковка", o4);
        operationMap.put("Склад ГП", o5);

        OperationBase op = operationMap.get(destrictBase.getParts().get(2));

        Integer length = destrictBase.getLength(); //длина списка операций из технологии
        for (int i = 0; i < length; i++) {
            OperationList.add(operationMap.get(destrictBase.getParts().get(i)));
            OperationBase ob =  OperationList.get(i);
            System.out.println();
        }


        OperationA opA = new OperationA();


        return "ok";
    }
}
