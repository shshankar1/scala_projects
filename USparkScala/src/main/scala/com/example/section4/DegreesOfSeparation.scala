package com.example.section4

import org.apache.spark.rdd.RDD
import org.apache.spark.{Accumulator, SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

object DegreesOfSeparation {
  val startCharacterId: Int = 5306
  val targetCharacterId: Int = 14

  var hitCounter: Option[Accumulator[Int]] = None

  type BFSData = (Array[Int], Int, String)
  type BFSNode = (Int, BFSData)

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DegreesOfSeparation").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)

    hitCounter = Some(sparkContext.accumulator(0))

    var iterationRDD = createRDDFromFile(sparkContext)

    for(iteration <- 1 to 10){
      //println("Running BFS Iteration# " + iteration)
      val mapped = iterationRDD.flatMap(bfsMap)

      mapped.count()
      //println("Processing " + mapped.count() + " values.")
      if(hitCounter.isDefined){
        val hitCount = hitCounter.get.value
        if(hitCount >0){
          println("Hit the target character! From " + hitCount +
            " different direction(s).")
          return
        }
      }
      iterationRDD = mapped.reduceByKey(bfsReduce)
    }


  }

  def convertToBFS(line: String): BFSNode = {
    val fields = line.split("\\s+")
    val heroID = fields(0).toInt

    var connections: ArrayBuffer[Int] = ArrayBuffer()
    for (connection <- 1 to (fields.length - 1)) {
      connections += fields(connection).toInt
    }

    var color: String = "WHITE"
    var distance: Int = 9999

    if (heroID == startCharacterId) {
      color = "GRAY"
      distance = 0
    }
    val bfsData: BFSData = (connections.toArray, distance, color)
    (heroID, bfsData)
  }

  def createRDDFromFile(sparkContext: SparkContext): RDD[BFSNode] = {
    val inputFile = sparkContext.textFile("data/Marvel-graph.txt")
    inputFile.map(convertToBFS)
  }

  def bfsMap(node: BFSNode): Array[BFSNode] = {
    val characterID: Int = node._1
    val data: BFSData = node._2

    val connections: Array[Int] = data._1
    val distance: Int = data._2
    var color: String = data._3

    val results: ArrayBuffer[BFSNode] = ArrayBuffer()
    if (color == "GRAY") {
      for (connection <- connections) {
        val newCharacterID: Int = connection
        val newDistance: Int = distance + 1
        val newColor: String = "GRAY"

        if (targetCharacterId == connection && hitCounter.isDefined) {
          hitCounter.get.add(1)
        }
        val newEntry: BFSNode = (newCharacterID, (Array(), newDistance, newColor))
        results += newEntry
      }
      color = "BLACK"
    }

    val thisEntry: BFSNode = (characterID, (connections, distance, color))
    results += thisEntry
    results.toArray
  }

  def bfsReduce(data1: BFSData, data2: BFSData): BFSData = {
    val edges1: Array[Int] = data1._1
    val distance1: Int = data1._2
    val color1: String = data1._3

    val edges2: Array[Int] = data2._1
    val distance2: Int = data2._2
    val color2: String = data2._3

    var distance: Int = 9999
    var color: String = "WHITE"
    var edges: ArrayBuffer[Int] = ArrayBuffer()

    if (edges1.length > 0)
      edges ++= edges1

    if (edges2.length > 0)
      edges ++= edges2

    if (distance1 < distance)
      distance = distance1

    if (distance2 < distance)
      distance = distance2

    if (color1 == "WHITE" && (color2 == "GRAY" || color2 == "BLACK"))
      color = color2

    if (color2 == "WHITE" && (color1 == "GRAY" || color1 == "BLACK"))
      color = color1

    if (color2 == "GRAY" && color1 == "BLACK")
      color = color2

    (edges.toArray, distance, color)
  }
}
