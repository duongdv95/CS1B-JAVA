import java.text.*;
import java.util.*;
// Daniel Duong
//------------------------------------------------------
public class Foothill {
    // -------  main --------------
    public static void main(String[] args) throws Exception {
        FHtree<String> sceneTree = new FHtree<String>();
        FHtreeNode<String> tn;

        System.out.println("Starting tree empty? " + sceneTree.empty() + "\n");
        // create a scene in a room
        tn = sceneTree.addChild(null, "room");

        // add three objects to the scene tree
        sceneTree.addChild(tn, "Lily the canine");
        sceneTree.addChild(tn, "Miguel the human");
        sceneTree.addChild(tn, "table");
        // add some parts to Miguel
        tn = sceneTree.find("Miguel the human");

        // Miguel's left arm
        tn = sceneTree.addChild(tn, "torso");
        tn = sceneTree.addChild(tn, "left arm");
        tn =  sceneTree.addChild(tn, "left hand");
        sceneTree.addChild(tn, "thumb");
        sceneTree.addChild(tn, "index finger");
        sceneTree.addChild(tn, "middle finger");
        sceneTree.addChild(tn, "ring finger");
        sceneTree.addChild(tn, "pinky");

        // Miguel's right arm
        tn = sceneTree.find("Miguel the human");
        tn = sceneTree.find(tn, "torso", 0);
        tn = sceneTree.addChild(tn, "right arm");
        tn =  sceneTree.addChild(tn, "right hand");
        sceneTree.addChild(tn, "thumb");
        sceneTree.addChild(tn, "index finger");
        sceneTree.addChild(tn, "middle finger");
        sceneTree.addChild(tn, "ring finger");
        sceneTree.addChild(tn, "pinky");

        // add some parts to Lily
        tn = sceneTree.find("Lily the canine");
        tn = sceneTree.addChild(tn, "torso");
        sceneTree.addChild(tn, "right front paw");
        sceneTree.addChild(tn, "left front paw");
        sceneTree.addChild(tn, "right rear paw");
        sceneTree.addChild(tn, "left rear paw");
        sceneTree.addChild(tn, "spare mutant paw");
        sceneTree.addChild(tn, "wagging tail");

        // add some parts to table
        tn = sceneTree.find("table");
        sceneTree.addChild(tn, "north east leg");
        sceneTree.addChild(tn, "north west leg");
        sceneTree.addChild(tn, "south east leg");
        sceneTree.addChild(tn, "south west leg");

        System.out.println("\n------------ Loaded Tree ----------------- \n");
        sceneTree.display();

        sceneTree.remove("spare mutant paw");
        sceneTree.remove("Miguel the human");
        sceneTree.remove("an imagined higgs boson");

        System.out.println("\n------------ Virtual (soft) Tree --------------- \n");
        sceneTree.display();

        System.out.println("\n----------- Physical (hard) Display ------------- \n");
        sceneTree.displayPhysical();

        System.out.println("------- Testing Sizes (compare with above) -------- \n");
        System.out.println("virtual (soft) size: " + sceneTree.size()  );
        System.out.println("physical (hard) size: " + sceneTree.sizePhysical()  );

        System.out.println("------------ Collecting Garbage ---------------- \n");

        System.out.println("found soft-deleted nodes? " + sceneTree.softDeletedNodesFound()  );
        System.out.println("running garbage collect...");
        sceneTree.collectGarbage();
        System.out.println("found soft-deleted nodes? " + sceneTree.softDeletedNodesFound()  );
        System.out.println("running garbage collect again...");
        sceneTree.collectGarbage();
        System.out.println("found soft-deleted nodes? " + sceneTree.softDeletedNodesFound()  );


        System.out.println("--------- Hard Display after garbage collection ------------ \n");

        sceneTree.displayPhysical();

        System.out.println("Semi-deleted tree empty? " + sceneTree.empty() + "\n"); //should return false since the tree is not empty
        sceneTree.remove("room");

        System.out.println("Completely-deleted tree empty? " + sceneTree.empty() + "\n"); //should return false since there was no garbage collection

        sceneTree.display();
    }
}