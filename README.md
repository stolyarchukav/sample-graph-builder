##Simple graph builder

###Usage
####Create graph
```$java
Graph<AnyUserType> graph = Graph.create();
```
#####Create oriented graph
```$java
Graph.createOriented();
```
#####Create graph with preferred traversal strategy
```$java
Graph.create(new DepthTraversalPathFinder());
Graph.create(new BreadthTraversalPathFinder());
```
Any custom strategy can be defined by implementing `graph.PathFinder` interface
####Add vertex to the graph
```$java
UUID vertexId = graph.addVertex(anyUserTypeValue);
```
where vertexId - generated unique identifier
####Add edge to the graph
```$java
graph.addEdge(vertexId1, vertexId2);
```
#####Add weighted edge to the graph
```$java
graph.addEdge(vertexId1, vertexId2, new Distance(75));
```
where `graph.Distance` contains value in kilometers. 
Any custom user weigh class can be defined by implementing interface `graph.Weight`
####Find all paths between 2 vertices
```$java
Collection<Path<String>> allPaths = graph.getAllPaths(vertexIdSource, vertexIdTarget);
```
Empty collection will be returned if no paths found
####Find the best path between 2 vertices
```$java
Optional<Path<String>> path = graph.getPath(vertexIdSource, vertexIdTarget);
```
"The best" means the lowest total weight. 
In case if weights are equal or graph is unweighted, path with less segments is considered as the best.   
####String representation of the path
```$java
String pathAsString = PathWriter.writeToString(path);
```

###Usage example
The following code fragment
```$java
Graph<String> graph = Graph.create();
UUID roma = graph.addVertex("Roma");
UUID napoli = graph.addVertex("Napoli");
graph.addEdge(roma, napoli, new Distance(227));

UUID orvieto = graph.addVertex("Orvieto");
graph.addEdge(roma, orvieto, new Distance(121));

graph.getPath(napoli, orvieto)
    .map(PathWriter::writeToString)
    .ifPresent(System.out::println);
```
will print:
~~~
Napoli --227km--> Roma --121km--> Orvieto. Total weight: 348km
~~~

###Build (compile and run unit tests)
~~~
gradlew clean build
~~~
