#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      AYYOOB
#
# Created:     18/10/2013
# Copyright:   (c) AYYOOB 2013
# Licence:     <your licence>
#-------------------------------------------------------------------------------
import itertools
def main():
    global graph;
    graph={};
    numLine = input();


    global vertice;
    vertice=[];

    global edges;
    edges=[];

    for i in range(int(numLine)):
        ins=input();
        ins=ins.split(",");
        edges.append([ins[0],ins[1]]);
        if max(ins[0],ins[1]) is ins[1] :

            graph[(ins[0],ins[1])] = float(ins[2])
        else:
            graph[(ins[1],ins[0])] = float(ins[2])

        if ins[0] not in vertice:
            vertice.append(ins[0])

        if ins[1] not in vertice:
            vertice.append(ins[1])
    from collections import OrderedDict
    from operator import itemgetter
    graph=OrderedDict(sorted(graph.items(), key=itemgetter(1)));

    pass

"""
based on http://en.wikipedia.org/wiki/Disjoint-set_data_structure
"""

def MakeSet(x):
     x.parent = x
     x.rank   = 0

def Union(x, y):
     xRoot = Find(x)
     yRoot = Find(y)
     if xRoot.rank > yRoot.rank:
         yRoot.parent = xRoot
     elif xRoot.rank < yRoot.rank:
         xRoot.parent = yRoot
     elif xRoot != yRoot: # Unless x and y are already in same set, merge them
         yRoot.parent = xRoot
         xRoot.rank = xRoot.rank + 1

def Find(x):
     if x.parent == x:
        return x
     else:
        x.parent = Find(x.parent)
        return x.parent



class Node:
    def __init__ (self, label):
        self.label = label
    def __str__(self):
        return self.label
    def lab(self):
        return self.label

def getNode(ch):
    global nodelist;
    for i in range(len(nodelist)):

        if  nodelist[i].lab()== ch:
            return nodelist[i];


def kruskal():
    finalList = list();
    global vertice;
    global graph;
    global nodelist;
    nodelist = [];


    for i in range(len(vertice)):
        nodelist.append(Node(vertice[i]))
##    print ("")
##    print ("objects labels:\t\t\t", [str(i) for i in nodelist])
    [MakeSet(node) for node in nodelist]
    for k, v in graph.items():
        node1=getNode(k[0]);
        node2=getNode(k[1])
        ##print ("%s: %s" % (k, v))
        if(Find(node1) != Find(node2)):
            finalList.append((k[0],k[1]))
            Union(node1,node2)

    print(finalList)

if __name__ == '__main__':
    main()
    kruskal()
