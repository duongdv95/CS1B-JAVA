package com.company;

//Class Stack ---------------------------------------
class Stack
{
    // pointer to first node in stack
    private StackNode top;

    // constructor
    public Stack()
    {
        top = null;
    }

    public void push(StackNode newNode)
    {
        if (newNode == null)
            return;   // emergency return
        newNode.next = top;
        top = newNode;
    }

    public StackNode pop()
    {
        StackNode temp;

        temp = top;
        if (top != null)
        {
            top = top.next;
            temp.next = null; // don't give client access to stack!
        }
        return temp;
    }

    // console display
    public void showStack()
    {
        StackNode p;

        // Display all the nodes in the stack
        for( p = top; p != null; p = p.next )
            p.show();
    }

}