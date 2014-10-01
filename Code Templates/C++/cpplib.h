/**
 * C++ Utility Library
 * by Madura A.
 * Placed on public domain, this is "do what ever you want with it code" with
 * an additional statement for copyright. I do hold it and due credit is admired.
 *
 */
#ifndef CPPUTILS_H
#define CPPUTILS_H

#include <iostream>
#include <string>
#include <vector>
#include <set>
#include <map>
#include <cstring>
#include <sstream>
#include <cmath>
#include <list>
#include <limits>
#include <algorithm>
#include <cctype>
#include <cstdio>
#include <queue>
#include <cassert>

#ifdef _MSC_VER
#define typeof(x) decltype(x)
#endif

/** General foreach for STL like containers */
#define FORI(x,i,j) \
    for(int x=i;x<j;x++)

#define FORUI(x,i,j) \
    for(unsigned int x=i;x<j;x++)

#define FORSZ(x,i,j) \
    for(size_t x=i;x<j;x++)

#if __cplusplus < 201103L
#define FOREACH(var, container) \
    for(typeof((container).begin()) var = (container).begin(); \
        var != (container).end(); \
        ++var)
#else
#define FOREACH(var, container) \
    for(auto var = (container).begin(); \
        var != (container).end(); \
        ++var)
#endif

