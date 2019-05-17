package designPatterns.creational.prototype.ex1;

import java.util.HashMap;
import java.util.Map;

public class MonsterRegistry {

    private Map<String, Monster> registry = new HashMap<String, Monster>();

    public Monster createMonster(String monster_name) {
        Monster new_monster = null;
        if (monster_name.equals("Geth"))
            new_monster = new Geth();
        else if (monster_name.equals("Alien"))
            new_monster = new Alien();
        else if (monster_name.equals("Cyborg"))
            new_monster = new Cyborg();

        new_monster.LoadSound();
        new_monster.LoadTextures();

        return new_monster;
    }

    public void registerMonster(String name, Monster monster) {
            if (! registry.containsKey(name)) {
            monster.LoadSound();
            monster.LoadTextures();
            registry.put(name, monster);
        }
    }

    public Monster getMonster(String name) throws Exception {
        if (registry.containsKey(name))
            return registry.get(name).Clone();//Cloning !!!
        throw new Exception("Monster not registered");
    }
}