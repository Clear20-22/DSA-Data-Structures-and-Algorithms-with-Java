#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
using namespace std;

// Graph representation
map<string, vector<string>> graph;
map<string, bool> visited;
map<string, int> inTime, lowTime;
int timer = 0;

// Function to perform DFS
void dfs(string node, string parent) {
    visited[node] = true;
    inTime[node] = lowTime[node] = ++timer;

    for (auto neighbor : graph[node]) {
        if (neighbor == parent) {
            // Ignore the edge that leads back to the parent
            continue;
        }
        if (visited[neighbor]) {
            // Back edge
            cout << "Back edge: " << node << "-" << neighbor << endl;
            // Update low time of the current node
            lowTime[node] = min(lowTime[node], inTime[neighbor]);
        } else {
            // Tree edge
            cout << "Tree edge: " << node << "-" << neighbor << endl;
            dfs(neighbor, node);
            // Update low time after visiting the child
            lowTime[node] = min(lowTime[node], lowTime[neighbor]);
        }
    }

    // Increment timer during backtracking
    ++timer;
    cout << "Backtracking from node " << node << ", updated timer: " << timer << endl;
}

int main() {
    // Create the graph
    vector<pair<string, string>> edges = {
        {"A", "C"}, {"A", "E"}, {"B", "D"}, {"B", "F"},
        {"B", "I"}, {"B", "G"}, {"B", "E"}, {"C", "I"}, {"I", "H"}, {"D", "G"}
    };

    // Add edges to the graph (undirected)
    for (auto edge : edges) {
        graph[edge.first].push_back(edge.second);
        graph[edge.second].push_back(edge.first);
        sort(graph[edge.first].begin(),graph[edge.first].end());
        sort(graph[edge.second].begin(),graph[edge.second].end());
    }

    // Initialize visited map
    for (auto node : graph) {
        visited[node.first] = false;
    }

    // Perform DFS and print tree/back edges and times
    for (auto node : graph) {
        if (!visited[node.first]) {
            dfs(node.first, "-1");
        }
    }

    // Print in time and low time
    cout << "\nNode\tIn Time\tLow Time\n";
    for (auto node : graph) {
        cout << node.first << "\t" << inTime[node.first] << "\t" << lowTime[node.first] << endl;
    }

    return 0;
}
