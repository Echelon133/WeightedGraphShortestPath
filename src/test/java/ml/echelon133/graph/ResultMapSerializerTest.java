package ml.echelon133.graph;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ml.echelon133.graph.json.ResultMapSerializer;
import ml.echelon133.graph.json.VertexResultSerializer;
import ml.echelon133.graph.json.VertexSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ResultMapSerializerTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setup() {
        SimpleModule simpleModule = new SimpleModule();
        mapper = new ObjectMapper();

        JavaType vertexType = mapper.constructType(Vertex.class);
        JavaType vertexResultType = mapper.constructType(VertexResult.class);
        JavaType resultMapType = mapper.getTypeFactory().constructMapType(Map.class, Vertex.class, VertexResult.class);

        simpleModule.addSerializer(new VertexSerializer(vertexType));
        simpleModule.addSerializer(new VertexResultSerializer(vertexResultType));
        simpleModule.addSerializer(new ResultMapSerializer(resultMapType));
        mapper.registerModule(simpleModule);
    }

    @Test
    public void serializeByteGraphResultMap() throws Exception {
        Graph<Byte> byteGraph = TestGraphStore.getByteTestGraph();

        Vertex<Byte> startVertex = byteGraph.findVertex("byteVertex1");

        ShortestPathSolver<Byte> sps = new ShortestPathSolver<>(byteGraph);
        Map<Vertex<Byte>, VertexResult<Byte>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Byte>, VertexResult<Byte>> v : resultMap.entrySet()) {
            VertexResult<Byte> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Byte>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeShortGraphResultMap() throws Exception {
        Graph<Short> shortGraph = TestGraphStore.getShortTestGraph();

        Vertex<Short> startVertex = shortGraph.findVertex("shortVertex1");

        ShortestPathSolver<Short> sps = new ShortestPathSolver<>(shortGraph);
        Map<Vertex<Short>, VertexResult<Short>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Short>, VertexResult<Short>> v : resultMap.entrySet()) {
            VertexResult<Short> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Short>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeIntegerGraphResultMap() throws Exception {
        Graph<Integer> intGraph = TestGraphStore.getIntegerTestGraph();

        Vertex<Integer> startVertex = intGraph.findVertex("intVertex1");

        ShortestPathSolver<Integer> sps = new ShortestPathSolver<>(intGraph);
        Map<Vertex<Integer>, VertexResult<Integer>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Integer>, VertexResult<Integer>> v : resultMap.entrySet()) {
            VertexResult<Integer> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Integer>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeLongGraphResultMap() throws Exception {
        Graph<Long> longGraph = TestGraphStore.getLongTestGraph();

        Vertex<Long> startVertex = longGraph.findVertex("longVertex1");

        ShortestPathSolver<Long> sps = new ShortestPathSolver<>(longGraph);
        Map<Vertex<Long>, VertexResult<Long>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Long>, VertexResult<Long>> v : resultMap.entrySet()) {
            VertexResult<Long> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Long>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeFloatGraphResultMap() throws Exception {
        Graph<Float> floatGraph = TestGraphStore.getFloatTestGraph();

        Vertex<Float> startVertex = floatGraph.findVertex("floatVertex1");

        ShortestPathSolver<Float> sps = new ShortestPathSolver<>(floatGraph);
        Map<Vertex<Float>, VertexResult<Float>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Float>, VertexResult<Float>> v : resultMap.entrySet()) {
            VertexResult<Float> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value (compare as floats, because of the precision loss)
            assertEquals(vResult.getSumOfWeights().floatValue(), nodeSumOfWeights.floatValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Float>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeDoubleGraphResultMap() throws Exception {
        Graph<Double> doubleGraph = TestGraphStore.getDoubleTestGraph();

        Vertex<Double> startVertex = doubleGraph.findVertex("doubleVertex1");

        ShortestPathSolver<Double> sps = new ShortestPathSolver<>(doubleGraph);
        Map<Vertex<Double>, VertexResult<Double>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<Double>, VertexResult<Double>> v : resultMap.entrySet()) {
            VertexResult<Double> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value (compare as doubles, because of the precision loss)
            assertEquals(vResult.getSumOfWeights().doubleValue(), nodeSumOfWeights.doubleValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<Double>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeBigIntegerGraphResultMap() throws Exception {
        Graph<BigInteger> bigIntGraph = TestGraphStore.getBigIntegerTestGraph();

        Vertex<BigInteger> startVertex = bigIntGraph.findVertex("bigIntVertex1");

        ShortestPathSolver<BigInteger> sps = new ShortestPathSolver<>(bigIntGraph);
        Map<Vertex<BigInteger>, VertexResult<BigInteger>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<BigInteger>, VertexResult<BigInteger>> v : resultMap.entrySet()) {
            VertexResult<BigInteger> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value
            assertEquals(vResult.getSumOfWeights(), nodeSumOfWeights.decimalValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<BigInteger>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }

    @Test
    public void serializeBigDecimalGraphResultMap() throws Exception {
        Graph<BigDecimal> bigDecGraph = TestGraphStore.getBigDecimalTestGraph();

        Vertex<BigDecimal> startVertex = bigDecGraph.findVertex("bigDecVertex1");

        ShortestPathSolver<BigDecimal> sps = new ShortestPathSolver<>(bigDecGraph);
        Map<Vertex<BigDecimal>, VertexResult<BigDecimal>> resultMap = sps.solveStartingFrom(startVertex);

        String serializedResultMap = mapper.writeValueAsString(resultMap);

        JsonNode mainNode = mapper.readTree(serializedResultMap);
        JsonNode results = mainNode.get("results");

        // Compare each VertexResult's values with serialized values
        for (Map.Entry<Vertex<BigDecimal>, VertexResult<BigDecimal>> v : resultMap.entrySet()) {
            VertexResult<BigDecimal> vResult = v.getValue();
            JsonNode vertexObj = results.findValue(v.getKey().getName());

            JsonNode nodePreviousVertex = vertexObj.get("previousVertex");
            JsonNode nodeSumOfWeights = vertexObj.get("sumOfWeights");
            JsonNode nodePathToVertex = vertexObj.get("pathToVertex");

            // Check previousVertex name
            try {
                assertEquals(vResult.getPreviousVertex().getName(), nodePreviousVertex.asText());
            } catch (NullPointerException ex) {
                // if vResult.getPreviousVertex() is null, then check whether serialized field is also null
                assertTrue(nodePreviousVertex.isNull());
            }

            // Check sumOfWeights value (compare as double, because e.g. BigDecimal 0.10 and 0.1 are considered not equal)
            assertEquals(vResult.getSumOfWeights().doubleValue(), nodeSumOfWeights.doubleValue());

            // Check pathToVertex elements (their order and values)
            LinkedList<Vertex<BigDecimal>> vPath = vResult.getPathToVertex();
            // get elements by their indexes to make sure that the order remains the same after serialization
            for (int i = 0; i < vPath.size(); i++) {
                String expectedVertexName = vPath.get(i).getName();
                String serializedVertexName = nodePathToVertex.get(i).asText();

                assertEquals(expectedVertexName, serializedVertexName);
            }
        }
    }
}
