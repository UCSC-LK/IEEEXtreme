#-------------------------------------------------------------------------------
# Name:        bellmonford
# Purpose:
#
# Author:      AYYOOB
#
# Created:     02/10/2013
# Copyright:   (c) AYYOOB 2013
# Licence:     public
#-------------------------------------------------------------------------------
import sys
import math
def main():
    global graph;
    graph={};
    numLine = input();
    global distance;
    distance={};

    global parents;
    parents={};

    global vertice;
    vertice=[];

    global edges;
    edges=[];

    for i in range(int(numLine)):
        ins=input();
        ins=ins.split(",");
        edges.append([ins[0],ins[1]]);
        if ins[0] in graph:
            if ins[1] in graph[ins[0]]:
                print("invalid structure");
            else:
                graph[ins[0]][ins[1]] = float(ins[2])
        else:
            graph[ins[0]] ={}
            graph[ins[0]][ins[1]] = float(ins[2])

        if ins[0] not in vertice:
            vertice.append(ins[0])

        if ins[1] not in vertice:
            vertice.append(ins[1])





    pass


##    print("ayyoob test",si,end='\n',sep='');



def initialiseSingleSource(s):
    global vertice;
    global distance;
    global parents;
    for i in range(len(vertice)):
        distance[vertice[i]]=float("inf");
        parents[vertice[i]]=None;

    distance[s]=0;


def Relax(u,v):
    global Q;
    global parents;
    global distance;

    if distance[v] > distance[u] + (graph.get(u)).get(v):
        distance[v] = distance[u] + (graph.get(u)).get(v);
        parents[v] = u;






def bellmanford(s):
    initialiseSingleSource(s);
    S = list();#final weighted vertices
    VN = list();
    global vertice;
    for i in range(len(vertice)-1):
        for i in range(len(edges)):
            Relax(edges[i][0],edges[i][1])






if __name__ == '__main__':
    main()
    global parents;
    ##bellmanford(source)
    bellmanford('s')
    print(parents)



