<?php


include_once('includes/database.php');
for ($i=1;$i<101;$i++){
$db->query("update min_article set url= 'articles/art" .$i.".html' where id=".$i);
}

?>

