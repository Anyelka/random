#include <cassert>
#include <iostream>
#include <set>
#include <vector>
using namespace std;

#define assertm(exp, msg) assert((void(msg), exp))

typedef long long myint64;

struct PRNG {
    myint64 seed;
    
    PRNG(myint64 seed) : seed(seed) {}
    
    myint64 _random() {
        myint64 M = 2147483647, A = 16807;
        myint64 Q = M/A, R = M%A;
        seed = A * (seed % Q) - R * (seed / Q);
        if (seed <= 0) seed += M;
        return seed;
    }
    
    myint64 randint(myint64 start, myint64 end) {
        return start + _random() % (end - start + 1);
    }
    
    myint64 choice(const vector<myint64> &seq) {
        return seq[ randint(0, ((myint64)(seq.size()))-1 ) ];
    }

    vector<myint64> sample(myint64 lo, myint64 hi, myint64 count) {
        set<myint64> answer;
        while ((myint64)answer.size() < count) answer.insert( randint(lo, hi) );
        return vector<myint64> (answer.begin(), answer.end());
    }
};

struct Checksum {
    myint64 chk;
    
    Checksum() : chk(47) {}

    void add(myint64 x) { chk = (42 * chk + x) % 123455678901LL; }
    
    void add(const vector<myint64> &xs) { for (myint64 x : xs) add(x); }

    void check() {
        myint64 exp = 36494177703LL;
        assertm(chk == exp, "Internal error, something went wrong, test checksum does not match.\n");
    }
};

const myint64 MAXD = 1'000'000'000;
const myint64 MAXN = 1'000'000;

int main() {
    PRNG random(4747);
    Checksum chk;

    vector< vector<myint64> > TESTS = { {MAXD,7}, {MAXD,42}, {MAXN/10,MAXN}, {MAXN,MAXN}, {10*MAXN,MAXN} };
    for (int t=0; t<13; ++t) TESTS.push_back( {MAXD,MAXN} );
    TESTS.push_back( {MAXD,MAXN,1,(myint64)(1.1*MAXN)} );
    TESTS.push_back( {MAXD,MAXN,(myint64)(1.1*MAXN),1} );
    TESTS.push_back( {MAXD,MAXN,2,MAXN} );
    TESTS.push_back( {MAXD,MAXN,MAXN/4,5,95} );
    TESTS.push_back( {MAXD,MAXN,2000,MAXN/1900} );
    TESTS.push_back( {MAXD,MAXN,2000,MAXN/1900,90} );
    TESTS.push_back( {MAXD,MAXN,MAXN/5,MAXN/5} );
    TESTS.push_back( {MAXD,MAXN,MAXN/5,MAXN/5,90} );
    TESTS.push_back( {MAXD,MAXN,MAXN/3,MAXN/3} );
    TESTS.push_back( {MAXD,MAXN,MAXN/3,MAXN/3,95} );
    TESTS.push_back( {MAXD,MAXN,MAXN,MAXN} );
    TESTS.push_back( {MAXD,MAXN,MAXN,MAXN,85} );

    assert( TESTS.size() == 30u );
    cout << TESTS.size() << "\n";
    for (unsigned nt=0; nt<TESTS.size(); ++nt) {
        cerr << "Generating test " << (nt+1) << "/" << TESTS.size() << endl;
        vector<myint64> test = TESTS[nt];
        myint64 d = test[0], n = test[1], sr = 1, sc = 1, gridprob = 0;
        if (test.size() >= 3u) sr = test[2];
        if (test.size() >= 4u) sc = test[3];
        if (test.size() >= 5u) gridprob = test[4];

        vector<myint64> rows = random.sample(0, d-1, sr);
        vector<myint64> cols = random.sample(0, d-1, sc);
        vector<myint64> rookr;
        vector<myint64> rookc;
        vector<char> rookcol;
        set<pair<myint64,myint64> > occupied;
        while ((myint64)rookr.size() < n) {
            myint64 r, c;
            if (random.randint(0, 99) < gridprob) {
                r = random.choice(rows);
                c = random.choice(cols);
            } else {
                r = random.randint(0, d-1);
                c = random.randint(0, d-1);
            }
            if (occupied.count({r,c})) continue;
            occupied.insert({r,c});
            rookr.push_back(r);
            rookc.push_back(c);
            rookcol.push_back( "BW"[random.randint(0,1)] );
        }
        cout << d << " " << n << "\n";
        for (int i=0; i<n; ++i) {
            cout << rookr[i] << " " << rookc[i] << " " << rookcol[i] << "\n";
            chk.add(rookr[i]); chk.add(rookc[i]); chk.add(rookcol[i]);
        }
    }
    chk.check();
}
