/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.myrestaurant.restcontrollers;


import edu.eci.arsw.myrestaurant.services.RestaurantOrderServicesStub;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping("/")
public class OrdersAPIController {

    @Autowired
    private RestaurantOrderServicesStub orderServices;

    @GetMapping("/orders")
    private ResponseEntity<String> getOrder(){
        try{
            List<String> order = new ArrayList<>();
            for(int i = 0; i <= 4; i++){
                if(orderServices.getTablesWithOrders().contains(i)){
                    order.add(orderServices.getTableOrder(i).toString() + ", TOTALBILL: " + orderServices.calculateTableBill(i));
                }
            }
            System.out.println(order);

            return new ResponseEntity<>(String.valueOf(order), HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
