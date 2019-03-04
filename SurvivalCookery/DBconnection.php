<?php

$host="localhost";
$user="root";
$pass="root";
$dbname="recipes";

$con= mysql_connect($host,$user,$pass);
$BD= mysql_select_db($dbname, $con);

$query=mysql_query("select * from recipe");
$num=mysql_num_rows($query);

if($num==1)
{
    while($list=mysql_fetch_assoc($query))
    {
        $output=$list;
        echo json_encode($output);
    }
        mysql_close();
    }
?>