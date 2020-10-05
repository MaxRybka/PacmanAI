package com.zetcode;

import java.util.ArrayList;

interface Agent {
    ArrayList<Pair> startAgent();
    ArrayList<Pair>successor(Pair pos);
    boolean found(Pair pos);
}
