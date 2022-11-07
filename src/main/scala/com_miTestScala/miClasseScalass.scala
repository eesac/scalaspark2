package com_miTestScala
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}
import org.apache.spark
import org.apache.spark.sql.{DataFrame, SparkSession}

//import org.apache.spark.sql.SparkSession
//import net.snowflake.spark.snowflake.Utils.SNOWFLAKE_SOURCE_NAME
//import org.apache.log4j.Level
//import org.apache.log4j.Logger
import net.snowflake.spark.snowflake.Utils

object miClasseScalass
{
  def textFile(str: String) = ???


  def main(args: Array[String]): Unit =
  {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("SparkDemo04")
      .getOrCreate()

    val sfOptions = Map(
      "sfURL" -> "https://dgb48103.us-east-1.snowflakecomputing.com",
      "sfUser" -> "isaacalva",
      "sfPassword" -> "Password12",
      "sfDatabase" -> "DB_NEW",
      "sfSchema" -> "SCH_NEW",
      "sfWarehouse" -> "MI_WAREWHOSE",
      "sfRole" -> "accountadmin"
    )



    val df: DataFrame = spark.read
      .format("net.snowflake.spark.snowflake") // or just use "snowflake"
      .options(sfOptions)
      .option("dbtable", "big_data_table1")
      //.option("query", "select id, name, preferences, created_at from BIG_DATA_DB.BIG_DATA_SCHEMA.big_data_table1")
      //.option("query", "insert into big_data_table1 values(102,'test name2','test preferences2','2022-10-26') ")
      .load()

    df.show()

      .format(SNOWFLAKE_SOURCE_NAME)
      .options(sfOptions)
      .option("dbtable", "SPARK_SNOWFLAKE_TABLE")
      .load().cache

    println("SPARK_SNOWFLAKE_TABLE:")
    println("*********************")

  }






  //  package com.sparkdemo
//  object SparkDemo01 {
//    def main(args: Array[String]): Unit =
//    {
//      Logger.getRootLogger.setLevel(Level.INFO)
//      val sc = new SparkContext("local[*]", "testingYarYar")
//      val lines = sc.textFile("C:\\\\Users\\\\Consultant\\\\Documents\\\\hadoop_options.TXT")
//      val words = lines.flatMap(line => line.split(' '))
//      val wordsKVRdd = words.map(x => (x, 1))
//      val count = wordsKVRdd.reduceByKey((x, y) => x + y).map(x => (x._2, x._1)).sortByKey(false).map(x => (x._2, x._1)).take(10)
//      count.foreach(println)
//    }
//  }

}
