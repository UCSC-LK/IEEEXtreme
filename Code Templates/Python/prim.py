#-------------------------------------------------------------------------------
# Name:        prim
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

    for i in range(int(numLine)):
        ins=input();
        ins=ins.split(",");
        if ins[0] in graph:
            if ins[1] in graph[ins[0]]:
                print("invalid structure");
            else:
                graph[ins[0]][ins[1]] = float(ins[2])

        else:
            graph[ins[0]] ={}
            graph[ins[0]][ins[1]] = float(ins[2])

        if ins[1] in graph:
            if ins[0] in graph[ins[1]]:
                print("invalid structure");
            else:
                graph[ins[1]][ins[0]] = float(ins[2])

        else:
            graph[ins[1]] ={}
            graph[ins[1]][ins[0]] = float(ins[2])


        if ins[0] not in vertice:
            vertice.append(ins[0])

        if ins[1] not in vertice:
            vertice.append(ins[1])
##prim(start)
    prim('a')

    print(parents);


    pass






## HEAP IMPLEMENTATION

#left child
def left(i):
	return int(2 * i + 1)

#right child
def right(i):
	return int(2 * i + 2)

#parent node
def parent(i):
	return int(math.floor(i / 2))



class heap_node:
	_object = 0
	_value = 0

	def __init__(self,aobject,avalue):
		self._object = aobject
		self._value = avalue

'''
	Implementation of a binary minimum heap.
	@notes
	@todo
		Consider replacing _data w/ a more generic structure that allows nodes to have a weight
		and a pointer to a different data structure.
'''
class Minimum_Heap:
	#the main data array
	_data = None

	def __init__(self,data):
		self._data = list(data)
		self.build()

	#Min. heapify (cascade down from idx down until sub parent nodes are greater than their children)
	def __heapify__(self,idx):
		l = left(idx)
		r = right(idx)
		p = parent(idx)

		min = idx
		size = len(self._data)

		if l < size and self._data[l]._value < self._data[min]._value:
			min = l

		if r < size and self._data[r]._value < self._data[min]._value:
			min = r

		if not min == idx:
			tmp = self._data[min]
			self._data[min] = self._data[idx]
			self._data[idx] = tmp
			self.__heapify__(min)

	#build min heap
	def build(self):
		size = len(self._data)
		for i in range(int(math.floor(size/2))-1,-1,-1):
			self.__heapify__(i)

'''
	Implementation of a min priority queue
'''
class Minimum_Priority_Queue:
	_heap = None

	def __init__(self,data):
		self._heap = Minimum_Heap(data)

	#Get min w/o destroying nodes
	def min(self):
		return self._heap._data[0]

	#Get min, remove root node
	def extract_min(self):
		size = len(self._heap._data)
		if size <= 0:
			return None
		min = self._heap._data[0]
		self._heap._data[0] = self._heap._data[size-1]
		self._heap._data.pop(size-1)
		self._heap.__heapify__(0)
		return min

	#insert new value
	def insert(self,key):
		self._heap._data.append(float("inf"))
		self.decrease_key(len(self._data)-1,key)

	#decrease existing value
	def decrease_key(self,elem,key):
		if elem < 0 or elem >= len(self._heap._data):
			print ("elem",elem,"not in heap data range.  [0 <= ",elem," <= ",len(self._heap._data),"]");
			return

		if key > self._heap._data[elem]._value:
			print ("new key larger than current key")
			return

		self._heap._data[elem]._value = key

		i = elem
		#cascade changes upward by swapping parents until child is greater than parent.
		while i > 0 and self._heap._data[parent(i)]._value > self._heap._data[i]._value:
			tmp = self._heap._data[i]
			self._heap._data[i] = self._heap._data[parent(i)]
			self._heap._data[parent(i)] = tmp
			i = parent(i)

	def decrease_object(self,obj,key):
		count = 0
		for hn in self._heap._data:
			if hn._object == obj:
				self.decrease_key(count,key)
				return
			count += 1
		print ("Object not found in heap")





##heap



def initialise(s):
    global vertice;
    global distance;
    global parents;
    for i in range(len(vertice)):
        distance[vertice[i]]=float("inf");
        parents[vertice[i]]=None;

    distance[s]=0;


def compareAndAdd(u,v):

    global Q;
    global parents;
    global distance;
    global removed;

    if (distance[v] > (graph.get(u)).get(v)) and (v not in removed):

        parents[v] = u;

        Q.decrease_object(v,(graph.get(u)).get(v))


def prim(s):

    initialise(s);
    VN = list();
    #Convert vertices from our graph in to heap nodes
    global distance;
    for i in range(len(vertice)):
        VN.append(heap_node(vertice[i],distance[vertice[i]]))

    global pathlist;
    pathlist=[];

    global removed;
    removed=[];
    global Q;
    global graph;
    Q = Minimum_Priority_Queue(VN)
    uq = Q.extract_min()

    while not uq == None:

#get original vertex
        u = uq._object;
        removed.append(u);
        if(parents[u] is not None):
            pathlist.append((parents[u],u))

#Loop through adjecent nodes, relax each node.
        if (graph.get(''+u)) is None:
            pass;
        else:
            for v in list((graph.get(''+u)).keys()):

                compareAndAdd(u,v);

        uq = Q.extract_min()


    print(pathlist)



if __name__ == '__main__':
    main()