// helpers
namespace cpplib {
inline int char_to_int(char c)
{
    switch (c){
    case 'A':
        return 10;
    case 'B':
        return 11;
    case 'C':
        return 12;
    case 'D':
        return 13;
    case 'E':
        return 14;
    case 'F':
        return 15;
    default:
        return c - '0';
    }
}
}
namespace std{

/** String Functions  **/

template<typename FROM>
/** Convert to wstring from FROM type
 */
std::wstring to_wstring(FROM f){
    std::wstringstream ss;
    ss<<f;
    return ss.str();
}
template<typename FROM>
/** Convert to string from FROM type
 */
std::string to_string(FROM f){
    std::stringstream ss;
    ss<<f;
    return ss.str();
}
/** Get upper case string
 */
std::string to_upper(std::string str);
/** Get lower case string
 */
std::string to_lower(std::string str);
/** Trim string from start and end.
	@remarks
	Uses isspace() to check for blanks
 */
std::string trim(std::string str);
/** Splits a string by delim (char) and puts in the given vector
  */
void split(std::string str, char delim, std::vector<std::string>& strvec);
/** Splits a string by delim (c_str) and puts in the given vector
  */
void split(std::string str, char *delim, std::vector<std::string>& strvec);

template<typename TO>
/** TO=conversion type, 0 < base <= 16
 */
TO from_string(std::string number, TO base);
// following is not tested it was written thinking it'd be needed but it wasn't
// needed in the end but it is left here if anyone wants to make use of it, you
// have been warned this is *very* untested code
/** Reversable(in O(1)) Doubly Linked List
  *//*
template<typename T>
class reversable_list{
public:
    struct Node{
        T data;
        Node *next;
        Node *prev;
        Node(Node *_prev, T& _data, Node *_next): next(_next), prev(_prev), data(_data) {}
    };
private:

    Node *front;
    Node *back;
    int direction;
    int size;
public:
    reversable_list() : front(NULL), back(NULL), size(0), direction(1) {}
    inline void toggle_direction(){
        direction = 1-direction;
    }
    inline void push_back(const T& item){
        if (direction)
            push_back_real(item);
        else
            push_front_real(item);
    }
    inline void push_back(const T& item){
        if (direction)
            push_front_real(item);
        else
            push_back_real(item);
    }
private:
    inline void push_back_real(const T& item)
    {
        Node *n;
        n = new Node(back,item,NULL);

        if (!front) {
            front = n;
        }
        if (back) {
            back->prev = n;
        }
        back = n;
    }
    inline void push_front_real(const T& item)
    {
        Node *n;
        n = new Node(NULL,item,front);

        if (!back) {
            back = n;
        }
        if (front) {
            front->prev = n;
        }
        front = n;
    }
    inline Node *next(Node *current) {
        if (direction) { return current->next; } else { return current->prev; }
    }
    inline Node *previous(Node *current) {
        if (direction) { return current->prev; } else { return current->next; }
    }
};
*/
/** Mat 2D and 3D */
/* Matrix type for common 2D and 3D data use this instead of 2D/3D arrays
 * this is much easier to move around from function to function ..etc
 */

template<typename MAT_T>
struct Mat : public vector< MAT_T > {
    int sx,sy,sz;
    Mat(int x, int y) : sx(x), sy(y) ,sz(0) {
        vector< MAT_T >::resize(x*y);
    }
    Mat(int x, int y, int z) : sx(x), sy(y), sz(z) {
        vector< MAT_T >::resize(x*y*z);
    }
    typename vector<MAT_T>::reference at(int x, int y){
        assert(y<sy && x<sx);
        return vector<MAT_T>::at(sy*x+y);
    }
    typename vector<MAT_T>::reference at(int x,int y, int z) {
        assert(z<sz && y<sy && x<sx);
        return vector<MAT_T>::at(sy*sz*x + sz*y + z);
    }
};


/** Graph structures and algorithms **/
/*  NOTE: IDX_T can be int,long ..etc
 *  WGT_T can be int,long,float ..etc
 */

template<typename IDX_T, typename WGT_T>
struct edge{
    IDX_T a,b;
    WGT_T w;
    edge(){}
    edge(IDX_T _a, IDX_T _b, WGT_T _w){
        a=_a;b=_b;w=_w;
    }
};

template<typename IDX_T, typename WGT_T>
struct edge_comparator{
    bool operator() (const edge<IDX_T, WGT_T>& a,const edge<IDX_T, WGT_T>& b)
    {
        return (a.w < b.w);
    }
};
template<typename IDX_T>
class disjoint_set {
private:
    vector<IDX_T> parents,ranks;
public:
    disjoint_set(IDX_T size) {
        parents.resize(size);
        ranks.resize(size);
    }
    void make(IDX_T idx) {
        parents[idx]=idx;
        ranks[idx]=0;
    }
    IDX_T find(IDX_T idx) {
        while (parents[idx] != idx)
        {
            idx=parents[idx];
        }
        return idx;
    }
    void merge(IDX_T idx1, IDX_T idx2){
        IDX_T idx1root=find(idx1);
        IDX_T idx2root=find(idx2);

        if (idx1root != idx2root){
            if (ranks[idx1root] < ranks[idx2root]) {
                parents[idx1root] = idx2root;
            } else if (ranks[idx1root] > ranks[idx2root]) {
                parents[idx2root] = idx1root;
            } else {
                parents[idx2root] = idx1root;
                ranks[idx1root] = ranks[idx1root]+1;
            }
        }
    }
    void get_sets(map< IDX_T, vector<IDX_T> >& rset){
        FORI(i,0,parents.size())  {
            int k=find(i);
            if (rset.find(k)==rset.end()) {
                vector<IDX_T> g;
                g.push_back(i);
                rset[k] = g;
            } else {
                rset[k].push_back(i);
            }
        }
    }
};

template<typename IDX_T, typename WGT_T>
class edge_list : public vector< edge< IDX_T, WGT_T > >
{
private:
    IDX_T vertex_count;
public:
    edge_list(IDX_T verts) : vector< edge< IDX_T, WGT_T > >()
    {
        vertex_count = verts;
    }
    void add_edge(IDX_T a, IDX_T b, WGT_T w)
    {
        this->push_back(edge<IDX_T, WGT_T>(a,b,w));
    }
    inline IDX_T get_vertex_count(){
        return vertex_count;
    }
    void mst(edge_list< IDX_T, WGT_T >& mst){
        disjoint_set<IDX_T> ds(this->size());
        edge_comparator<IDX_T, WGT_T> ec;
        sort(this->begin(), this->end(), ec);
        FORI (i, 0 , vertex_count) {
            ds.make(i);
        }
        FOREACH (i, *this) {
            if ( ds.find(i->a) != ds.find(i->b) ) {
                mst.push_back(*i);
                ds.merge(i->a, i->b);
            }
        }
    }
};
template<typename IDX_T, typename WGT_T>
class adj_list : public vector< map<IDX_T,WGT_T> >
{

