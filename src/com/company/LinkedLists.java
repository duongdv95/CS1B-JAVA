package com.company;

public class LinkedLists
{
    public static void main (String[] args)
    {
        Stack stk = new Stack();
        StackNode p;

        // build the stack
        for (int k = 0; k < 5; k++)
        {
            p = new StackNode();
            stk.push(p);
        }

        // show the stack
        while ( (p = stk.pop()) != null)
            p.show();
        System.out.println();
        SolarSystem.Planet myHome;
        myHome = SolarSystem.Planet.VENUS;
        System.out.println(myHome);
    }
}

class SolarSystem
{
    public enum Planet {
        MERCURY, VENUS, EARTH, MARS, JUPITER,
        SATURN, URANUS, NEPTUNE
    }

    public void memberMethod()
    {
        Planet myHome = Planet.NEPTUNE;
    }
}



/* -------------------------- RUN -------------------

(generic node) (generic node) (generic node) (generic node) (generic node)


----------------------------------------------------- */