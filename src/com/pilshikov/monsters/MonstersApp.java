package com.pilshikov.monsters;

import java.util.Scanner;

public class MonstersApp {

    // user keyboard input
    private static Scanner sc = new Scanner(System.in);

    // Hero class
    static class GameCharacter {
        String name;
        int maxHp;
        int hp;
        int attackPower;
        boolean block;

        public GameCharacter(String name, int hp,int maxHp, int attackPower) {
            this.name = name;
            this.hp = hp;
            this.maxHp = maxHp;
            this.attackPower = attackPower;
            this.block = false;
        }

        public void attack(GameCharacter target) {
            int damage = this.attackPower;
            if(target.block) {
                if(Math.random()< 0.75) {
                    System.out.println(target.name+ " 100% blocked damage");
                    return;
                } else {
                    damage *= 2; // block penetration. Damage doubles!
                }
            }
            target.hp -= damage;
            System.out.println(this.name + " hits target " + target.name+ " damage = " + this.attackPower);
            System.out.println("Target " + target.name + "remaining health: " + target.hp + " / " + target.maxHp);

        }

        public void blockAction() {
            hp++;
            if(hp > maxHp) {
                hp = maxHp;
            }
            block = true;
            System.out.println(this.name + " tries to block next attack. Gains +1 HP.");
        }

        public void reset() {
            block = false;
        }
    }    

    // Application entry point
    public static void main(String[] args) {
        
        GameCharacter hero = new GameCharacter("sir Bob", 10, 10, 2);
        GameCharacter monster = new GameCharacter("troll", 6,10,  2);

        // story
        System.out.println(hero.name + " begins his journey.");
        System.out.println(hero.name + " encounters an enemy! He is attacked by " + monster.name);
        System.out.println("A battle begins.");

        //infinite loop
        while (true) {
            System.out.println("\nHero turn: " + hero.name);
            System.out.println(hero.name + ", your actions?");
            hero.reset();

            String input = sc.next();

            if(input.equals("/hit")) {
                hero.attack(monster);
                if(monster.hp <= 0) {
                    System.out.println(hero.name + " vanquished " + monster.name + "!");
                    break;
                }
            }
            if(input.equals("/block")) {
                hero.blockAction();
            }

            System.out.println("\nMonster turn: " + monster.name);
            monster.reset();
            if(Math.random() < 0.5) {
                monster.attack(hero);
                if(hero.hp <= 0) {
                    System.out.println(monster.name + " vanquished " + hero.name + "!");
                    break;
                }
            } else {
                monster.blockAction();
            }
        }
        System.out.println("Game finished.");
    }
}