    class vertice_comparator
    {
        vector<IDX_T> *dist;
    public:
        vertice_comparator(vector<IDX_T> *_dist){
            dist = _dist;
        }
        bool operator() (const IDX_T& a, const IDX_T& b){
            return dist->at(a) > dist->at(b);
        }

    };

public:
    adj_list(edge_list<IDX_T, WGT_T>& edgelist) : vector< map<IDX_T,WGT_T> > ()
    {
        this->resize(edgelist.get_vertex_count());
        FOREACH(i,edgelist) {
            add_edge(i->a, i->b, i->w);
        }
    }
    adj_list(IDX_T vertices) : vector< map<IDX_T,WGT_T> > ()
    {
        this->resize(vertices);
    }
    inline WGT_T get_weight(IDX_T a, IDX_T b)
    {
        return this->at(a).find(a)->second;
    }
    void add_edge(IDX_T a, IDX_T b, WGT_T w)
    {
        this->at(a).insert(pair<IDX_T,WGT_T>(b,w));
    }
    bool has_edge(IDX_T a, IDX_T b)
    {
        map<IDX_T,WGT_T>& tt=this->at(a);
        return tt.find(b)!=tt.end();
    }
    /* http://en.wikibooks.org/wiki/Algorithm_implementation/Graphs/Maximum_flow/Edmonds-Karp */
    // convert that up :)
    void max_network_flow(IDX_T src, IDX_T sink){

    }
    /** Set WGT_T to int to find Eulerian trail */
    /*  See the wikipedia's article on eulerian path to see how this works
     *  I have implemented the algorithm that is listed last
     */
    vector<IDX_T> eulerian_trail(IDX_T idx){
        int total=0;
        FORI(i,0, this->size()) {
            FOREACH(j,this->at(i)) {
                this->at(i)[j->first]=0;
                total++;
            }
        }
        int count=0;
        int start_idx=idx;
        list<IDX_T> ls;
        typename list<IDX_T>::iterator current=ls.end();
        while (count<total) {
            map<IDX_T, WGT_T>& tt=this->at(idx);
            bool found=false;
            FOREACH(j,tt) {
                if (j->second == 0) {
                    ls.insert(current,idx);

                    idx=j->first;

                    tt[j->first]=1;
                    count++;

                    if (j->first != start_idx) {
                        found=true;
                    }
                    break;
                }

            }

            if (!found) {
                list<int>::iterator i=ls.begin();
                while (i!=ls.end()) {
                    map<IDX_T, WGT_T>& tt=this->at(*i);
                    bool found_free=false;
                    FOREACH(j,tt) {
                        if (j->second==0) {
                            idx=*i;
                            current=i;
                            start_idx=idx;
                            found_free=true;
                            break;
                        }
                    }
                    if (found_free)
                        break;
                    i++;
                }

            }
        }
        vector<IDX_T> vv;
        FOREACH(j,ls) {
            vv.push_back(*j);
        }
        return vv;
    }
    void bellmanford_shortest_path(IDX_T src,
                                   vector<IDX_T>& dist,
                                   vector<IDX_T>& pred)
    {
        dist.resize(this->size());
        pred.resize(this->size());
        FORI(i,0,dist.size()){
            dist[i]=std::numeric_limits<IDX_T>::max();
            pred[i]=std::numeric_limits<IDX_T>::max();
        }
        dist[src]=0;
        FORI(i,0,(int)dist.size()) {
            FORSZ(row,0,this->size()) {
                FOREACH(col,this->at(row)){
                    if (dist[row] + col->second < dist[col->first]){
                        dist[col->first] = dist[row] + col->second;
                        pred[col->first] = row;

                    }
                }
            }
        }

        FORSZ(row,0,this->size()) {
            FOREACH(col,this->at(row)){

                if (dist[row] + col->second < dist[col->first] )
                    cout<<("Graph contains a negative-weight cycle")<<endl;
            }
        }

    }

