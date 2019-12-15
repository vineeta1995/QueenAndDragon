package com.example.goibibo.GOT;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;

@Component
public class DragonService {

    private static ArrayList<Dragon> dragons= new ArrayList<>();
    private static int dragonCount = 0;

    public ArrayList<Dragon> findAll()
    {
        return dragons;
    }

    //To register the dragon
    public Dragon save(Dragon dragon)
    {
        if(dragon.getId()==null)
        {
            dragonCount++;
        }
        String idd = "Drogon" + dragonCount;
        dragon.setId(idd);
        dragons.add(dragon);
        return dragon;
    }
}
