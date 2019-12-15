package com.example.goibibo.GOT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

@Slf4j
@RestController
public class Controller {

    @Autowired
    DragonService serviceDragon;

    @Autowired
    RuleService serviceRule;

    //Post Request to register the dragons
    @PostMapping(value = "/dragons")
    public String registerDragon(@RequestBody Dragon dragon) {
        Dragon dragons = serviceDragon.save(dragon);
        return ("dragonId : " + dragons.getId());
    }

    //GET request to view all the defined rules
    @GetMapping(value = "/rules")
    public ArrayList<Rules> retrieveAllRules() {
        return serviceRule.findAll();
    }

    //POST request to register new rules.
    @PostMapping(value = "/rules")
    public String registerRule(@RequestBody Rules rule) {
        Rules rules = serviceRule.save(rule);
        if(rules==null)
            throw new AlreadyExistException("rule-"+ rule);
        return ("ruleId : " + rules.getId());
    }

    //DELETE Request to delete the defined rule bu rule Id
    @DeleteMapping(value = "/rules/{id}")
    public String deleteRule(@PathVariable String id) {
        Rules rules = serviceRule.deleteRule(id);
        if (rules == null)
            throw new RuleNotFoundException("id-"+ id);
        return "Successfully Deleted";
    }

    //GET request to know whether the dragon can kill animal or not
    @GetMapping(value = "/dragons/{id}")
    public String killAnimals(@PathVariable String id, @RequestBody Map<String, String> mp) {
        LOGGER.info(mp.values().toArray()[0]);
        LOGGER.info(mp.values().toArray()[1]);
        ArrayList<String> list = new ArrayList<>();
        list = serviceRule.isKill(id, (String) mp.values().toArray()[0], (String) mp.values().toArray()[1]);

        StringBuilder str = new StringBuilder();
        if (list.get(list.size() - 1).equals("True")) {
            str = new StringBuilder("didKill : True(satisfies ");
            for (int i = 0; i < list.size() - 2; i++) {
                str.append(list.get(i));
                str.append(" & ");
            }
        } else
            str = new StringBuilder("didKill : False (break ");
        str.append(list.get(list.size() - 2));
        str.append(" )");
        return str.toString();
    }
}
