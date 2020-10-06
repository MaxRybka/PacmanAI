package com.zetcode;

import java.util.ArrayList;
import java.util.Stack;

interface Agent {
    Stack<Pair> startAgent();
    ArrayList<Pair>successor(Pair pos);
    boolean found(Pair pos);
}
