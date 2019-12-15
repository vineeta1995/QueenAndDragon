package com.example.goibibo.GOT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Slf4j
@Component
public class RuleService {
    private static ArrayList<Rules> rules = new ArrayList<>(); // To store all the defined rules
    //To store which dragon kills how many animal and at which time
    private static Map<String, ArrayList<ArrayList<String> > >mp = new HashMap<>();
    //To keep the track of generated ids
    private static int ruleCount = 0;

    public ArrayList<Rules> findAll() {
        return rules;
    }

    // Function to register new rules dynamically.
    public Rules save(Rules rule) {
        for(Rules r :rules)
        {
            if(r.getTime()==rule.getTime() && r.getNoOfAnimals()==rule.getNoOfAnimals())
                return null;
        }
        if (rule.getId() == null)
            ruleCount++;
        String idd = "Rule" + ruleCount;
        rule.setId(idd);
        rules.add(rule);
        return rule;
    }

    //Function to delete the rules
    public Rules deleteRule(String id) {
        Iterator<Rules> iterator = rules.iterator();
        while (iterator.hasNext()) {
            Rules rule = iterator.next();
            if (rule.getId().equals(id)) {
                iterator.remove();
                return rule;
            }
        }
        return null;
    }

    //Function to determine whether dragon ca kill animals or not.
    public ArrayList<String> isKill(String id, String date, String animalKill) {
        ArrayList<String> list =new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd-HH:mm");
        ArrayList<ArrayList<String>> hmp = new ArrayList<>();
        if(mp.isEmpty())
        {
            for (Rules rule : rules) {
                if (Integer.parseInt(animalKill) > rule.getNoOfAnimals())
                {
                    list.clear();
                    list.add(rule.getId());
                    list.add("False");
                    return list;
                }
            }
        }
        else {
            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(date);
                hmp = mp.get(id);
                for (Rules rule : rules) {
                    int count = Integer.parseInt(animalKill);
                    int length = hmp.size();
                    for (int i = length - 1; i >= 0; i--) {
                        d2 = format.parse(hmp.get(i).get(0));
                        long diff = d1.getTime() - d2.getTime();
                        long diffHours = (diff / (60 * 60 * 1000) % 24);
                        if (diffHours < (long) rule.getTime()) {
                            if ((count + Integer.parseInt(hmp.get(i).get(1))) <= (long) rule.getNoOfAnimals())
                                count = count + Integer.parseInt(hmp.get(i).get(1));
                            else {
                                list.add(rule.getId());
                                list.add("False");
                                return list;
                            }
                        } else if (count > (long) rule.getNoOfAnimals()) {
                            list.add(rule.getId());
                            list.add("False");
                            return list;
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> animal = new ArrayList<>();
        animal.add(date);
        animal.add(animalKill);
        hmp.add(animal);
        mp.put(id, hmp);
        for(Rules rule :rules)
            list.add(rule.getId());
        list.add("True");
        return list;
    }
}