    void dijkstra_shortest_path(IDX_T src,
                                vector<IDX_T>& dist,
                                vector<IDX_T>& pred){
        vector<IDX_T> heap;
        dist.resize(this->size());
        pred.resize(this->size());
        heap.resize(this->size());
        FORI(i,0,dist.size()){
            dist[i]=std::numeric_limits<IDX_T>::max();
            pred[i]=std::numeric_limits<IDX_T>::max();
            heap[i]=i;
        }
        dist[src]=0;

        vertice_comparator vc(&dist);

        make_heap(heap.begin(), heap.end(),vc);

        while (!heap.empty()){
            pop_heap(heap.begin(),heap.end(),vc);

            IDX_T u=heap.back();
            heap.pop_back();
            if (dist[u]==numeric_limits<IDX_T>::max()) {
                break;
            }
            FOREACH(i, this->at(u)) {
                // i->first is index, i->second is weight
                WGT_T w =dist[u]+ i->second;
                if (w < dist[i->first]) {
                    dist[i->first]=w;
                    pred[i->first]=u;
                    make_heap(heap.begin(),heap.end(),vc);
                }
            }
        }

    }
};


/** Longest Common Subsequence */
/* s1, s2 are sequence lengths
* i, j are starting indexes you should start from the end of both strings
* all indexing starts with 0
* SEQ_T - sequence type
*
* Use the lcs_init function to initialize the Mat before calling lcs
* You can only call lcs_trace on a Mat that already has been gone through with lcs
*/

template<typename SEQ_T>
void lcs_init(Mat<int>& mem) {
    FORI(i,0,mem.sx) {
        FORI(j,0,mem.sy){
            mem.at(i,j)=numeric_limits<int>::max();
        }
    }
}

template<typename SEQ_T>
int lcs(Mat<int>& mem, SEQ_T *seq1, SEQ_T *seq2, int i, int j)
{
    if (i==-1 || j==-1) {
        return 0;
    } else if (mem.at(i,j) != numeric_limits<int>::max()) {
        return mem.at(i,j);
    } else if (seq1[i] == seq2[j]){
        mem.at(i,j)=lcs<SEQ_T>(mem,seq1,seq2,i-1,j-1)+1;
        return mem.at(i,j);
    } else {
        mem.at(i,j)=max<int>(lcs<SEQ_T>(mem,seq1,seq2,i,j-1) , lcs<SEQ_T>(mem,seq1,seq2,i-1,j));
        return mem.at(i,j);
    }
}

template<typename SEQ_T>
void lcs_trace(Mat<int>& mem, SEQ_T *seq1, SEQ_T *seq2, vector< SEQ_T >& trace, int i, int j )
{

    if (i==-1 || j==-1){
        return;
    } else if (seq1[i]==seq2[j]){
        trace.push_back(seq1[i]);
        lcs_trace<SEQ_T>(mem, seq1, seq2, trace, i-1, j-1);
    } else {

        if (mem.at(i,j-1) > mem.at(i-1,j)) {
            lcs_trace<SEQ_T>(mem, seq1, seq2, trace, i, j-1);
        } else {
            lcs_trace<SEQ_T>(mem, seq1, seq2, trace, i-1, j);
        }
    }

}
}

#endif // CPPUTILS_H
